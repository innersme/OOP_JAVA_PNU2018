package Lab11;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.net.*;
import java.io.*;


public class Server{

    public static void main(String[] args) {
        JFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class BounceFrame extends JFrame{
    public BounceFrame(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        panel = new BallPanel();
        addBall();
        add2Ball();
        down = new JPanel();
        panelString = Integer.toString(panel.getBallCount()) + "개";
        label = new JLabel(panelString);
        down.add(label);

        add(panel, BorderLayout.CENTER);
        add(down, BorderLayout.SOUTH);
    }

    public void addBall(){
        Ball b = new Ball(0,0);
        panel.add(b);
        Runnable r = new BallRunnable(b, panel);
        Thread t = new Thread(r);
        t.start();
    }

    public void add2Ball(){
        Ball b = new Ball(15,15);
        Ball b2 = new Ball(0,0);

        panel.add(b); panel.add(b2);
        Runnable r = new BallRunnable(b, panel);
        Runnable r2 = new BallRunnable(b2, panel);
        Thread t = new Thread(r);
        Thread t2 = new Thread(r2);
        t.start(); t2.start();
    }

    private String panelString;
    private JLabel label = null;
    private JPanel down = null;
    private BallPanel panel;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 450;
    public static final int SETPS = 1000;
    public static final int DELAY = 3;
}

class BallPanel extends JPanel {
    public void add(Ball b){
        balls.add(b);
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
            g2.drawString(Integer.toString(b.getBallCount()),(int)(b.getX()) , (int)(b.getY()));
        }
    }

    public int getBallCount(){
        return Ball.countSum;
    }

    private ArrayList<Ball> balls = new ArrayList<Ball>();
}

/* runnable */
class BallRunnable implements Runnable {
    public BallRunnable(Ball ball, Component component) {

        this.ball = ball;
        this.component = component;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= STEPS ; i++) {
                ball.move(component.getBounds()); // update the location of the ball
                component.repaint();    // redraw the panel
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private Ball ball;
    private Component component;
    // 100번의 이동과 100ms 의 딜레이
    public static final int STEPS = 100;
    public static final int DELAY = 100;
}

/* 패널에 나타낼 공 class */
class Ball {
    public Ball(double x, double y) {
        this.ballCount = countSum++;
        this.x = x;
        this.y = y;
    }

    public void move(Rectangle2D bounds){
        x += dx;
        y += dy;
        if (x < bounds.getMinX()){
            x = bounds.getMinX();
            dx = -dx;
        }
        if (x + XSIZE >= bounds.getMaxX()){
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY()){
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + YSIZE >= bounds.getMaxY()){
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }

    public Ellipse2D getShape()
    {
        Ellipse2D ret2D = new Ellipse2D.Double(x, y, XSIZE, YSIZE);
        return ret2D;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getBallCount() {
        return ballCount;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    public static int getCountSum() {
        return countSum;
    }

    static int countSum = 1;
    private int ballCount = 0;
    private static final int XSIZE = 25;
    private static final int YSIZE = 25;
    private double x = 0;
    private double y = 0;
    private double dx = 5;
    private double dy = 5;
}