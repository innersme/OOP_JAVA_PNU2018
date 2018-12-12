package Lab11;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Client {
    public static void main(String[] args) {
        JFrame frame = new ClientFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ClientFrame extends JFrame{
    public ClientFrame(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem OpenItem = fileMenu.add(new AbstractAction("Open") {
            public void actionPerformed(ActionEvent event){
                setVisible(true);
            }
        });
        JMenuItem CloseItem = fileMenu.add(new AbstractAction("Close") {
            public void actionPerformed(ActionEvent event){
                setVisible(false);
            }
        });
        JMenuItem ExitItem = fileMenu.add(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu ballMenu = new JMenu("Ball");
        JMenuItem add1Item = ballMenu.add(new AbstractAction("Add 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
//                addBall();
            }
        });

        JMenuItem add2Item = ballMenu.add(new AbstractAction("Add 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
//                add2Ball();
            }
        });
        bar.add(fileMenu);
        bar.add(ballMenu);
        setJMenuBar(bar);
    }

    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 450;
    public static final int SETPS = 1000;
    public static final int DELAY = 3;
}