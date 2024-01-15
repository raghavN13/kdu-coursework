package org.example.Application;
import java.util.ArrayList;
import java.util.*;

public class Trader {
    private String walletAddress;
    private String firstName;
    private String lastName;
    private String phone;

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    private Portfolio portfolio;


    // Constructor
    public Trader(String walletAddress, String firstName, String lastName, String phone) {
        this.walletAddress = walletAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;

        this.portfolio = new Portfolio();

    }

    // Getter methods (you can add more as needed)

    public String getWalletAddress() {
        return walletAddress;
    }



    // Static method to convert CSV data into a list of Trader objects
    static List<Trader> fromCsvData(List<String[]> csvData) {
        List<Trader> traders = new ArrayList<>();

        for (String[] row : csvData) {
            // Assuming the order of columns in the CSV is: walletAddress, firstName, lastName, phone
            String walletAddress = row[4]; // Adjust the index based on your CSV structure
            String firstName = row[1];
            String lastName = row[2];
            String phone = row[3];

            Trader trader = new Trader(walletAddress, firstName, lastName, phone);
            traders.add(trader);
        }

        return traders;
    }
}
