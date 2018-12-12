import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Component;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Ball
{
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private boolean suspended;

    Ball(double x, double y)
    {//공의출발점과출발방향설정
        this.x = x;
        this.y = y;
        dx = 5;
        dy = 5;
    }

    public void move(Rectangle2D bounds)//벽충돌시방향전환
    {
        x += dx;
        y += dy;
        if(x< bounds.getMinX())
        {
            x = bounds.getMinX();
            dx = -dx;
        }
        if(x + 15D >= bounds.getMaxX())
        {
            x = bounds.getMaxX() - 15D;
            dx = -dx;
        }
        if(y< bounds.getMinY())
        {
            y = bounds.getMinY();
            dy = -dy;
        }
        if(y + 15D >= bounds.getMaxY())
        {
            y = bounds.getMaxY() - 15D;
            dy = -dy;
        }
    }

    public Ellipse2D getShape()
    {
        return new java.awt.geom.Ellipse2D.Double(x, y, 15D, 15D);	//공모양생성
    }

    public boolean isSuspended()
    {
        return suspended;
    }

    public void setSuspended(boolean suspended)
    {
        this.suspended = suspended;
    }
}




// Referenced classes of package finalterm2:
//            Ball

class BallPanel extends JPanel
{
    private ArrayList balls;

    BallPanel()
    {
        balls = new ArrayList();
    }

    public void add(Ball b)
    {
        balls.add(b);
    }

    public void clear()
    {
        balls.clear();
    }

    public void suspend(boolean flag)
    {
        Ball b;
        for(Iterator iterator = balls.iterator(); iterator.hasNext(); b.setSuspended(flag))
            b = (Ball)iterator.next();

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Ball b;
        for(Iterator iterator = balls.iterator(); iterator.hasNext(); g2.fill(b.getShape()))
            b = (Ball)iterator.next();

    }
}



// Referenced classes of package finalterm2:
//            Ball

class BallRunnable implements Runnable{
    private Ball ball;
    private Component component;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;

    public BallRunnable(Ball aBall, Component aComponent)
    {
        ball = aBall;
        component = aComponent;
    }

    public void run()
    {
        do
            try
            {
                if(!ball.isSuspended())
                {
                    ball.move(component.getBounds());
                    component.repaint();
                }
                Thread.sleep(5L);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        while(true);
    }
}




// Referenced classes of package finalterm2:
//            BounceServerFrame, BounceServerSocket

public class BounceServer
{

    public BounceServer()
    {
    }

    public static void main(String args[])
    {
        int port = 5555;
        BounceServerFrame frame = new BounceServerFrame();	//서버 frame 생성
        frame.setDefaultCloseOperation(3);
        frame.setTitle("Bounce Server 2");
        frame.setVisible(true);
        BounceServerSocket socket = new BounceServerSocket(frame);	//서버 Socket 생성
        socket.getReady(port);		//연결, 명령입력, 명령수행, ~종료까지
        frame.setBounceSocket(socket);	//소켓을프레임에삽입
    }
}




// Referenced classes of package finalterm2:
//            BallPanel, BounceServerSocket, Ball, BallRunnable

class BounceServerFrame extends JFrame implements ActionListener
{
    private BounceServerSocket socket;
    private ArrayList threads;
    private boolean suspended;
    private BallPanel panel;
    private JTextField txtMsg;
    private JTextField txtPort;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    void setBounceSocket(BounceServerSocket socket)
    {
        this.socket = socket;
    }

    public BounceServerFrame()
    {
        threads = new ArrayList();
        suspended = false;
        setSize(450, 350);
        setTitle("BounceThread");
        panel = new BallPanel();
        add(panel, "Center");
        JPanel panelButton = new JPanel();
        txtMsg = new JTextField(30);	//입력창생성
        panelButton.add(txtMsg);
        add(panelButton, "South");
    }

    public void actionPerformed(ActionEvent event)
    {
        String strPort = txtPort.getText();
        int intPort = Integer.parseInt(strPort);
        socket.getReady(intPort);
    }

    public void quit()	//종료
    {
        System.exit(0);
    }

    public void suspend(){//일시정지
        suspended = !suspended;
        panel.suspend(suspended);
    }

    public void clear(){//클리어
        Thread t;
        for(Iterator iterator = threads.iterator(); iterator.hasNext(); t.interrupt())
            t = (Thread)iterator.next();

        panel.clear();
    }

    public void addBall(){
        Ball b = new Ball(0,0);
        panel.add(b);
        Runnable r = new BallRunnable(b, panel);
        Thread t = new Thread(r);
        threads.add(t);
        t.start();
    }

    public void printMsg(String msg)	//
    {
        txtMsg.setText(msg);
    }
}


// Referenced classes of package finalterm2:
//            BounceServerFrame

class BounceServerSocket
{
    private BounceServerFrame frame;

    public BounceServerSocket(BounceServerFrame frame)
    {
        this.frame = frame;
    }
    public void getReady(int port){
        ServerSocket s;
        System.out.println((new StringBuilder("Try to be ready at port ")).append(port).toString());	//포트콘솔창에출력
        try {
            s = new ServerSocket(port);
            frame.printMsg((new StringBuilder("Server Socket Ready at ")).append(port).toString());
            // 포트 BounceServer Frame에출력

            Socket incoming;
            incoming = s.accept();//클라이언트수용
            frame.printMsg((new StringBuilder("Client Connected from the remote port ")).append(incoming.getPort()).toString());//수용하였다는메세지BounceServer Frame에출력

            InputStream inStream = incoming.getInputStream();
            Scanner in = new Scanner(inStream);
            for(boolean done = false; !done && in.hasNextLine();){//클라이언트로부터입력받기, 콘솔창에출력하기, 수행하기
                String line = in.nextLine();//명령입력받기
                System.out.println(line);
                String cmd = line.trim();
                frame.printMsg((new StringBuilder("Command Received: ")).append(cmd).toString());//받은명령콘솔창출력하기
                if(cmd.equalsIgnoreCase("Quit")){//종료
                    frame.clear();
                    done = true;
                }
                if(cmd.equalsIgnoreCase("AddBall"))//공추가
                    frame.addBall();
                if(cmd.equalsIgnoreCase("Clear"))	//클리어
                    frame.clear();
                if(cmd.equalsIgnoreCase("Suspend"))//일시정지
                    frame.suspend();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}
