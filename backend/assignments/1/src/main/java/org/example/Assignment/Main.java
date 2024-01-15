package org.example.Assignment;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static List<String []> parseCSV(Path path) throws IOException {
        ArrayList<String []> coinList = new ArrayList<>();
        FileReader fileReader = new FileReader(path.toFile());
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        String[] nextRecord;
        while (( nextRecord = csvReader.readNext())!=null){
            coinList.add(nextRecord);
        }
        return coinList;
    }

    static Map<String, Coin> loadCoinsFromCSV(String filePath) throws IOException{
         CSVReader reader = new CSVReader(new FileReader(filePath));
            List<String[]> csvData = reader.readAll();
            // Assuming the first row contains headers, start from index 1
            List<Coin> coins = Coin.fromCsvData(csvData.subList(1, csvData.size()));


            // Create a map using coin symbols as keys
            Map<String, Coin> coinMap = new HashMap<>();
            for (Coin coin : coins) {
                coinMap.put(coin.getSymbol(), coin);
            }

            return coinMap;

    }

    static Map<String, Trader> loadTradersFromCSV(String filePath) throws IOException{
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> csvData = reader.readAll();
            List<Trader> traders = Trader.fromCsvData(csvData.subList(1, csvData.size()));

            // Create a map using wallet addresses as keys
            Map<String, Trader> traderMap = new HashMap<>();
            for (Trader trader : traders) {
                traderMap.put(trader.getWalletAddress(), trader);
            }

            return traderMap;
        }


    }

    public static JsonNode parseJsonFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File from = new File(path);
        return mapper.readTree(from);
    }


    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch,Map<String, Coin> coinMap,Map<String, Trader> traderMap) throws InterruptedException {
        ExecutorService transactionThreadPool = Executors.newFixedThreadPool(jsonTransactions.size());
        for(JsonNode transaction: jsonTransactions){
            ExecuteTransaction transactionObject = new ExecuteTransaction(transaction, latch,coinMap,traderMap);
            transactionThreadPool.execute(transactionObject);
        }
        transactionThreadPool.shutdown();
    }


    public static void main(String [] args) throws IOException, InterruptedException {

        Map<String,Coin>coinMap = loadCoinsFromCSV("/home/lenovo/Assignment1Work/untitled/src/main/resources/coins.csv");
        Map<String,Trader>traderMap = loadTradersFromCSV("/home/lenovo/Assignment1Work/untitled/src/main/resources/traders.csv");

        JsonNode jsonTransactions = parseJsonFile("/home/lenovo/Assignment1Work/untitled/src/main/resources/test_transaction.json");

        CountDownLatch latch = new CountDownLatch(jsonTransactions.size());

        executeTransactions(jsonTransactions, latch,coinMap,traderMap);
        latch.await();
//        Queries.runQueries(coinMap,traderMap);
    }
}
