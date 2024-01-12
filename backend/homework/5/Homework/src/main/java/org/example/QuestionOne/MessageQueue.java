package org.example.QuestionOne;
import java.util.ArrayDeque;
import java.util.Deque;

public class MessageQueue {
    public Deque<String> dq;
    int value;

    public MessageQueue() {
        this.dq = new ArrayDeque<>();
        this.value = 0;
    }



}
