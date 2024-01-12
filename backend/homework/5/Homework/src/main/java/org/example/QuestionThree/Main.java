package org.example.QuestionThree;

import org.example.logger;

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
            logger.logInfo("Exception Caught");
        }

        logger.logInfo("Main thread exiting.");
    }
}
