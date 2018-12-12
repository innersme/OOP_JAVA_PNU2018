package Ch9;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Less {
    public static void main(String[] args) throws Exception {
        Process proc = Runtime.getRuntime().exec("ls -l");
        InputStream in = proc.getInputStream();
        OutputStream out = proc.getOutputStream();
        byte buffer[] = new byte[1024];
        int n = -1;
        InputStream fin = null;
        if (args.length > 0) fin = new FileInputStream(args[0]);
        else fin = System.in;
        while ( ( n = fin.read(buffer)) != -1){
            out.write(buffer, 0, n);
        }
        fin.close();
        out.close();
        while ( ( n = in.read(buffer)) != -1)
            System.out.print(new String(buffer, 0, n));
        in.close();
    }
}
