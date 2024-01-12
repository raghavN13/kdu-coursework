package org.example.QuestionThree;

import org.example.logger;

public class Factors implements Runnable {
    private final Main shared;

    Factors(Main shared) {
        this.shared = shared;
    }

    public void findsFactors() {
        // Synchronize access to the shared variable
        synchronized (shared) {
            int value = shared.number;

            logger.logInfo("Factors of " + value + " are: ");

            for (int i = 1; i <= value; ++i) {
                if (value % i == 0) {
                    logger.logInfo(i + " ");
                }
            }
        }
    }

    @Override
    public void run() {
        findsFactors();
    }
}
