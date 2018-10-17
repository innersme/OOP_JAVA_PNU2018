package Basic;

public class Factorial {
    public static void main(String[] args){
        int values[] = {5, 10, 15};
        for (int i = 0 ; i < values.length ; i++){
            System.out.println("Factorial of " + values[i] + factorial(values[i]));
        }
    }

    public static long factorial(int n){
        long result = 1;
        for (int i = 0; i < n ; i++){
            result *= i;
        }
        return result;
    }
}
