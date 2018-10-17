package Basic;

import pnu.edu.shape.Rectangle;

class Rectangle2{
    private int leftTopX, leftTopY;
    private int rightBottomX, rightBottomY;
    private void setLeftTop(int x, int y){
        leftTopX = x; leftTopY = y;
    }
    private void setRightBottom(int x, int y){
        rightBottomX = x; rightBottomY = y;
    }

    public Rectangle2(int x1, int y1, int x2, int y2){
        setLeftTop(x1, y1);
        setRightBottom(x2, y2);
    }

    public int getArea(){
        return ( rightBottomX - leftTopX ) * ( rightBottomY - rightBottomX );
    }

}

public class RectangleTest {
    public static void main(String[] args){
        Rectangle2 r1 = new Rectangle2(0, 0, 50, 50);
        Rectangle2 r2 = new Rectangle2(0, 0, 100, 100);

        System.out.println(r1.getArea());
        System.out.println(r2.getArea());

    }
}
