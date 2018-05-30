package model.domain;

/**
 * @author qiyunzhou
 * @date 2018/5/21
 * @time 18:49
 */
public class Curriculum {
  private int id;
  private String studentNumber;
  private String courseName;
  private float score;
  private String term ;
  private String courseId;

  @Override
  public String toString() {
    return "Curriculum{" +
        "id=" + id +
        ", studentNumber='" + studentNumber + '\'' +
        ", courseName='" + courseName + '\'' +
        ", score=" + score +
        ", term='" + term + '\'' +
        ", courseId='" + courseId + '\'' +
        '}';
  }

  public Curriculum(int id, String studentNumber, String courseName, float score,
      String term, String courseId) {
    this.id = id;
    this.studentNumber = studentNumber;
    this.courseName = courseName;
    this.score = score;
    this.term = term;
    this.courseId = courseId;
  }

  public Curriculum() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }
}
