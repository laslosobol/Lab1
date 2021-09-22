package com.company;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Score {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final JLabel textField;

    public Score(JLabel textField){
        this.textField = textField;
        textField.setText("00");
    }

    public void incrementValue(){
        textField.setText(String.valueOf(counter.incrementAndGet()));
    }
}
