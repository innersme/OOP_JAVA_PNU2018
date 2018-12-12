import java.awt.event.*;
import javax.swing.*;

public class Lab8 {
    public static void main(String[] args) {
        MyActionFrame frame = new MyActionFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


class MyActionFrame extends JFrame {
    public MyActionFrame() {
        setTitle("Lab8 Swing");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        MyActionPanel panel = new MyActionPanel();
        add(panel);
    }

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
}

class MyActionPanel extends JPanel {
    public MyActionPanel() {

        Action add = new ButtonAction("Add");
        Action removeFirst = new ButtonAction("Remove First");
        Action removeLast = new ButtonAction("Remove Last");
        Action removeAll = new ButtonAction("Remove All");
        Action quit = new ButtonAction("Quit");

        JButton addButton = new JButton(add);
        JButton RemoveFirstButton = new JButton(removeFirst);
        JButton RemoveLastButton = new JButton(removeLast);
        JButton RemoveAllButton = new JButton(removeAll);
        JButton QuitButton = new JButton(quit);
        QuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(addButton);
        add(RemoveFirstButton);
        add(RemoveLastButton);
        add(RemoveAllButton);
        add(QuitButton);
    }

    public class ButtonAction extends AbstractAction {
        public ButtonAction(String name) {
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, "Help for " + name);
        }

        public void actionPerformed(ActionEvent event) {
            System.out.println(event);
            String cmd = event.getActionCommand();
            JOptionPane.showMessageDialog(null, cmd);
        }
    }
}

