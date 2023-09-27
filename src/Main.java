import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ControlCenter controlCenter = new ControlCenter();

        Faculty programming = new Faculty("Інженерія програмного забеспечення");
        Faculty economy = new Faculty("Економіка");
        Faculty psychology = new Faculty("Психологія");

        Course firstCourse = new Course(1);
        Course secondCourse = new Course(2);
        Course thirdCourse = new Course(3);
        Course fourthCourse = new Course(4);


        Student student1 = new Student("Олена", "Сергіївна", "Івакуренко", 2006, " вул. Занковецька, 14", "380501311212", programming, firstCourse);
        Student student2 = new Student("Григор", "Олександрович", "Івакур", 2005, " вул. Занковецька, 15", "380501111111", economy, secondCourse);
        Student student3 = new Student("Олег", "Михайлович", "Логіненко", 2004, " вул. Занковецька, 16", "380502222222", economy, thirdCourse);
        Student student4 = new Student("Іванка", "Сергіївна", "Івакуренко", 2003, " вул. Занковецька, 17", "380503333333", psychology, fourthCourse);
        Student student5 = new Student("Олександр", "Андрійович", "Івакуренко", 2006, " вул. Верещагіна, 1", "380504444444", psychology, firstCourse);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть факультет, учнів яких ви хочете побачити:");
        String input = scanner.nextLine();

        for(Student student:ControlCenter.getListOfStudentsByFaculty(input)) {
            System.out.println(student.toString());
        }

        System.out.println("\nСписок усіх студентів та курсів:");
        for(Student student:ControlCenter.getStudentsFromFacultiesAndCourses()) {
            System.out.println(student.toString());
        }

        System.out.println("\nВведіть рік народження, щоб показати студентів, які народилися вище заданого:");
        input = scanner.nextLine();
        for(Student student:ControlCenter.getStudentsFromFacultiesAndCourses()) {
            if(student.getYearOfBirth() > Integer.parseInt(input)) {
                System.out.println(student.toString());
            }
        }
        scanner.close();
    }
}