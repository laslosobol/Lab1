package com.company;

import javax.swing.*;

public class Tasker {
    public static void ballTask(){
        BounceFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Thread name = " + Thread.currentThread().getName());
    }

    public static void symbolsFirstTask(){
        Thread symbolNoSyncThread1 = new Thread(new NoThreadSyncSymbol('-'));
        Thread symbolNoSyncThread2 = new Thread(new NoThreadSyncSymbol('|'));
        symbolNoSyncThread1.start();
        symbolNoSyncThread2.start();
    }

    public static void symbolsSecondTask(){
        SymbolThreadsSynchronizer synchronizer1 = new SymbolThreadsSynchronizer(1);
        SymbolThreadsSynchronizer synchronizer2 = new SymbolThreadsSynchronizer(0);

        Thread symbolWithSyncThread1 = new Thread(new SyncedSymbol('-', synchronizer1, synchronizer2));
        Thread symbolWithSyncThread2 = new Thread(new SyncedSymbol('|', synchronizer2, synchronizer1));

        symbolWithSyncThread1.start();
        symbolWithSyncThread2.start();
    }

    public static void counter(ICountable counter){
        IncrementThread incrementThread = new IncrementThread(counter);
        DecrementThread decrementThread = new DecrementThread(counter);

        incrementThread.start();
        decrementThread.start();
        counter.print();
    }
}
