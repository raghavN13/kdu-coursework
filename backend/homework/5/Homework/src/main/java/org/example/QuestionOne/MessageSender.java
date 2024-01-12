package org.example.QuestionOne;

public class MessageSender implements Runnable {
    private final MessageQueue msg;

    public MessageSender(MessageQueue msg) {
        this.msg = msg;
    }



    public void messageSend() {
        String temp = String.valueOf(msg.value++);
        msg.dq.add(temp);
    }

    @Override
    public void run() {
        messageSend();
    }
}