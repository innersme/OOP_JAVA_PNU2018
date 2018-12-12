import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

public class FileChooserTest {
    public static void main(String[] args){
        ImageViewerFrame frame = new ImageViewerFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ImageViewerFrame extends JFrame {
    public ImageViewerFrame() {
        setTitle("FileChooserTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // set up menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        // use a label to display the images
        label = new JLabel();
        add(label);

        // set up file chooser
        chooser = new JFileChooser();

        // accept all image files ending with .jpg, .jpeg, .gif
        final ExtensionFileFilter filter = new ExtensionFileFilter();
        filter.addExtension("jpg");
        filter.addExtension("jpeg");
        filter.addExtension("gif");
        filter.setDescription("Image files");
        chooser.setFileFilter(filter);

        chooser.setAccessory(new ImagePreviewer(chooser));
        chooser.setFileView(new FileIconView(filter, new ImageIcon("palette.gif")));
    }

    private class FileOpenListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            chooser.setCurrentDirectory(new File("."));
            // show file chooser dialog
            int result = chooser.showOpenDialog(ImageViewerFrame.this);
            // if image file accepted, set it as icon of the label
            if (result == JFileChooser.APPROVE_OPTION){
                String name = chooser.getSelectedFile().getPath();
                label.setIcon(new ImageIcon(name));
            }
        }
    }

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private JFileChooser chooser;
}

class ExtensionFileFilter extends FileFilter{
    public void addExtension(String extension){
        if (!extension.startsWith("."))
            extension = "." + extension;
        extensions.add(extension.toLowerCase());
    }
    public void setDescription(String aDescription) {
        description = aDescription;
    }
    public String getDescription(){
        return description;
    }
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) return true;
        String name = f.getName().toLowerCase();

        for (String extension : extensions){
            if (name.endsWith(extension))
                return true;
        }
        return false;
    }

    private String description = "";
    private ArrayList<String> extensions = new ArrayList<String>();
}

class FileIconView extends FileView {
    /**
     *
     */
    public FileIconView(FileFilter aFilter, Icon anIcon){
        filter = aFilter;
        icon = anIcon;
    }

    public Icon getIcon(File f) {
        if (!f.isDirectory() && filter.accept(f))
            return icon;
        else return null;
    }

    private FileFilter filter;
    private Icon icon;
}

class ImagePreviewer extends JLabel {
    public ImagePreviewer(JFileChooser chooser){
        setPreferredSize(new Dimension(100, 100));
        setBorder(BorderFactory.createEmptyBorder());

        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getPropertyName() ==
                JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
                    File f = (File) event.getNewValue();
                    if (f == null) {
                        setIcon(null);
                        return;
                    }

                    // read the image into an icon
                    ImageIcon icon = new ImageIcon(f.getPath());

                    // if the icon is too large to fit, scale it
                    if (icon.getIconWidth() > getWidth()){
                        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));
                    }

                    setIcon(icon);
                }
            }
        });
    }
}