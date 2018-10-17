package pnu.edu.editor;

import java.util.*;
import pnu.edu.shape.*;

enum OperationKind {ADD_C, ADD_R, LIST, CLEAR, SORT_A, SORT_D,
    QUIT, INVALID};

public class EditorTest {
    private static Scanner scanner = new Scanner(System.in);
    private static List<AreaComputable> comparableList =
            new ArrayList<AreaComputable>() ;
    public static void main(String[] args) {
        while ( true ) {
            final OperationKind op = getOperation() ;
            if ( op == OperationKind.QUIT ) {
                System.out.println("Bye") ;
                break;
            }
            if ( op == OperationKind.INVALID ) {
                System.out.println("Invalid Operation!") ;
                continue ;
            }
            switch ( op ) {
                case ADD_R : {
                    final Rexctangle newLine = createRectangle() ;
                    System.out.println(newLine) ;
                    break ;
                }
                case ADD_C : {
                    final Circle newCircle = createCircle() ;
                    System.out.println(newCircle) ;
                    break ;
                }
                case SORT_A:
                    Editor.sortList(comparableList, SortKind.ASCENDING) ;
                    break ;
                case SORT_D:
                    Editor.sortList(comparableList, SortKind.DESCENDING) ;
                    break ;
                case CLEAR:
                    comparableList.clear() ;
                    break ;
                case LIST:
                    System.out.println(comparableList) ;
                    break ;
            }
        }
    }

    private static OperationKind getOperation() {
        System.out.print("Enter Operation String! ") ;
        final String operation = scanner.next() ;
        OperationKind kind = OperationKind.INVALID ;
        if ( operation.equalsIgnoreCase("ADDR"))
            kind = OperationKind.ADD_R ;
        if ( operation.equalsIgnoreCase("ADDC"))
            kind = OperationKind.ADD_C ;
        else if ( operation.equalsIgnoreCase("LIST"))
            kind = OperationKind.LIST ;
        else if ( operation.equalsIgnoreCase("SORTA"))
            kind = OperationKind.SORT_A ;
        else if ( operation.equalsIgnoreCase("SORTD"))
            kind = OperationKind.SORT_D ;
        else if ( operation.equalsIgnoreCase("CLEAR"))
            kind = OperationKind.CLEAR ;
        else if ( operation.equalsIgnoreCase("QUIT"))
            kind = OperationKind.QUIT ;
        return kind ;
    }

    private static Circle createCircle() {
        final int x = scanner.nextInt() ;
        final int y = scanner.nextInt() ;
        final int radius = scanner.nextInt() ;
        final Circle newCircle = new Circle(new Point(x, y), radius) ;
        comparableList.add(newCircle) ;
        return newCircle ;
    }

    private static Rectangle createRectangle() {
        final int x1 = scanner.nextInt() ;
        final int y1 = scanner.nextInt() ;
        final Rectangle newLine = new Rectangle(new Point(x1, y1)) ;
        comparableList.add(newLine) ;
        return newLine ;
    }

}
