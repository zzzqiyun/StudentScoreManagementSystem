package pojo.student2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import model.domain.Curriculum;
import utils.DBUtils;

/**
 * @author qiyunzhou
 * @date 2018/5/27
 * @time 20:46
 */
public class CurriculumDao {

  private static String url = "jdbc:mysql://localhost:3306/studentManagementSys?useUnicode=true&characterEncoding=utf-8&";
  private static String userName = "root";
  private static String passworld = "zhouqiyun";
  StudentDao studentDao = new StudentDao();
  Scanner scan = new Scanner(System.in);

  //添加学生的课程及成绩信息
  public void add(Curriculum curriculum) {
    String sql = "Insert into db_curriculum(studentNumber,courseName,score,term,courseId) VALUES (?,?,?,?,?)";
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      ps = connection.prepareStatement(sql);
      ps.setString(1, curriculum.getStudentNumber());
      ps.setString(2, curriculum.getCourseName());
      ps.setFloat(3, curriculum.getScore());
      ps.setString(4, curriculum.getTerm());
      ps.setString(5, curriculum.getCourseId());
      ps.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //通过学生学号查询全部学生成绩信息
  public List<Curriculum> selectAll(String studentNumber) {
    String sql = "select id,studentNumber,courseName,score,term,courseId from db_curriculum where studentNumber = ?";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Curriculum curriculum = null;
    List<Curriculum> curriculums = new ArrayList<>();
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      ps = connection.prepareStatement(sql);
      ps.setString(1, studentNumber);
      rs = ps.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      while (rs.next()) {
        curriculum = new Curriculum();
        int id = rs.getInt("id");
        String sn = rs.getString("studentNumber");
        String cn = rs.getString("courseName");
        float score = rs.getFloat("score");
        String term = rs.getString("term");
        String ci = rs.getString("courseId");

        curriculum.setId(id);
        curriculum.setStudentNumber(sn);
        curriculum.setCourseName(cn);
        curriculum.setScore(score);
        curriculum.setTerm(term);
        curriculum.setCourseId(ci);
        curriculums.add(curriculum);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return curriculums;
  }

  //通过学号和课程名查询学生成绩信息
  public Curriculum getSingleScore(String snumber, String cname) {
    String sql = "select id,studentNumber,courseName,score,term,courseId from db_curriculum where studentNumber = ? and courseName = ?";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Curriculum curriculum = null;
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      ps = connection.prepareStatement(sql);
      ps.setString(1, snumber);
      ps.setString(2, cname);
      rs = ps.executeQuery();
      while (rs.next()) {
        curriculum = new Curriculum();
        int id = rs.getInt("id");
        String studentNumber = rs.getString("studentNumber");
        String courseName = rs.getString("courseName");
        Float score = rs.getFloat("score");
        String term = rs.getString("term");
        String courseId = rs.getString("courseId");
        curriculum.setId(id);
        curriculum.setStudentNumber(studentNumber);
        curriculum.setCourseName(courseName);
        curriculum.setScore(score);
        curriculum.setTerm(term);
        curriculum.setCourseId(courseId);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
    }
    return curriculum;
  }


  //根据学号和课程名删除学生成绩
  public void deleteCurriculumscore(String snumber, String cname) {
    String sql = "delete from db_curriculum where studentNumber = ?  and courseName = ?";
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      ps = connection.prepareStatement(sql);
      ps.setString(1, snumber);
      ps.setString(2, cname);
      ps.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
    }
  }

  //查询所有的课程成绩信息
  public static List<Curriculum> selectAllCurriculum() {
    String sql = "SELECT * from db_curriculum";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Curriculum curriculum = null;
    List<Curriculum> curriculums = new ArrayList<>();
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      ps = connection.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        curriculum = new Curriculum();
        int id = rs.getInt("id");
        String studentNumber = rs.getString("studentNumber");
        String courseName = rs.getString("courseName");
        Float score = rs.getFloat("score");
        String term = rs.getString("term");
        String courseId = rs.getString("courseId");
        curriculum.setId(id);
        curriculum.setStudentNumber(studentNumber);
        curriculum.setCourseName(courseName);
        curriculum.setScore(score);
        curriculum.setTerm(term);
        curriculum.setCourseId(courseId);
        curriculums.add(curriculum);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
    }
    return curriculums;
  }

