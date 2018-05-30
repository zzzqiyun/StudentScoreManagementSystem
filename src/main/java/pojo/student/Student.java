package pojo.student;

import java.io.Serializable;

/**
 * @author qiyunzhou
 * @date 2018/5/15
 * @time 17:41
 */
public class Student implements Serializable {
  private String studentNumber;
  private String studentName;
  private String  curriculumName;
  private float   curriculumGrade;

  public  Student(){}

  public Student(String studentNumber, String studentName, String curriculumName,
      float curriculumGrade) {
    this.studentNumber = studentNumber;
    this.studentName = studentName;
    this.curriculumName = curriculumName;
    this.curriculumGrade = curriculumGrade;
  }

  public String getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getCurriculumName() {
    return curriculumName;
  }

  public void setCurriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
  }

  public float getCurriculumGrade() {
    return curriculumGrade;
  }

  public void setCurriculumGrade(float curriculumGrade) {
    this.curriculumGrade = curriculumGrade;
  }

  @Override
  public String toString() {
    return "Student{" +
        "studentNumber='" + studentNumber + '\'' +
        ", studentName='" + studentName + '\'' +
        ", curriculumName='" + curriculumName + '\'' +
        ", curriculumGrade=" + curriculumGrade +
        '}';
  }
}
