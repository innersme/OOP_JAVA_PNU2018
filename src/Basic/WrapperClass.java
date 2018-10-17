package Basic;

public class WrapperClass {
    public static void main(String[] args){
        int i = 10000;

        Integer intergerValue = i;
        int i2 = intergerValue;

        short s = intergerValue.shortValue();
        long l = intergerValue.longValue();

        System.out.println(i);
        System.out.println(intergerValue);
        System.out.println(i2);
        System.out.println(s);
        System.out.println(l);
    }
}
