public class Student {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    private int year;

    public Student() {
        this.name = "";
        this.year = 0;
    }

    public Student(String name, int year) {
        this.name = name;
        this.year = year;
    }

    @Override
    public String toString() {
        return "["+ name + ", " + year + "학년]";
    }
}
