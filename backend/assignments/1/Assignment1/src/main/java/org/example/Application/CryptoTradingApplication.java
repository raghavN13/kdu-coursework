package org.example.Application;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CryptoTradingApplication {
    public static Map<String, Coin> coinMap;
    public static Map<String, Trader> traderMap;
    public static List<Transaction> transactions;
    public static CountDownLatch Latch;

    private Object syncObject;

    static Map<String, Coin> loadCoinsFromCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> csvData = reader.readAll();
            // Assuming the first row contains headers, start from index 1
            List<Coin> coins = Coin.fromCsvData(csvData.subList(1, csvData.size()));


            // Create a map using coin symbols as keys
            Map<String, Coin> coinMap = new HashMap<>();
            for (Coin coin : coins) {
                coinMap.put(coin.getSymbol(), coin);
            }

            return coinMap;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null;
        }
    }

    static Map<String, Trader> loadTradersFromCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> csvData = reader.readAll();
            List<Trader> traders = Trader.fromCsvData(csvData.subList(1, csvData.size()));

            // Create a map using wallet addresses as keys
            Map<String, Trader> traderMap = new HashMap<>();
            for (Trader trader : traders) {
                traderMap.put(trader.getWalletAddress(), trader);
            }

            return traderMap;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null;
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }


    }

    static List<Transaction> loadTransactionsFromJSON(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Transaction> transactions = objectMapper.readValue(new File(filePath), new TypeReference<List<Transaction>>() {
            });
            return transactions;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }



}
