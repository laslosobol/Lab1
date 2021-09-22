package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private final JLabel scoreTextField = new JLabel();
    private final JTextField blueCountTextField = new JTextField("100", 4);

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");

        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");
        JButton buttonRed = new JButton("Add red ball");
        JButton buttonBlue = new JButton("Add blue ball");
        JButton buttonExperiment = new JButton("Experiment");
        JButton buttonJoin = new JButton("Try Join");

        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas, Color.darkGray, false);
                canvas.add(b);

                BallThread thread = new BallThread(b, 1);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        buttonRed.addActionListener(e -> {
            Ball b = new Ball(canvas, Color.RED, false);
            canvas.add(b);

            BallThread thread = new BallThread(b, 10);
            thread.start();
            System.out.println("Thread name = " +
                    thread.getName());
        });

        buttonBlue.addActionListener(e -> {
            Ball b = new Ball(canvas, Color.BLUE, false);
            canvas.add(b);

            BallThread thread = new BallThread(b, 1);
            thread.start();
            System.out.println("Thread name = " +
                    thread.getName());
        });

        buttonExperiment.addActionListener(e -> {
            LinkedList<BallThread> blueThreads = new LinkedList<>();
            var count = Integer.parseInt(blueCountTextField.getText());
            for (int i = 0; i < count; i++){
                Ball b = new Ball(canvas, Color.BLUE, true);
                canvas.add(b);
                blueThreads.add(new BallThread(b, 1));
            }

            Ball rb = new Ball(canvas, Color.RED, true);
            canvas.add(rb);

            for (BallThread bt: blueThreads){
                bt.start();
            }
            new BallThread(rb, 10).start();
        });

        buttonJoin.addActionListener(e -> {
            Ball b1 = new Ball(canvas, Color.MAGENTA, false);
            Ball b2 = new Ball(canvas, Color.GREEN, false);
            Ball b3 = new Ball(canvas, Color.YELLOW, false);

            canvas.add(b1);
            canvas.add(b2);
            canvas.add(b3);

            BallThread bt1 = new BallThread(b1, Thread.NORM_PRIORITY);
            BallThread bt2 = new BallThread(b2, Thread.NORM_PRIORITY, bt1);
            BallThread bt3 = new BallThread(b3, Thread.NORM_PRIORITY, bt2);

            bt3.start();
            bt2.start();
            bt1.start();
        });


        buttonPanel.add(new JLabel("Blue balls count: "));
        buttonPanel.add(blueCountTextField);
        buttonPanel.add(buttonExperiment);
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonJoin);
        buttonPanel.add(buttonStop);


        JPanel counterPanel = new JPanel();

        JLabel scoreLabel = new JLabel("Score:");
        counterPanel.add(scoreLabel);
        counterPanel.add(scoreTextField);

        content.add(buttonPanel, BorderLayout.SOUTH);
        content.add(counterPanel, BorderLayout.NORTH);

        canvas.score = new Score(scoreTextField);
    }
}
