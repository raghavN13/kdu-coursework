package org.example.questionthree;
import org.example.Logging;



public class Factorial implements  Runnable{
    private final Main shared;

    Factorial(Main shared){
        this.shared = shared;
    }

    public void findsFactorial() {
        // Synchronize access to the shared variable
        synchronized (shared) {

            int value = shared.number;
            long fact = 1;
            for (int i = 1; i <= value; i++) {
                fact = fact * i;

            }
            Logging.logInfo("Factorial : ".concat(Long.toString(fact)));
        }
    }

    @Override
    public void run() {
        findsFactorial();
    }
}