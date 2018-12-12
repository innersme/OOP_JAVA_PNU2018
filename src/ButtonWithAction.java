import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonWithAction {
    public static void main(String[] args){
        MyFrame frame = new MyFrame("ButtonWithAction");
    }

    static class MyFrame extends JFrame implements ActionListener {
        public MyFrame(String title){
            setTitle(title);
            setVisible(true);
            setSize(400, 150);

            JPanel panel = new JPanel();
            add(panel);
            JButton button1 = new JButton("Click me1");
            button1.addActionListener(this);
            panel.add(button1);

            JButton button2 = new JButton("click me2");
            button2.addActionListener(this);
            panel.add(button2);
        }

        public void actionPerformed(ActionEvent event){
            System.out.println(event);

            String cmd = event.getActionCommand();

            JOptionPane.showMessageDialog(null, cmd);

        }
    }
}
