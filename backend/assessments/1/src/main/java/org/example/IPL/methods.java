package org.example.IPL;

import java.util.*;
import java.util.stream.Collectors;

public class methods {

    public static List<Player> getTop3WicketTakers(Map<String, Player> coinMap) {
        return coinMap.values().stream()
                .sorted(Comparator.comparingDouble(Player::getWickets).reversed())
                .limit(7)
                .collect(Collectors.toList());
    }
    public static List<Player> getTop3RunsTakers(Map<String, Player> coinMap) {
        return coinMap.values().stream()
                .sorted(Comparator.comparingDouble(Player::getRuns).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    public static String higestWicketTaker(Map<String,Player>coinMap , String s) {

        String name="";
        int wickets = 0;

        for (Map.Entry<String, Player> entry : coinMap.entrySet()) {
            if (entry.getValue().getTeam().equals(s)) {
                if (entry.getValue().getWickets() > wickets) {
                    name = entry.getValue().getName();
                    wickets = entry.getValue().getWickets();
                }
            }


        }
        return name;
    }
    public static List<String> fortyWickets(Map<String,Player>coinMap , String s) {
        ArrayList<String> arr = new ArrayList<>();

        String name="";
        int wickets = 0;

        for (Map.Entry<String, Player> entry : coinMap.entrySet()) {
            if (entry.getValue().getTeam().equals(s)) {
                if (entry.getValue().getWickets() > 40) {
                    name = entry.getValue().getName();
                    arr.add(name);
                }
            }


        }
        return arr;
    }

    public static String higestRunTaker(Map<String,Player>coinMap , String s) {

        String name="";
        int runs = 0;

        for (Map.Entry<String, Player> entry : coinMap.entrySet()) {
            if (entry.getValue().getTeam().equals(s)) {
                if (entry.getValue().getRuns() > runs) {
                    name = entry.getValue().getName();
                    runs = entry.getValue().getRuns();
                }
            }


        }
        return name;
    }


    public static void caller(Map<String, Player> coinMap){

        Log.customLogger("Enter the option That you want 1-> get bowlers with 40 wickets , 2->Highest Run Taker for a team  ,3->Highest Wicket Taker for a team,4 -> Top 3 run getters 5--> top 3 wicket Takers","INFO");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice==1){
            Log.customLogger("Give the Name of the team","INFO");
            String team = sc.next();

            List<String>Bowlers = fortyWickets(coinMap,team);


            System.out.println(Bowlers);
        }

        if(choice==2){
            Log.customLogger("Give the Name of the team","INFO");
            String team = sc.next();

            String ans = higestRunTaker(coinMap,team);

            Log.customLogger(ans,"INFO");



        }
        if(choice==3){
            Log.customLogger("Give the Name of the team","INFO");
            String team = sc.next();

            String ans = higestWicketTaker(coinMap,team);

            Log.customLogger(ans,"INFO");

        }
        if(choice==4){
            List<Player>arr= getTop3RunsTakers(coinMap);

            Log.customLogger(arr.get(0).getName() + " " + arr.get(0).getRuns(),"INFO");
            Log.customLogger(arr.get(1).getName() + " " + arr.get(1).getRuns(),"INFO");
            Log.customLogger(arr.get(2).getName() + " " + arr.get(2).getRuns(),"INFO");

        }

        if(choice==5){
            List<Player>arr= getTop3RunsTakers(coinMap);

            Log.customLogger(arr.get(0).getName() + " " + arr.get(0).getWickets(),"INFO");
            Log.customLogger(arr.get(1).getName() + " " + arr.get(1).getWickets(),"INFO");
            Log.customLogger(arr.get(2).getName() + " " + arr.get(2).getWickets(),"INFO");

        }

    }


}
