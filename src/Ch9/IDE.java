//package Ch9;
//
//import java.awt.*;
//import java.io.*;
//import javax.swing.*;
//import java.awt.event.*;
//
//public class IDE extends JFrame implements ActionListener {
//    private boolean debug = true;
//    private JFileChooser    fd;
//    private JTextArea       editor, output;
//    private JMenuItem       blank, open, save, compile, run;
//    private String  fileName = "";
//    private String  javac = "/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home";
//    private String  java;
//    private File    pwd;
//    private byte[]  buf;
//
//    public IDE(){
//        super("Java 개발 툴");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(500, 400);
//
//        editor = new JTextArea();
//        editor.setTabSize(2);
//        output = new JTextArea();
//
//        JMenuBar bar = new JMenuBar();
//        JMenu file = new JMenu("File");
//
//    }
//}
