package Ch9;

import java.io.*;

public class Ls {
    public static void main(String[] args) {
        try {
            String param = "";
            for (int i = 0; i < args.length; i++) {
                param += " " + args[i];
            }
            Process proc = Runtime.getRuntime().exec("cmd /c dir " + param);
            InputStream in = proc.getInputStream();
            byte buffer[] = new byte[1024];
            int n = -1;
            while ((n = in.read(buffer)) != -1 ){
                System.out.print(new String(buffer, 0, n));
            }
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
