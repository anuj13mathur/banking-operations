package com.bank.operations;

import com.bank.consumer.tasks.FetchedTask;
import com.bank.consumer.tasks.Task;
import io.vavr.control.Try;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.util.Optional.ofNullable;

public class ConsumerQueue {
    final BlockingQueue queue = new ArrayBlockingQueue(100);

    boolean isEmpty() {
        return queue.isEmpty();
    }

    void add(Task accountCreation) {
        queue.add(accountCreation);
    }

    public FetchedTask fetchTask() {
        return new FetchedTask(buildTryForFetchedTask());
    }

    private Try<Optional<Task>> buildTryForFetchedTask() {
        return Try.of(() -> ofNullable((Task) queue.poll(1, TimeUnit.SECONDS)));
    }
}
