package org.example.IPL;

import java.util.ArrayList;
import java.util.List;
//Name,Team,Role,Matches,Runs,Average,SR,Wickets

public class Player {
    private String name;
    private String Team;
    private String role;
    private int matches;
    private int runs;
    private double average;


    private double strikeRate;
    private int wickets;

    public Player(String name, String team, String role ,int matches, int runs, double average, double strikeRate, int wickets) {
        this.name = name;
        this.role = role;
        Team = team;
        this.matches = matches;
        this.runs = runs;
        this.average = average;
        this.strikeRate = strikeRate;
        this.wickets = wickets;
    }

    public Player() {

    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    static List<Player> fromCsvData(List<String[]> csvData) {
        List<Player> coins = new ArrayList<>();

        for (String[] row : csvData) {
            String name = row[0];

            String team = row[1];

            String role = row[2];

            int  matches = Integer.parseInt(row[3]);

            int runs = Integer.parseInt(row[4]);

            double average = Double.parseDouble(row[5]);

            double strikeRate = Double.parseDouble(row[6]);

            int wickets = Integer.parseInt(row[7]);


            Player player = new Player(name,team,role,matches,runs,average,strikeRate,wickets);
            coins.add(player);

        }

        return coins;
    }
}
