import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonTest {
    public static void main(String[] args){
        ButtonFrame frame = new ButtonFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ButtonFrame extends JFrame{
    public ButtonFrame(){
        setTitle("ButtonTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        ButtonPanel panel = new ButtonPanel();
        add(panel);
    }

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
}

class ButtonPanel extends JPanel implements ActionListener {
    public ButtonPanel(){
        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");

        //add buttons to panel
        add(yellowButton);
        add(blueButton);
        add(redButton);

        // create button actions
        ColorAction yellowAction = new ColorAction(Color.YELLOW);
        ColorAction blueAction = new ColorAction(Color.BLUE);
        ColorAction redAction = new ColorAction(Color.RED);

        yellowButton.addActionListener(this);
        blueButton.addActionListener(this);
        redButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event){
        String command = event.getActionCommand();
        if ( command.equalsIgnoreCase("Yellow")) {
            setBackground(Color.YELLOW);
        }
        else if( command.equalsIgnoreCase("Blue")) {
            setBackground(Color.BLUE);
        }
        else if( command.equalsIgnoreCase("Red")) {
            setBackground(Color.RED);
        }
    }



    private class ColorAction implements ActionListener{
        public ColorAction(Color c) {
            backgroundColor = c;
        }
        public void actionPerformed(ActionEvent event){
            setBackground(backgroundColor);
        }
        private Color backgroundColor;
    }
}



