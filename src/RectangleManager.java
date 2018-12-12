import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RectangleManager {
    private ArrayList<MyRectangle> rectangles = new ArrayList<MyRectangle>();
    Scanner scannerObject;

    RectangleManager(Scanner scannerObject){
        this.scannerObject = scannerObject;
    }

    public static void main(String[] args){
        Scanner scannerObject = new Scanner(System.in);
        RectangleManager manager = new RectangleManager(scannerObject);
        while ( true ) {
            System.out.println("Enter a command: [create width height, zoom id ratio or quit]");
            final String command = scannerObject.next();
            if (command.equalsIgnoreCase("create")) { manager.create(); }
            else if (command.equalsIgnoreCase("zoom")) { manager.zoom(); }
            else if (command.equalsIgnoreCase("showAll")) { manager.showAll(); }
            else if (command.equalsIgnoreCase("quit")) { System.out.println("Bye"); break; }
        }

    }

    public void create(){
        try{
            final int width = scannerObject.nextInt();
            final int height = scannerObject.nextInt();
            if (height < 0 || width < 0) throw new InvalidRectangleException(width, height);
            final MyRectangle newRectangle = new MyRectangle(width, height);
            rectangles.add(newRectangle);
            System.out.print(newRectangle);
            System.out.println(" is added at " + (rectangles.size()-1));
        } catch (InvalidRectangleException e){

        } catch (java.util.InputMismatchException e){
            System.out.println("입력된 인자의 형식이 맞지 않습니다.");
        }

    }

    public void zoom() {
        try{
            final int id = scannerObject.nextInt();
            final int ratio = scannerObject.nextInt();
            System.out.println("Before: " + rectangles.get(id));
            rectangles.get(id).setHeight(rectangles.get(id).getHeight() * ratio);
            rectangles.get(id).setWidth(rectangles.get(id).getWidth() * ratio);
            System.out.println("After: " + rectangles.get(id));
        } catch (java.lang.IndexOutOfBoundsException e){
            System.out.println("존재하지 않는 배열의 원소를 접근했습니다." + e);
        }

    }

    public void showAll(){
        for (int i = 0; i < rectangles.size(); i++){
            System.out.println(rectangles.get(i));
        }
    }
}

class InvalidRectangleException extends Exception{
    private int width, height;
    public InvalidRectangleException(int width, int height){
        this.height = height;
        this.width = width;
        System.out.println("사각형의 넓이와 높이는 양수이어야 합니다. " + getWidth() + " " + getHeight());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

