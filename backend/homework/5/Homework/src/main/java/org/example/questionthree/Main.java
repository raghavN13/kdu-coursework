package org.example.questionthree;

import org.example.Logging;

public class Main {
    int number = 8;

    public static void main(String[] args) {
        Main shared = new Main();
        Thread threadOne = new Thread(new Factorial(shared));
        Thread threadTwo = new Thread(new Factors(shared));
        threadOne.start();
        threadTwo.start();

        try {
            // Wait for both threads to finish
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Main thread interrupted", e);
        }

        Logging.logInfo("Main thread exiting.");
    }
}
