package org.example.Assignment;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    public Map<String, Long> coinQuantities;  // Map to store the quantity of each coin
    public double totalProfitLoss;

    public Portfolio() {
        this.coinQuantities = new HashMap<>();
        this.totalProfitLoss = 0.0;
    }

}
