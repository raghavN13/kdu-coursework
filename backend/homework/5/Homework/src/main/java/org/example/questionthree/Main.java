package org.example.questionthree;

import org.example.Logging;

public class Main {
    int number = 8;

    public static void main(String[] args) throws InterruptedException {
        Main shared = new Main();
        Thread threadOne = new Thread(new Factorial(shared));
        Thread threadTwo = new Thread(new Factors(shared));
        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();



        Logging.logInfo("Main thread exiting.");
    }
}
