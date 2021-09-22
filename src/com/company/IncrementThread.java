package com.company;

public class IncrementThread extends Thread{
    private final ICountable _counter;

    public IncrementThread(ICountable counter) {
        _counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            _counter.increment();
        }
    }
}
