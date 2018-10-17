import java.util.Arrays;

public class School {
    private String name;
    private int limit;

    private Student[] students;
    private int studentCount;

    public School(String name, int limit) {
        this.name = name;
        this.limit = limit;
        students = new Student[limit];
        studentCount = 0;
    }

    public void addStudent(Student addedStudent){
        students[studentCount++] = new Student(addedStudent.getName(), addedStudent.getYear());
    }

    public Student findStudent(String name, int year){
        Student findedStudent = null;
        for (int i = 0; i < studentCount; i++){
            if ( students[i].getName().equals(name) ){
                if ( students[i].getYear() == year ){
                    findedStudent = new Student(name, year);
                }
            }
        }
        return findedStudent;
    }

    public void removeAllStudent(){
        // students = new Student[limit];
        studentCount = 0;
    }

    @Override
    public String toString() {
        String msg =  "School name: " + name + " Student Count: "
                + studentCount + "\n";
        for (int i = 0 ; i < studentCount ; i++ ){
            msg += "\t" + students[i] + "\n";
        }
        return msg;
    }
}
