import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;




public class BounceClient
{

    public BounceClient()
    {
    }

    public static void main(String args[])
    {
        String host = "localhost";
        BounceClientSocket socket = new BounceClientSocket(host);
        JFrame frame = new BounceClientFrame(socket);
        frame.setDefaultCloseOperation(3);
        frame.setTitle("Bounce Client");
        frame.setVisible(true);
    }
}


class BounceClientFrame extends JFrame implements ActionListener{
    private final BounceClientSocket socket;
    private JTextField txtPort;
    public static final int DEFAULT_WIDTH = 550;
    public static final int DEFAULT_HEIGHT = 80;

    public BounceClientFrame(BounceClientSocket socket)
    {
        this.socket = socket;
        setSize(550, 80);
        JPanel buttonPanel = new JPanel();
        txtPort = new JTextField(4);
        buttonPanel.add(txtPort);
        addButton(buttonPanel, "Connect", this);
        addButton(buttonPanel, "AddBall", this);
        addButton(buttonPanel, "Suspend", this);
        addButton(buttonPanel, "Clear", this);
        addButton(buttonPanel, "Quit", this);
        add(buttonPanel, "South");
    }

    public void addButton(Container c, String title, ActionListener listener)
    {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void actionPerformed(ActionEvent event)
    {
        String cmd = event.getActionCommand();
        System.out.println(cmd);
        if(cmd.equalsIgnoreCase("Connect")) //Connect 누름
        {
            String strPort = txtPort.getText();
            int intPort = Integer.parseInt(strPort);
            System.out.println((new StringBuilder("Try to connect at port ")).append(intPort).toString());		//연결됨출력
            socket.open(intPort);//소켓생성&연결
        }
        else// 다른것들누름
        {
            System.out.println((new StringBuilder("Sending Command to the Server: ")).append(cmd).toString());  	//커멘드출력
            socket.send(cmd);
            if(cmd.equalsIgnoreCase("Quit"))// 소켓종료
            {
                socket.close();
                System.exit(0);
            }
        }
    }

}


class BounceClientSocket
{
    private Socket socket;
    private PrintWriter writer;
    private String host;
    public static int TIMEOUT = 5000;

    public BounceClientSocket(String host)
    {
        this.host = host;
    }

    public void open(int port)
    {
        try
        {
            socket = new Socket();//소켓생성
            socket.connect(new InetSocketAddress(host, port), TIMEOUT);//서버와연결
            writer = new PrintWriter(socket.getOutputStream(), true);//writer == 서버에출력할명령문
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        try
        {
            socket.close();//소켓종료
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void send(String msg)
    {
        writer.println(msg); //서버에명령문 (writer) 출력
    }
}
