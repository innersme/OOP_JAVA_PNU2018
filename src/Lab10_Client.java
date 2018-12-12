import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*;

public class Lab10_Client {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("Client");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        Container container = this.getContentPane();

        /*
         *  upPanel = TextArea
         *  downPanel = buttons
         *
         *
         *
         *  */
        upPanel = new JPanel();
        downPanel = new JPanel();

        textArea = new JTextArea(40, 60);
        textArea.setText("");
        upPanel.add(textArea);

        Action[] actions = new Action[6];
        actions[0] = new connectButtonAction("Connect");
        actions[1] = new disconnectButtonAction("Disconnect");
        actions[2] = new helloButtonAction("hello World");
        actions[3] = new sendButtonAction("Send");
        actions[4] = new clearButtonAction("Clear");
        actions[5] = new quitButtonAction("Quit");

        JButton[] buttons = new JButton[6];
        for (int i = 0; i < 6;i++){
            buttons[i] = new JButton(actions[i]);
        }

        container.add(upPanel, BorderLayout.NORTH);
        for (int i = 0; i < 6;i++){
            downPanel.add(buttons[i]);
        }
        container.add(downPanel, BorderLayout.SOUTH);
    }


    /* buttonActions
     *
     *
     *
     *  */

    public class connectButtonAction extends AbstractAction {
        public connectButtonAction(String name){
            this.name = name;
            putValue(Action.NAME, name);
        }

        public void actionPerformed(ActionEvent event){
            try {
                sock = new Socket("127.0.0.1", 10001);
                inetaddr = sock.getInetAddress();
                toText = "Try to connect " + inetaddr.getHostAddress() + "\nConnection Established\n";
                out = sock.getOutputStream();
                in = sock.getInputStream();
                pw = new PrintWriter(out, true);
                br = new BufferedReader(new InputStreamReader(in));
                textArea.append(toText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String name;
    }

    public class disconnectButtonAction extends AbstractAction {
        public disconnectButtonAction(String name){
            this.name = name;
            putValue(Action.NAME, name);
        }

        public void actionPerformed(ActionEvent event){
            try {
//                toText = "disConnected";
//                textArea.append(toText);
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String name;
    }

    public class helloButtonAction extends AbstractAction {
        public helloButtonAction(String name){
            this.name = name;
            putValue(Action.NAME, name);
        }

        public void actionPerformed(ActionEvent event){
           toText = hello;
        }
        String name;
    }

    public class sendButtonAction extends AbstractAction {
        public sendButtonAction(String name){
            this.name = name;
            putValue(Action.NAME, name);
        }

        public void actionPerformed(ActionEvent event){
            try {
                /* 보낸 메시지 */
                pw.println(toText);
                toText = "Send: " + toText;
                /* textArea에 추가 */
                textArea.append(toText + '\n');
                /* 받은 메시지 */
                FromText = br.readLine();
                toText = "Receive: " + FromText;
                /* textArea에 추가 */
                textArea.append(toText + '\n');
                /* text 초기화 */
                toText =""; FromText ="";
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        String name;
    }

    public class clearButtonAction extends AbstractAction {
        public clearButtonAction(String name){
            this.name = name;
            putValue(Action.NAME, name);
        }

        public void actionPerformed(ActionEvent event){
            textArea.setText("");
        }
        String name;
    }

    public class quitButtonAction extends AbstractAction {
        public quitButtonAction(String name){
            this.name = name;
            putValue(Action.NAME, name);
        }

        public void actionPerformed(ActionEvent event){
            System.exit(0);
        }
        String name;
    }

    /* 입력할 text */
    String hello = "hello World";
    String FromText = "";
    String toText = "";

    /*  */
    JPanel upPanel = null;
    JPanel downPanel = null;
    JTextArea textArea;

    /* socket */
    Socket sock = null;
    InetAddress inetaddr = null;
    OutputStream out = null;
    InputStream in = null;
    PrintWriter pw = null;
    BufferedReader br = null;
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 800;
}
