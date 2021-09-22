package com.company;

public class BallThread extends Thread{
    private final Ball b;
    private final BallThread additionalBallThread;

    public BallThread(Ball ball, int priority){
        b = ball;
        this.setPriority(priority);
        this.additionalBallThread = null;
    }

    public BallThread(Ball ball, int priority, BallThread additionalBallAThread) {
        b = ball;
        this.setPriority(priority);
        this.additionalBallThread = additionalBallAThread;
    }

    @Override
    public void run(){
        try{
            if (this.additionalBallThread != null){
                this.additionalBallThread.join();
            }

            for(int i=1; i<10000; i++){
                b.move();

                if(b.isInCorner() || b.isInSideLoose()){
                    System.out.println(Thread.currentThread().getName() + " interrupted");
                    break;
                }

                //System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        }
        catch(InterruptedException ex){

        }
    }
}
