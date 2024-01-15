package org.example.Application;

import java.util.ArrayList;
import java.util.List;

public class Coin {
    private String symbol;
    private int rank;
    private String name;
    private double price;
    private long circulatingSupply;

    public Coin(int rank, String name, String symbol, double price, long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(long circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    static List<Coin> fromCsvData(List<String[]> csvData) {
        List<Coin> coins = new ArrayList<>();

        for (String[] row : csvData) {
            int rank = Integer.parseInt(row[1]);
            String name = row[2];
            String symbol = row[3];
            double price = Double.parseDouble(row[4]);
            long circulatingSupply = Long.parseLong(row[5]);

            Coin coin = new Coin(rank, name, symbol, price, circulatingSupply);
            coins.add(coin);
        }

        return coins;
    }

    public void setPrice(Double newPrice) {
        this.price = newPrice;
    }

    public void setVolume(Long newVolume) {
        this.circulatingSupply += newVolume;
    }
}
