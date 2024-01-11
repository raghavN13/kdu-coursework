package org.example.question4;

public class Main {
    public static void main(String[] args) {
        SetDemo.treeSetDemo(null);

        // Using PubDateAscComparator

        SetDemo.treeSetDemo(new PubDateAscComparator());

        // Using PubDateDescComparator
        SetDemo.treeSetDemo(new PubDateDescComparator());
    }
}
