package org.example.Assignment;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Queries {
    // Assuming you have Log class with a customLogger method
    // You may need to replace this with your actual logging mechanism

    public static void runQueries(Map<String, Coin> coinMap, Map<String, Trader> traderMap) {
        Scanner sc = new Scanner(System.in);

        Log.customLogger("Enter the choice 1->Get coin info , 2->Top 5 Coins , 3->Get PortFolio by wallet address ,4->Get profit loss ,5->Show top 5 and bottom 5 traders ", "INFO");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Log.customLogger("Enter the coin Symbol", "INFO");
                String symbol = sc.next();

                // Fetching coin details
                List<Coin> coinDetails = getCoinDetails(symbol, coinMap);
                Log.customLogger("Coin details for symbol " + symbol + ": " + coinDetails, "INFO");
                break;

            case 2:
                // Fetching top 5 coins
                List<Coin> top5Coins = getTop50Coins(coinMap).subList(0, Math.min(5, getTop50Coins(coinMap).size()));
                Log.customLogger("Top 5 Coins: " + top5Coins, "INFO");
                break;

            case 3:
                Log.customLogger("Enter the wallet address", "INFO");
                String walletAddress = sc.next();

                // Fetching trader portfolio
                Map<String, Long> traderPortfolio = getTraderPortfolio(walletAddress, traderMap);
                Log.customLogger("Trader portfolio for wallet address " + walletAddress + ": " + traderPortfolio, "INFO");
                break;

            case 4:
                Log.customLogger("Enter the wallet address", "INFO");
                String profitLossWalletAddress = sc.next();

                // Fetching trader profit loss
                double traderProfitLoss = getTraderProfitLoss(profitLossWalletAddress, traderMap);
                Log.customLogger("Trader profit loss for wallet address " + profitLossWalletAddress + ": " + traderProfitLoss, "INFO");
                break;

            case 5:
                // Fetching top and bottom traders
                Map<String, Double> topBottomTraders = getTopBottomTraders(traderMap);
                Log.customLogger("Top and bottom 5 traders: " + topBottomTraders, "INFO");
                break;

            default:
                Log.customLogger("Invalid choice", "INFO");
                break;
        }
    }

    public static List<Coin> getCoinDetails(String nameOrCode, Map<String, Coin> coinMap) {
        return coinMap.values().stream()
                .filter(coin -> coin.getSymbol().equalsIgnoreCase(nameOrCode) || coin.getName().equalsIgnoreCase(nameOrCode))
                .collect(Collectors.toList());
    }

    public static List<Coin> getTop50Coins(Map<String, Coin> coinMap) {
        return coinMap.values().stream()
                .sorted(Comparator.comparingDouble(Coin::getPrice).reversed())
                .limit(50)
                .collect(Collectors.toList());
    }

    public static Map<String, Long> getTraderPortfolio(String walletAddress, Map<String, Trader> traderMap) {
        Trader trader = traderMap.get(walletAddress);
        return (trader != null && trader.getPortfolio() != null)
                ? trader.getPortfolio().coinQuantities
                : null;
    }

    public static double getTraderProfitLoss(String walletAddress, Map<String, Trader> traderMap) {
        Trader trader = traderMap.get(walletAddress);
        return (trader != null && trader.getPortfolio() != null)
                ? trader.getPortfolio().totalProfitLoss
                : 0.0;
    }

    public static Map<String, Double> getTopBottomTraders(Map<String, Trader> traderMap) {
        return traderMap.values().stream()
                .sorted(Comparator.comparingDouble(trader -> trader.getPortfolio().totalProfitLoss))
                .collect(Collectors.toMap(Trader::getWalletAddress, trader -> trader.getPortfolio().totalProfitLoss));
    }
}
