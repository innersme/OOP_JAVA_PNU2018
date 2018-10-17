/* Lab1_Java_Types */

import java.util.Scanner;

enum Operation { ADD, LIST, SUM, QUIT };

public class ArrayEnum {
    private static Scanner scanner = new Scanner(System.in);
    static int ArrayIndex = 0;
    public static void main(String[] args){
        int Array[] = new int[101];

        boolean chksum = true;

        while( chksum == true ){
            final Operation op = getOperation();
            if ( op == Operation.QUIT )
                chksum = false;
            else if ( op == Operation.ADD )
                Array[ArrayIndex++] = scanner.nextInt();
            else if ( op == Operation.LIST)
                printList(Array);
            else if ( op == Operation.SUM)
                System.out.println(getSum(Array));
        }
    }
    private static Operation getOperation() {
        final String operation = scanner.next() ;
        Operation kind = Operation.QUIT;
        if ( operation.equalsIgnoreCase("ADD"))
            kind = Operation.ADD ;
        else if ( operation.equalsIgnoreCase("SUM"))
            kind = Operation.SUM ;
        else if ( operation.equalsIgnoreCase("LIST"))
            kind = Operation.LIST;
        return kind ;
    }

    private static void printList(int[] values){
        for ( int i = 0; i < ArrayIndex ; i++ )
            System.out.print(values[i] + " ");
        System.out.println();
    }

    private static int getSum(int[] values){
        int returnValue = 0;
        for (int value : values)
            returnValue += value;
        return returnValue;
    }
}
