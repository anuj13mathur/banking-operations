package com.bank;

import com.bank.consumer.tasks.FetchedTask;
import com.bank.operations.ConsumerQueue;

public class Teller {

    private final ConsumerQueue consumerQueue;

    public Teller(ConsumerQueue consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    public FetchedTask fetchTask() {
        return consumerQueue.fetchTask();
    }
}
