package Basic;

public class StringExample {
    public static void main(String[] args){
        String greeting = "Hello";

        // length, charAt
        for(int i = 0 ;  i < greeting.length() ; i++){
            System.out.println(greeting.charAt(i));
        }

        // substring
        String hel = greeting.substring(0, 3);

    }
}