  //获得所有的课程号
  public static List<String> getCurrculumNames() {
    List<String> curriculumNames = new ArrayList<>();
    List<Curriculum> curriculumList = selectAllCurriculum();
    Iterator iterator = curriculumNames.iterator();
    while (iterator.hasNext()) {
      Curriculum c = (Curriculum) iterator.next();
      String cName = c.getCourseName();
      curriculumNames.add(cName);
    }
    return curriculumNames;
  }

  //根据课程名判断是否含有该课程
  public boolean isHasCurriculum(String curriculumName) {
    List<String> curriculumListNames = getCurrculumNames();
    for (String s : curriculumListNames) {
      if (curriculumName.equals(s)) {
        return true;
      }
    }
    return false;
  }


  //添加课程信息
  public boolean addCurriculum() {
    Curriculum curriculum = new Curriculum();
    System.out.println("输入课程及成绩信息进行添加");
    System.out.println("输入学号：");
    String studentNumber = scan.next();
    if (studentDao.isHasStudentNumber(studentNumber)) {
      System.out.println("输入课程名：");
      String cname = scan.next();
      Boolean flag = true;
      while (flag) {
        if (isHasCurriculum(cname)) {
          System.out.println("该学生的该门课程成绩已经存在，请重新输入课程名：");
          cname = scan.next();
        } else {
          System.out.println("成绩：");
          Float score = scan.nextFloat();
          System.out.println("学期：");
          String term = scan.next();
          System.out.println("课程号：");
          String cid = scan.next();

          curriculum.setStudentNumber(studentNumber);
          curriculum.setCourseName(cname);
          curriculum.setScore(score);
          curriculum.setTerm(term);
          curriculum.setCourseId(cid);
          add(curriculum);
          flag = false;
          return true;
        }
      }
    } else {
      System.out.println("没有该学生，不能添加该学生的成绩！");
    }
    return false;
  }


  //根据学号和课程名修改该学生某科成绩信息
  public boolean update(String studentNumber, String cname) {
    String sql = "update db_curriculum set studentNumber=? ,"
        + "courseName = ?,score = ?,term = ? ,courseId = ? where studentNumber = ? and courseName = ?";
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      ps = connection.prepareStatement(sql);
      Curriculum curriculum = getSingleScore(studentNumber, cname);
      System.out.println(curriculum);
      System.out.println("输入一下信息进行修改");
      System.out.println("课程名：");
      String couseName = scan.next();
      System.out.println("成绩：");
      float score = scan.nextFloat();
      System.out.println("学期：");
      String term = scan.next();
      System.out.println("课程号：");
      String cId = scan.next();
      curriculum.setCourseName(couseName);
      curriculum.setTerm(term);
      curriculum.setCourseId(cId);
      curriculum.setScore(score);

      ps.setString(1, curriculum.getStudentNumber());
      ps.setString(2, couseName);
      ps.setFloat(3, curriculum.getScore());
      ps.setString(4, curriculum.getTerm());
      ps.setString(5, curriculum.getCourseId());
      ps.setString(6, studentNumber);
      ps.setString(7, cname);

      ps.executeUpdate();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
    }
    return false;
  }

  //根据学号删除成绩信息
  public boolean deleteBystudentNumber(String sn) {
    Connection connection = null;
    PreparedStatement ps = null;
    String sql = "delete from db_curriculum where studentNumber = ? ";
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      ps = connection.prepareStatement(sql);
      ps.setString(1, sn);
      ps.executeUpdate();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
    }
    return false;
  }
}
