package com.company;

public class Main {

    public static void main(String[] args) {
        //Tasker.ballTask();

        //Tasker.symbolsFirstTask();
        //Tasker.symbolsSecondTask();

        for (int i = 0; i<10000; i++){
            //Tasker.counter(new Counter());
            //Tasker.counter(new CounterSyncLock());
             Tasker.counter(new CounterSyncMethod());
            //Tasker.counter(new CounterSyncBlock());
        }
    }
}