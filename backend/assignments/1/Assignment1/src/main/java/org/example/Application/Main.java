package org.example.Application;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.example.Logging;

import static org.example.Application.CryptoTradingApplication.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        coinMap = loadCoinsFromCSV("src/main/resources/coins.csv");
        traderMap = loadTradersFromCSV("src/main/resources/traders.csv");
        transactions = loadTransactionsFromJSON("src/main/resources/test_transaction.json");

        assert transactions != null;
        ExecutorService executorService = Executors.newFixedThreadPool(transactions.size());
        Latch = new CountDownLatch(transactions.size());

        for (Transaction transaction : transactions) {
            TransactionProcessor transactionProcessor = new TransactionProcessor(transaction, coinMap, traderMap, Latch);

            executorService.submit(transactionProcessor);
        }

        Latch.await();
        executorService.shutdown();

        // Query examples
        String coinSymbol = "RENBTC";

        // a. Given the name or code of a coin, retrieve all its details.
        List<Coin> coinDetails = CryptoQueries.getCoinDetails(coinSymbol, coinMap);
        Logging.logInfo("Details of ".concat(coinSymbol).concat(": ").concat(String.valueOf(coinDetails)));

        // b. Display top 50 coins in the market based on price.
        List<Coin> top50Coins = CryptoQueries.getTop50Coins(coinMap);
        Logging.logInfo("Top 50 coins based on price: ".concat(String.valueOf(top50Coins)));

        // c. For a given trader, show his portfolio.
        String walletAddress = "0x6048710a582fc9ebc9f46afd0fcda2f8";
        Map<String, Long> traderPortfolio = CryptoQueries.getTraderPortfolio(walletAddress, traderMap);
        Logging.logInfo("Portfolio of trader ".concat(walletAddress).concat(": ").concat(String.valueOf(traderPortfolio)));

        // d. For a given trader, display the total profit or loss they have made trading in the crypto market.
        double traderProfitLoss = CryptoQueries.getTraderProfitLoss(walletAddress, traderMap);
        Logging.logInfo("Total profit/loss for trader ".concat(walletAddress).concat(": ").concat(String.valueOf(traderProfitLoss)));

        // e. Show top traders based on their profit/loss.
        Map<String, Double> topTraders = CryptoQueries.getTopBottomTraders(traderMap);


        List<Map.Entry<String, Double>> top5Traders = topTraders.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());

        Logging.logInfo("Top 5 Traders: " + top5Traders);

        Map<String, Double> topBottomTraders = CryptoQueries.getTopBottomTraders(traderMap);
        List<Map.Entry<String, Double>> bottom5Traders = topBottomTraders.entrySet().stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .limit(5)
                .collect(Collectors.toList());

        Logging.logInfo("Bottom 5 Traders: ".concat(String.valueOf(bottom5Traders)));
    }
}
