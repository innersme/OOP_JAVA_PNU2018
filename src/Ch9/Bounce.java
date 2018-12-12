//package Ch9;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.*;
//import java.util.*;
//import javax.swing.*;
//
//public class Bounce {
//    public static void main(String[] args) {
//        JFrame frame = new BounceFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//}
//
//class BallRunnable implements Runnable {
//    public BallRunnable(Ball ball, Component component) {
//        this.ball = ball;
//        this.component = component;
//    }
//
//    @Override
//    public void run() {
//        try {
//            for (int i = 1; i <= STEPS ; i++) {
//                ball.move(component.getBounds()); // update the location of the ball
//                component.repaint();    // redraw the panel
//                Thread.sleep(DELAY);
//            }
//        } catch (InterruptedException e){
//
//        }
//    }
//    private Ball ball;
//    private Component component;
//    public static final int STEPS = 1000;
//    public static final int DELAY = 5;
//}
//
//
//class Ball {
//    public void move(Rectangle2D bounds){
//        x += dx;
//        y += dy;
//        if (x < bounds.getMinX()){
//            x = bounds.getMinX();
//            dx = -dx;
//        }
//        if (x + XSIZE >= bounds.getMaxX()){
//            x = bounds.getMaxX() - XSIZE;
//            dx = -dx;
//        }
//        if (y < bounds.getMinY()){
//            y = bounds.getMinY();
//            dy = -dy;
//        }
//        if (y + YSIZE >= bounds.getMaxY()){
//            y = bounds.getMaxY() - YSIZE;
//            dy = -dy;
//        }
//    }
//    public Ellipse2D getShape()
//    {
//        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
//    }
//
//    public void setDx(double dx) {
//        this.dx = dx;
//    }
//
//    public void setDy(double dy) {
//        this.dy = dy;
//    }
//
//    public double getDx() {
//        return dx;
//    }
//
//    public double getDy() {
//        return dy;
//    }
//
//    private static final int XSIZE = 15;
//    private static final int YSIZE = 15;
//    private double x = 0;
//    private double y = 0;
//    private double dx = 1;
//    private double dy = 1;
//}
//
//class BallPanel extends JPanel {
//    public void add(Ball b){
//        balls.add(b);
//    }
//
//    public void paintComponent (Graphics g){
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        for (Ball b : balls) {
//            g2.fill(b.getShape());
//        }
//    }
//    private ArrayList<Ball> balls = new ArrayList<Ball>();
//}
//
//class BounceFrame extends JFrame{
//    public BounceFrame(){
//        fasterClicked = 0;
//        slowerClicked = 0;
//        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//        setTitle("BounceThread");
//
//        JMenuBar bar = new JMenuBar();
//        JMenu speedMenu = new JMenu("Speed");
//        JMenuItem Faster = speedMenu.add(new AbstractAction("Faster") {
//            public void actionPerformed(ActionEvent event){
//                fasterClicked++;
//            }
//        });
//        JMenuItem Slower = speedMenu.add(new AbstractAction("Slower") {
//            public void actionPerformed(ActionEvent event){
//                slowerClicked++;
//            }
//        });
//        bar.add(speedMenu);
//        setJMenuBar(bar);
//
//        panel = new BallPanel();
//        add(panel, BorderLayout.CENTER);
//        JPanel buttonPanel = new JPanel();
//        addButton(buttonPanel, "Add 1", new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addBall();
//            }
//        });
//
//        addButton(buttonPanel, "Add 2", new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                add2Ball();
//            }
//        });
//
//        addButton(buttonPanel, "Close", new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//
//        add(buttonPanel, BorderLayout.SOUTH);
//    }
//
//    public void addButton(Container c, String title, ActionListener listener){
//        JButton button = new JButton(title);
//        c.add(button);
//        button.addActionListener(listener);
//    }
//
//    public void addBall(){
//
//        Ball b = new Ball();
//        if (fasterClicked != 0 || slowerClicked != 0){
//            b.setDx(b.getDx() * Math.pow(2, fasterClicked-slowerClicked ));
//            b.setDy(b.getDy() * Math.pow(2, fasterClicked-slowerClicked ));
//        }
//        System.out.println(b.getDx() + " " + b.getDy());
//        panel.add(b);
//        Runnable r = new BallRunnable(b, panel);
//        Thread t = new Thread(r);
//        t.start();
//    }
//
//    public void add2Ball(){
//        Ball b = new Ball();
//        Ball b2 = new Ball();
//        if (fasterClicked != 0 || slowerClicked != 0) {
//            b.setDx(b.getDx() * Math.pow(2, fasterClicked-slowerClicked ));
//            b.setDy(b.getDy() * Math.pow(2, fasterClicked-slowerClicked ));
//            b2.setDx(b2.getDx() * Math.pow(2, fasterClicked-slowerClicked ));
//            b2.setDy(b2.getDy() * Math.pow(2, fasterClicked-slowerClicked ));
//        }
//
//        panel.add(b); panel.add(b2);
//        Runnable r = new BallRunnable(b, panel);
//        Runnable r2 = new BallRunnable(b2, panel);
//        Thread t = new Thread(r);
//        Thread t2 = new Thread(r2);
//        t.start(); t2.start();
//    }
//
//    private BallPanel panel;
//    public static final int DEFAULT_WIDTH = 450;
//    public static final int DEFAULT_HEIGHT = 450;
//    public static final int SETPS = 1000;
//    public static final int DELAY = 3;
//    private int fasterClicked;
//    private int slowerClicked;
//}
//
////class