package com.bank.consumer.tasks;

import io.vavr.control.Try;

import java.util.Optional;

public class FetchedTask {
    private final Try<Optional<Task>> tryFetch;

    public FetchedTask(Try<Optional<Task>> tryFetch) {
        this.tryFetch = tryFetch;
    }

    public boolean isSuccess() {
        return tryFetch.isSuccess();
    }

    public Optional<Task> get() {
        return tryFetch.get();
    }
}
