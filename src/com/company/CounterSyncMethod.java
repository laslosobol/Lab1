package com.company;

public class CounterSyncMethod implements ICountable {
    private int _counter;

    public synchronized void increment() {
        ++_counter;
    }

    public synchronized void decrement() {
        --_counter;
    }

    public void print() {
        System.out.println(_counter);
    }
}
