package model.domain;

/**
 * @author qiyunzhou
 * @date 2018/4/27
 * @time 20:19
 */
public class Student {

  private int id;
  private String studentName;
  private String studentNumber;
  private String gender;
  private int classNumber;

  public Student() {}

  public Student(int id, String studentName, String studentNumber, String gender, int classNumber) {
    this.id = id;
    this.studentName = studentName;
    this.studentNumber = studentNumber;
    this.gender = gender;
    this.classNumber = classNumber;
  }

  public int getClassNumber() {
    return classNumber;
  }

  public void setClassNumber(int classNumber) {
    this.classNumber = classNumber;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }

  public String getGender() {
    return gender;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", studentName='" + studentName + '\'' +
        ", studentNumber='" + studentNumber + '\'' +
        ", gender='" + gender + '\'' +
        ", classNumber=" + classNumber +
        '}';
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

}
