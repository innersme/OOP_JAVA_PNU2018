package pnu.edu.shape;

public class Circle implements AreaComputable {
    private Point center;
    private int radius;

    public Circle(Point center, int radius){
        this.center = center;
        this.radius = radius;
    }

    public int compareTo(final AreaComputable other){
        AreaComputable othercomputable = (AreaComputable) other;
        int returnValue = 0;
        if ( this.getSize() < othercomputable.getSize() ) returnValue = -1;
        else if  ( this.getSize() == othercomputable.getSize() ) returnValue = 0;
        else returnValue = 1;
        return returnValue;
    }

    public float getSize(){
        float returnValue = radius * radius * (float)Math.PI;
        returnValue = (int)returnValue;
        return (float) returnValue ;
    }

    @Override
    public String toString() {
        String returnString = String.format("[" + " Circle " + center + " " + radius + " " + "%.6f" + "]", getSize());
        return returnString;
    }
}
