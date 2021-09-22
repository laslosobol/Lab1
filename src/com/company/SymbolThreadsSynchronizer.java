package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class SymbolThreadsSynchronizer {
    private AtomicInteger _threadCounter;

    public SymbolThreadsSynchronizer(int initialValue) {
        _threadCounter = new AtomicInteger(initialValue);
    }

    public void leaseThread() {
        do {
            if (_threadCounter.get() <= 0) continue;
            else _threadCounter.decrementAndGet();
            break;
        } while (true);
    }

    public void releaseThread() {
        _threadCounter.incrementAndGet();
    }
}
