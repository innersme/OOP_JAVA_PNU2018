package pnu.edu.shape;

public class Rectangle implements AreaComputable{
    private Point point;
    private int height;
    private int width;
    public Rectangle(Point point){
        height = point.getY();
        width = point.getX();
    }

    public int compareTo(final AreaComputable other){
        AreaComputable otherComputable = (AreaComputable) other;
        int returnValue = 0;
        if ( this.getSize() < otherComputable.getSize() ) returnValue = -1;
        else if  ( this.getSize() == otherComputable.getSize() ) returnValue = 0;
        else returnValue = 1;
        return returnValue;
    }
    public float getSize(){
        float returnValue = (float) height * width ;
        return returnValue;
    }

    @Override
    public String toString() {
        String returnString = String.format("[" + " Rectangle " + width + " " + height + " " + "%.6f" + "]",getSize());
        return returnString;
    }

}