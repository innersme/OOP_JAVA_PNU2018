
import javax.swing.*;

public class HelloSwingWorld {
    public static void main(String[] args){
        JFrame frame = new JFrame("My First Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 150);

        JPanel panel = new JPanel();
        frame.add(panel);
        JButton button1 = new JButton("click me1");
        panel.add(button1);
        JButton button2 = new JButton("click me2");
        panel.add(button2);
    }
}
