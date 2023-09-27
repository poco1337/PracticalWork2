import java.util.ArrayList;

public class Course {
    private final int numberOfCourse;
    private ArrayList<Student> studentsOfCourse;
    public Course(int numberOfCourse) {
        this.numberOfCourse = numberOfCourse;
    }

    @Override
    public String toString() {
        return "курс " +  numberOfCourse;
    }
}
