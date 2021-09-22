package com.company;

public class SyncedSymbol implements Runnable{
    private static final int symbolNum = 50;
    private final char _symbol;
    private final SymbolThreadsSynchronizer _inSymbolThreadsSynchronizer;
    private final SymbolThreadsSynchronizer _outSymbolThreadsSynchronizer;

    public SyncedSymbol(char symbol, SymbolThreadsSynchronizer inSymbolThreadsSynchronizer, SymbolThreadsSynchronizer outSymbolThreadsSynchronizer) {
        _symbol = symbol;
        _inSymbolThreadsSynchronizer = inSymbolThreadsSynchronizer;
        _outSymbolThreadsSynchronizer = outSymbolThreadsSynchronizer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < symbolNum; j++) {
                _inSymbolThreadsSynchronizer.leaseThread();
                System.out.print(_symbol);
                if (true) {
                    System.out.println();
                }
                _outSymbolThreadsSynchronizer.releaseThread();
            }
        }
    }
}
