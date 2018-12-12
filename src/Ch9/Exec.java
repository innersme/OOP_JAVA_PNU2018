package Ch9;

public class Exec {
    public static void main(String[] args) {
        try {
            // method 1
//            Process proc = Runtime.getRuntime().exec("cmd / c dir");
            // method 2
            Process proc = new ProcessBuilder("cmd", "/c").start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
