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
