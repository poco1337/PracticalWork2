import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ControlCenter {

    public static List<Faculty> faculties = new ArrayList<>();
    public static void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }
    public static List<Student> getListOfStudentsByFaculty(String facultyName) {
        for(Faculty faculty: faculties) {
            if(faculty.getNameOfFaculty().equals(facultyName)) {
                return faculty.getStudentsList();
            }
        }
        throw new RuntimeException("Такого факультету не існує");
    }
    public static List<Student> getStudentsFromFacultiesAndCourses() {
        List<Student> students = new ArrayList<>();
        for(Faculty faculty:faculties) {
            for(Student student: faculty.getStudentsList()) {
                students.add(student);
            }
        }
        return students;
    }
    public static List<Student> getStudentsBornAfterYear(int year) {
        List<Student> allStudents = getStudentsFromFacultiesAndCourses();
        List<Student> studentsBornAfterYear =  new ArrayList<>();;
        for(Student student: allStudents) {
            if(year < student.getYearOfBirth()) {
                studentsBornAfterYear.add(student);
            }
        }
        if(studentsBornAfterYear.isEmpty()) {
            throw new RuntimeException("Нема студентів");
        }
        else {
            return studentsBornAfterYear;
        }
    }
    public List<Faculty> getFaculties() {
        return faculties;
    }
    public ControlCenter() {}
}
