package org.example.Application;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;





    public class CryptoQueries {

        // Assuming coinMap is a Map<String, Coin> and traderMap is a Map<String, Trader>

        // a. Given the name or code of a coin, retrieve all its details.
        public static List<Coin> getCoinDetails(String nameOrCode, Map<String, Coin> coinMap) {
            return coinMap.values().stream()
                    .filter(coin -> coin.getSymbol().equalsIgnoreCase(nameOrCode) || coin.getName().equalsIgnoreCase(nameOrCode))
                    .collect(Collectors.toList());
        }

        // b. Display top 50 coins in the market based on price.
        public static List<Coin> getTop50Coins(Map<String, Coin> coinMap) {
            return coinMap.values().stream()
                    .sorted(Comparator.comparingDouble(Coin::getPrice).reversed())
                    .limit(50)
                    .collect(Collectors.toList());
        }

        // c. For a given trader, show his portfolio.
        public static Map<String, Long> getTraderPortfolio(String walletAddress, Map<String, Trader> traderMap) {
            Trader trader = traderMap.get(walletAddress);
            return (trader != null && trader.getPortfolio() != null)
                    ? trader.getPortfolio().coinQuantities
                    : null;
        }


        // d. For a given trader, display the total profit or loss they have made trading in the crypto market.
        public static double getTraderProfitLoss(String walletAddress, Map<String, Trader> traderMap) {
            Trader trader = traderMap.get(walletAddress);
            return (trader != null && trader.getPortfolio() != null)
                    ? trader.getPortfolio().totalProfitLoss
                    : 0.0;
        }

        // e. Show top 5 and bottom 5 traders based on their profit/loss.
        public static Map<String, Double> getTopBottomTraders(Map<String, Trader> traderMap) {
            return traderMap.values().stream()
                    .sorted(Comparator.comparingDouble(trader -> trader.getPortfolio().totalProfitLoss))
                    .collect(Collectors.toMap(Trader::getWalletAddress, trader -> trader.getPortfolio().totalProfitLoss));
        }

    }

