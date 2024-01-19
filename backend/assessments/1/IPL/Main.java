package org.example.IPL;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Main {
    static Map<String, Player> loadCoinsFromCSV(String filePath) throws IOException{
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> csvData = reader.readAll();
        // Assuming the first row contains headers, start from index 1
        List<Player> coins = Player.fromCsvData(csvData.subList(1, csvData.size()));



        Map<String, Player> coinMap = new HashMap<>();
        for (Player coin : coins) {
            coinMap.put(coin.getName(), coin);
        }

        return coinMap;

    }

    // function to write in the csv file
    static public void  matchMaking(String filePath){
        ArrayList<String>arr = new ArrayList<>();
        arr.add("CSK");
        arr.add("MI");
        arr.add("RCB");
        arr.add("KKR");
        arr.add("SRH");
        arr.add("PBKS");
        arr.add("DC");
        arr.add("RR");

        // every team will play 7 times so total 56 matches

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Match no", "Teams ", "Timing" , "Date" };
            writer.writeNext(header);

            // add data to csv
//            String[] data1 = { "Aman", "10", "620" };
//            writer.writeNext(data1);
//            String[] data2 = { "Suraj", "10", "630" };
//            writer.writeNext(data2);

//            for(int i=1 ;i<=30 ;i++){
                String Teams = "";
                int matchno = 1;

                for(int j=0 ;j< arr.size();j++){
                    for(int k=j+1 ;k<arr.size() ;k+=2){
                        Teams = arr.get(j)+arr.get(j);
                        String MatchCount = String.valueOf(matchno);
                        String Timing = "3:30";
                        String Date = String.valueOf(matchno) + "April";
                        matchno++;




                    }
                }

//            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Map<String,Player>coinMap = loadCoinsFromCSV("src/main/resources/IPL_2021-data.csv");

        methods.caller(coinMap);

        matchMaking("src/main/resources/matches.csv");






    }
}




