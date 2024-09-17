package org.example.questionone;

import org.example.Logging;

public class MessageReceiver implements Runnable {
    private final MessageQueue msg;

    public MessageReceiver(MessageQueue msg) {
        this.msg = msg;
    }

    private synchronized void messageReceive() {
        String message = msg.dq.poll();
        Logging.logInfo("Thread " + Thread.currentThread().getId() + " received: " + message);
    }

    @Override
    public void run() {
        messageReceive();
    }
}
