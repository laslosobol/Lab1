package com.company;

public class Counter implements ICountable {
    private int _counter;

    @Override
    public void increment() {
        ++_counter;
    }

    @Override
    public void decrement() {
        --_counter;
    }

    @Override
    public void print() {
        System.out.println(_counter);
    }
}
