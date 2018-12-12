import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlafTest {
    public static void main(String[] args){
        PlafFrame frame = new PlafFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class PlafFrame extends JFrame{
    public PlafFrame(){
        setTitle("PlafTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        PlafPanel panel = new PlafPanel();
        add(panel);

    }

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
}

class PlafPanel extends JPanel {
    public PlafPanel(){
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        // javax.swing.UIManager: keeps track of the current look and feel and its defaults

        for (UIManager.LookAndFeelInfo info : infos){
            makeButton(info.getName(), info.getClassName());
        }
    }
    void makeButton(String name, final String plafName){
        JButton button = new JButton(name);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    UIManager.setLookAndFeel(plafName);
                    SwingUtilities.updateComponentTreeUI(PlafPanel.this);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}