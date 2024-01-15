package org.example.Application;

import org.example.Logging;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class TransactionProcessor implements Runnable {
    private Transaction transaction;
    private Map<String, Coin> coinMap;
    private Map<String, Trader> traderMap;
    private CountDownLatch latch;

    TransactionProcessor(Transaction transaction, Map<String, Coin> coinMap, Map<String, Trader> traderMap, CountDownLatch latch) {
        this.transaction = transaction;
        this.coinMap = coinMap;
        this.traderMap = traderMap;
        this.latch = latch;
    }

    private void buyingFunction(Map<String, Coin> coinMap, Transaction transaction, Coin coin, Map<String, Trader> traderMap) {
        Long quantity = transaction.getTransactionData().getQuantity();
        Trader trader = traderMap.get(transaction.getTransactionData().getWallet_address());

        if (coin != null && trader != null && trader.getPortfolio() != null) {
            synchronized (CryptoTradingApplication.class) {
                Long availableVolume = coin.getCirculatingSupply();
                double price = coin.getPrice();
                double transactionNet = price * quantity;

                if (availableVolume < quantity) {
                    // Insufficient supply, transition to a pending state
                    try {
                        coin.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                coin.setCirculatingSupply(availableVolume - quantity);
                coinMap.put(coin.getSymbol(), coin);
;

                synchronized (trader.getPortfolio().coinQuantities) {
                    if (trader.getPortfolio().coinQuantities.containsKey(coin.getSymbol())) {
                        Long available = trader.getPortfolio().coinQuantities.get(coin.getSymbol());
                        Long updated = available + quantity;
                        trader.getPortfolio().coinQuantities.put(coin.getSymbol(), updated);
                    } else {
                        trader.getPortfolio().coinQuantities.put(coin.getSymbol(), quantity);
                    }

                    if (trader.getPortfolio() != null) {
                        double profitLoss = trader.getPortfolio().totalProfitLoss - transactionNet;
                        trader.getPortfolio().totalProfitLoss = profitLoss;
                    }
                }

                CryptoTradingApplication.class.notifyAll();
            }
        }
    }

    private void sellingFunction(Map<String, Coin> coinMap, Transaction transaction, Coin coin, Map<String, Trader> traderMap) {
        Long quantity = transaction.getTransactionData().getQuantity();
        Trader trader = traderMap.get(transaction.getTransactionData().getWallet_address());

        synchronized (CryptoTradingApplication.class) {
            if (coin != null && trader != null && trader.getPortfolio() != null) {
                Long availableVolume = coin.getCirculatingSupply();
                double price = coin.getPrice();

                double transactionNet = price * quantity;

                coin.setCirculatingSupply(availableVolume + quantity);
                coinMap.put(coin.getSymbol(), coin);
                CryptoTradingApplication.class.notifyAll(); // Notify waiting threads


                    if (trader.getPortfolio().coinQuantities.containsKey(coin.getSymbol())) {
                        Long available = trader.getPortfolio().coinQuantities.get(coin.getSymbol());
                        Long updated = available - quantity;
                        if (updated < 0) {
                            updated = 0L;
                        }
                        trader.getPortfolio().coinQuantities.put(coin.getSymbol(), updated);
                    } else {
                        trader.getPortfolio().coinQuantities.put(coin.getSymbol(), quantity);
                    }



            }

            CryptoTradingApplication.class.notifyAll();
        }
    }

    private void updatePrice(Map<String, Coin> coinMap, Transaction transaction) {
        String coinSymbol = transaction.getTransactionData().getCoin();

        if (coinSymbol != null && coinMap.containsKey(coinSymbol)) {
            Coin coin = coinMap.get(coinSymbol);

            Double newPrice = transaction.getTransactionData().getPrice();
            if (newPrice != null) {
                synchronized (CryptoTradingApplication.class) {
                    coin.setPrice(newPrice);
                    coinMap.put(coinSymbol, coin);
                }
            }



        }
    }

    private void updateVolume(Map<String, Coin> coinMap, Transaction transaction) {
        String coinSymbol = transaction.getTransactionData().getCoin();

        if (coinSymbol != null && coinMap.containsKey(coinSymbol)) {
            Coin coin = coinMap.get(coinSymbol);

            Long newVolume = transaction.getTransactionData().getVolume();
            if (newVolume != null) {
                synchronized (CryptoTradingApplication.class) {
                    coin.setVolume(newVolume);
                    coinMap.put(coinSymbol, coin);
                    CryptoTradingApplication.class.notifyAll();
                }
            }
        }
    }

    private void processFunction() {
        String walletAddress = transaction.getTransactionData().getWallet_address();
        Trader trader = traderMap.get(walletAddress);
        Coin coin = coinMap.get(transaction.getTransactionData().getCoin());

        if (transaction.getType() != null) {
            switch (transaction.getType()) {
                case "BUY":
                    Long quantity = transaction.getTransactionData().getQuantity();
                    Long circulatingSupply = (coin != null) ? coin.getCirculatingSupply() : null;

                    if (circulatingSupply != null) {
                        buyingFunction(coinMap, transaction, coin, traderMap);
                    }
                    break;

                case "SELL":
                    sellingFunction(coinMap, transaction, coin, traderMap);
                    break;

                case "UPDATE_PRICE":
                    updatePrice(coinMap, transaction);
                    break;

                case "ADD_VOLUME":
                    updateVolume(coinMap, transaction);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void run() {
        synchronized (CryptoTradingApplication.class) {
            try {
                Logging.logInfo("Processing transaction: ".concat(String.valueOf(transaction)));
                processFunction();
            } finally {
                latch.countDown();
            }
        }
    }

}
