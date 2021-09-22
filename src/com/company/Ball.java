package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {
    private BallCanvas canvas;
    private final Color color;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int  y= 0;
    private int dx = 2;
    private int dy = 2;

    public Ball(BallCanvas c, Color color, boolean isPlacedInCenter){
        this.canvas = c;
        this.color = color;

        if (!isPlacedInCenter) {
            if (Math.random() < 0.5) {
                x = new Random().nextInt(this.canvas.getWidth());
                y = 0;
            } else {
                x = 0;
                y = new Random().nextInt(this.canvas.getHeight());
            }
        } else {
            x = this.canvas.getWidth() / 2;
            y = this.canvas.getHeight() / 2;
        }
    }

    public static void f(){
        int a = 0;
    }

    public void draw (Graphics2D g2){
        g2.setColor(this.color);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }

    public void move(){
        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }

        this.canvas.repaint();
    }
    public boolean isInCorner(){
        int sizeFault;
        if (XSIZE >= YSIZE){
            sizeFault = XSIZE/2;
        }
        else{
            sizeFault = YSIZE/2;
        }

        int widthSizeFault = canvas.getWidth() - 3 * sizeFault;
        int heightSizeFault = canvas.getHeight() - 3 * sizeFault;


        if ((this.x <= sizeFault && this.y <= sizeFault)
                || (this.x <= sizeFault && this.y >= heightSizeFault)
                || (this.x >= widthSizeFault && this.y >= heightSizeFault)
                || (this.x >= widthSizeFault && this.y <= sizeFault)){
            canvas.score.incrementValue();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isInSideLoose() {
        if (this.x > 390 && this.x < 410){
            if(this.y > 0 && this.y < 10){
                canvas.score.incrementValue();
                return true;
            }
            else if (this.y > 590 && this.y < 600){
                canvas.score.incrementValue();
                return true;
            }
            else return false;
        }
        else return false;
    }
}
