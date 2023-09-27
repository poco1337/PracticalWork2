import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String nameOfFaculty;
    private static int countOfFaculties;
    private List<Student> studentsOfFaculty = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    public void addCourses(int numberOfCourses) {
        for(int i = 0; i < numberOfCourses; i++) {
            courses.add(new Course(i));
        }
    }
    public Faculty(String nameOfFaculty) {
        this.nameOfFaculty = nameOfFaculty;
        ControlCenter.faculties.add(this);
    }
    public static int getCountOfFaculties() {
        return countOfFaculties;
    }
    public List<Student> getStudentsList() {
        return studentsOfFaculty;
    }
    public void addStudentToList(Student student) {
        studentsOfFaculty.add(student);
    }
    public String getNameOfFaculty() {
        return nameOfFaculty;
    }
    public List<Course> getCourses() {
        return courses;
    }
    @Override
    public String toString() {
        return "факультет " + nameOfFaculty;
    }
}
