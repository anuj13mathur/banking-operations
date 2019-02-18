package com.bank.operations;

import com.bank.Teller;
import com.bank.consumer.tasks.AccountCreation;
import com.bank.consumer.tasks.FetchedTask;
import com.bank.consumer.tasks.Task;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class BankingOperationsTest {
    private ConsumerQueue consumerQueue;
    private Teller teller;

    @Before
    public void setUp() throws Exception {
        consumerQueue = new ConsumerQueue();
        teller = new Teller(consumerQueue);
    }

    @Test
    public void verifyConsumerQueueIsEmpty() {
        //when-then
        assertThat(consumerQueue.isEmpty(), is(true));
    }

    @Test
    public void verifyConsumerQueueIsNotEmpty() {
        //given
        Task accountCreation = new AccountCreation();

        //when
        consumerQueue.add(accountCreation);

        //then
        assertThat(consumerQueue.isEmpty(), is(false));
    }

    @Test
    public void verifyTellerCanFetchATask() {
        //given
        Task accountCreation = new AccountCreation();
        consumerQueue.add(accountCreation);

        //when
        FetchedTask tryFetch = teller.fetchTask();

        //then
        assertTrue(tryFetch.isSuccess());
        assertThat(consumerQueue.isEmpty(), is(true));
    }

    @Test
    public void verifyTellerDoesNotGetATaskOnFetchingFromAnEmptyConsumerQueue() {
        //when
        FetchedTask fetchedTask = teller.fetchTask();

        //then
        assertTrue(fetchedTask.isSuccess());
        assertThat(fetchedTask.get().isPresent(), is(false));
        assertThat(consumerQueue.isEmpty(), is(true));
    }
}
