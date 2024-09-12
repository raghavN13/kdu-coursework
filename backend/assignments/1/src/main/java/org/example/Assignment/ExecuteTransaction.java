package org.example.Assignment;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class ExecuteTransaction implements Runnable {

    private JsonNode transaction;
    private CountDownLatch latch;
    private Map<String, Coin> coinMap;
    private Map<String, Trader> traderMap;
    private String transactionType;

    public ExecuteTransaction() {

    }

    public ExecuteTransaction(JsonNode transaction, CountDownLatch latch, Map<String, Coin> coinMap, Map<String, Trader> traderMap) {
        this.transaction = transaction;
        this.coinMap = coinMap;
        this.traderMap = traderMap;
        this.transactionType = transaction.get("type").asText();
        this.latch = latch;
    }

    private boolean buyingFunction() {
        String coinSymbol = transaction.get("data").get("coin").asText();
        Coin coinone = coinMap.get(coinSymbol);

        if (coinone == null) {
            // Handle the case when the coin is not found in the map
            return false;
        }


        long availableVolume = coinMap.get(transaction.get("data").get("coin").asText()).getCirculatingSupply();

        long quantity = transaction.get("data").get("quantity").asLong();

        if (quantity > availableVolume) return false;

        synchronized (coinMap.get(transaction.get("data").get("coin").asText())) {
            long difference = availableVolume - quantity;
            coinMap.get(transaction.get("data").get("coin").asText()).setCirculatingSupply(difference);
        }
        synchronized (traderMap.get(transaction.get("data").get("wallet_address").asText())) {
            Trader trader = traderMap.get(transaction.get("data").get("wallet_address").asText());
            Coin coin = coinMap.get(transaction.get("data").get("coin").asText());

            if (trader.getPortfolio().coinQuantities.containsKey(coin.getSymbol())) {
                Long available = trader.getPortfolio().coinQuantities.get(coin.getSymbol());
                Long updated = available + quantity;
                trader.getPortfolio().coinQuantities.put(coin.getSymbol(), updated);
            } else {
                trader.getPortfolio().coinQuantities.put(coin.getSymbol(), quantity);
            }

            if (trader.getPortfolio() != null) {
                trader.getPortfolio().totalProfitLoss = trader.getPortfolio().totalProfitLoss - (coin.getPrice() * quantity);

            }

        }
        return true;
    }

    private boolean sellingFunction() {
        long quantity = transaction.get("data").get("quantity").asLong();
        Trader trader = traderMap.get(transaction.get("data").get("wallet_address").asText());
        Coin coin = coinMap.get(transaction.get("data").get("coin").asText());
        if (trader == null || trader.getPortfolio() == null) {
            return false;
        }
        Map<String, Long> coinQuantities = trader.getPortfolio().coinQuantities;
        if (coinQuantities == null) {
            return false;
        }

        if (trader.getPortfolio() == null) return false;


        synchronized (trader) {
            if (coin != null && trader.getPortfolio() != null) {
                Long availableVolume = coin.getCirculatingSupply();
                double price = coin.getPrice();

                double transactionNet = price * quantity;

                coin.setCirculatingSupply(availableVolume + quantity);
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
        }
        return true;

    }


    private boolean updatePrice() {
        double newPrice = transaction.get("data").get("price").asDouble();
        Coin coin = coinMap.get(transaction.get("data").get("coin").asText());
        if(coin==null) return false;
        synchronized (coin) {


            coin.setPrice(newPrice);
        }

        return true;
    }

    private boolean updateVolume() {
        long newVolume = transaction.get("data").get("volume").asLong();
        Coin coin = coinMap.get(transaction.get("data").get("coin").asText());
        if(coin==null) return false;
        synchronized (coin) {

            coin.setVolume(newVolume);
        }

        return true;

    }

    @Override
    public void run() {

        boolean flag = false;
        int count = 200;
        while (!flag && count > 0) {
            if (transactionType.equals("BUY")) {

                flag = buyingFunction();
                if (flag) {
                    Log.customLogger("Bought!!", "INFO");
                }
            } else if (transactionType.equals("SELL")) {
                flag = sellingFunction();
                if (flag) {
                    Log.customLogger("Sold!!", "INFO");
                }
            } else if (transactionType.equals("ADD_VOLUME")) {
                flag = updateVolume();
                if (flag) {
                    Log.customLogger("Volume Updated !!", "INFO");
                }
            } else {
                flag = updatePrice();
                if (flag) {
                    Log.customLogger("Price Updated!!", "INFO");
                }
            }


            if (!flag) {
                try {
                    Thread.sleep(5);
                    count--;
                } catch (InterruptedException e) {
                    Log.customLogger("The Code is Stopped", "ERROR");
                }

            }
        }
//        System.out.println(coinMap.get(transaction.get("data").get("coin").asText()).getSymbol() + coinMap.get(transaction.get("data").get("coin").asText()).circulatingSupply);
        latch.countDown();


    }

    private String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}