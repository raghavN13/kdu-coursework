package org.example.questiontwo;

import org.example.questionone.MessageQueue;
import org.example.questionone.MessageReceiver;
import org.example.questionone.MessageSender;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MessageQueue sharedQueue = new MessageQueue();

        Executor executor = Executors.newFixedThreadPool(6);

        for (int i = 0; i < 3; i++) {
            executor.execute(new MessageSender(sharedQueue));
            executor.execute(new MessageReceiver(sharedQueue));
        }
    }
}
