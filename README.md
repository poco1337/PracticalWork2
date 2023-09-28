«ООП» 
ПРАКТИЧНА РОБОТА №2

Варіант 1
____
Завдання:
```
Студент: Фамілія, Імя, По батькові, Дата народження, Адрес, Телефон, Факультет, Курс. Створити масив об'єктів.
Вивести:
    1.1.Список студентів навчального факультету;
    1.2.Списки студентів для кожного факультету і курсу;
    1.3.Список студентів, що народилися після заданого року.
```
<details>
<summary>Виконання програми</summary>
IntelIj Idea:
  
![image](https://github.com/poco1337/PracticalWork2/assets/98651796/0f7edbb2-fb83-4d00-923f-b0de323c8436)
____
WSL:

![image](https://github.com/poco1337/PracticalWork2/assets/98651796/083b2453-7b42-4309-b25f-ce9313a09f7f)

</details>


# Код
<details>
<summary>Class Student</summary>

```java
import java.time.Year;
public class Student {

  public String firstName;
  public String lastName;
  public String middleName;
  private int yearOfBirth;
  public String address;
  private String phoneNumber;
  public Faculty faculty;
  public Course courseName;

  Student(String firstName, String middleName, String lastName, int yearOfBirth, String address,
      String phoneNumber, Faculty faculty, Course courseName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    setYearOfBirth(yearOfBirth);
    this.address = address;
    setPhoneNumber(phoneNumber);
    this.faculty = faculty;
    this.courseName = courseName;
    faculty.addStudentToList(this);

  }

  @Override
  public String toString() {
    return "Студент: " + middleName + " " + firstName + " " + lastName + "; року народження "
        + yearOfBirth + "; " + address + "; " + phoneNumber + "; " + faculty.toString() + "; "
        + courseName.toString();
  }

  public int getYearOfBirth() {
    return yearOfBirth;
  }

  public void setYearOfBirth(int yearOfBirth) {
    if (Year.now().getValue() - yearOfBirth < 13 || Year.now().getValue() - yearOfBirth >= 100) {
      throw new RuntimeException("Студенту не може бути меньше 13 чи більше 100 років");
    } else {
      this.yearOfBirth = yearOfBirth;
    }

  }

  public void setPhoneNumber(String phoneNumber) {
    boolean isPlusInString = false;

    for (int i = 0; i <= phoneNumber.length() - 1; i++) {
      if (phoneNumber.charAt(i) == '+') {
        isPlusInString = true;
      }
    }

    if (phoneNumber.length() != 12) {
      throw new RuntimeException("кількість символів номеру телефона повинен дорівнювати 12");
    } else {
      if (isPlusInString) {
        this.phoneNumber = "+" + phoneNumber;
      } else {
        this.phoneNumber = phoneNumber;
      }
    }
  }
}
 ```

</details>

<details>
<summary>Class Faculty</summary>
  
```java
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
```

</details>

<details>
<summary>Class Course</summary>
  
```java
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
```
</details>

<details>
<summary>Class ControlCenter</summary>

```java
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
    public ControlCenter() {}
}

```

</details>

<details>
<summary>Class Main</summary>
  
```java
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ControlCenter controlCenter = new ControlCenter();
        System.setProperty("console.encoding", "UTF-16");

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
```
</details>

# Стек та купа
Stack (Стек):
У стеку зберігаються виклики методів (функцій) та локальні змінні цих методів.
Кожен потік (thread) має свій власний стек.
Операції над стеком дуже швидкі, оскільки вони обмежені лише поточним викликом методу.
Стек очищається автоматично після виклику методу.
Інструкції, що взаємодіють зі стеком, включають в себе декларації змінних, виклики методів, передачу параметрів тощо.

Heap (Купа):
У купі зберігається об'єктовий пул, включаючи об'єкти класів та масиви.
Об'єкти в купі можуть існувати довше, ніж поточний метод чи потік.
Звернення до об'єктів у купі потребує більше часу порівняно із стеком, оскільки купа служить для зберігання об'єктів з довшим циклом життя.
Інструкції, що взаємодіють з купою, включають в себе створення нових об'єктів (за допомогою new), роботу з посиланнями на об'єкти, їхнє звільнення тощо.
# Висновок
На даній практичній роботі я практикував написання програми на об'єктно-орієнтованій структурі, використовуючи знання з лекцій. Ці знання допоможуть мені створювати більш складні програми з більш високим рівнем абстракції та повторного використання коду.
