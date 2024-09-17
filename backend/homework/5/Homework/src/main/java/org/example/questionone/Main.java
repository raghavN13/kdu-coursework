package org.example.questionone;

public class Main {

    public static void main(String[] args) {
        MessageQueue sharedQueue = new MessageQueue();

        Thread senderThread1 = new Thread(new MessageSender(sharedQueue));
        Thread senderThread2 = new Thread(new MessageSender(sharedQueue));
        Thread senderThread3 = new Thread(new MessageSender(sharedQueue));
        Thread receiverThread1 = new Thread(new MessageReceiver(sharedQueue));
        Thread receiverThread2 = new Thread(new MessageReceiver(sharedQueue));
        Thread receiverThread3 = new Thread(new MessageReceiver(sharedQueue));


        senderThread1.start();
        senderThread2.start();
        senderThread3.start();
        receiverThread1.start();
        receiverThread2.start();
        receiverThread3.start();
    }

}
