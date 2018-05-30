package pojo.student2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.domain.Student;
import utils.DBUtils;

/**
 * @author qiyunzhou
 * @date 2018/5/24
 * @time 08:45
 */
public class StudentDao {

  private static String url = "jdbc:mysql://localhost:3306/studentManagementSys?useUnicode=true&characterEncoding=utf-8&";
  private static String userName = "root";
  private static String passworld = "zhouqiyun";
  Scanner scan = new Scanner(System.in);

  //添加学生
  public static void add(Student student) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = "insert into db_student(studentNumber,studentName,gender,classNumber) values(?,?,?,?)";
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, student.getStudentNumber());
      preparedStatement.setString(2, student.getStudentName());
      preparedStatement.setString(3, student.getGender());
      preparedStatement.setInt(4, student.getClassNumber());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
      DBUtils.relaese(preparedStatement, null);
    }
  }

  //添加学生及成绩信息
  public static void addStudent() {
    Student student = new Student();
    Scanner scan = new Scanner(System.in);
    System.out.println("插入学生及其成绩信息：");
    System.out.println("输入学号：");
    String studentNumber = scan.next();
    //先判断学号是否重复
    List<String> studentNumbers = getStudentNumbers();

    for (String s : studentNumbers) {
      Boolean flag = true;
      while (flag) {
        String sn = s;
        if (studentNumber.equals(sn)) {
          System.out.println("学号已经存在，请重新输入学号：");
          studentNumber = scan.next();
        } else {
          flag = false;
        }
      }
    }
    System.out.println("输入姓名：");
    String studentName = scan.next();
    System.out.println("输入性别：");
    String gender = scan.next();
    System.out.println("输入班级：");
    int classNumber = scan.nextInt();
    student.setStudentNumber(studentNumber);
    student.setStudentName(studentName);
    student.setGender(gender);
    student.setClassNumber(classNumber);
    add(student);
  }

  //更新学生
  public void update(String studentNumber) {
    Student student = select(studentNumber);
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    System.out.println(student);
    String sql = "update db_student set studentNumber=?,studentName =?,gender=?,classNumber=? where studentNumber =?";
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      preparedStatement = connection.prepareStatement(sql);
      System.out.println("输入修改信息：");
      System.out.println("学号：");
      String snumber = scan.next();
      student.setStudentNumber(snumber);
      System.out.println("姓名：");
      String sname = scan.next();
      student.setStudentName(sname);
      System.out.println("性别：");
      String sgender = scan.next();
      student.setGender(sgender);
      System.out.println("班级：");
      int classNumber = scan.nextInt();
      student.setClassNumber(classNumber);

      preparedStatement.setString(1, student.getStudentNumber());
      preparedStatement.setString(2, student.getStudentName());
      preparedStatement.setString(3, student.getGender());
      preparedStatement.setInt(4, student.getClassNumber());
      preparedStatement.setString(5, studentNumber);

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
      DBUtils.relaese(preparedStatement, null);
    }
  }

  //删除学生
  //级联删除,当删除学生的时候也会把成绩表中的该学生成绩一起删除
  public void delete(String studentNumber) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement ps= null;
    String sql = "delete from db_student where studentNumber = ?";
    String sql1 = " delete from db_curriculum where studentNumber = ? ";
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, studentNumber);
      ps = connection.prepareStatement(sql1);
      ps.setString(1, studentNumber);
      preparedStatement.executeUpdate();
      ps.executeUpdate();
    } catch (Exception e) {
      //e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
      DBUtils.relaese(preparedStatement, null);
    }
  }

  //查询单个学生
  public static Student select(String sn) {
    String sql = "select id,studentNumber,studentName,gender,classNumber from db_student where studentNumber = ?";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Student student = null;
    ResultSet rs = null;
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, sn);
      rs = preparedStatement.executeQuery();

      student = new Student();
      //返回单个学生
      ResultSetMetaData rsmd = rs.getMetaData();
      while (rs.next()) {

        Integer id = rs.getInt("id");
        String snumber = rs.getString("studentNumber");
        String sname = rs.getString("studentName");
        String gender = rs.getString("gender");
        int classNumber = rs.getInt("classNumber");

        student.setId(id);
        student.setStudentNumber(snumber);
        student.setStudentName(sname);
        student.setGender(gender);
        student.setClassNumber(classNumber);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(connection);
      DBUtils.relaese(preparedStatement, null);
    }
    return student;
  }

  //查询所有的学生
  public static List<Student> selectAll() {
    List<Student> students = new ArrayList<>();
    String sql = "select * from db_student";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = DBUtils.getConnetion(url, userName, passworld);
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String snumber = resultSet.getString(2);
        Student s = select(snumber);
        students.add(s);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBUtils.relaese(preparedStatement, resultSet);
      DBUtils.closeConnection(connection);
    }
    return students;
  }

  //获取所有的学生学号
  public static List<String> getStudentNumbers() {
    List<String> studentNumbers = new ArrayList<>();
    List<Student> students = selectAll();
    java.util.Iterator it = students.iterator();
    while (it.hasNext()) {
      Student s = (Student) it.next();
      String snumber = s.getStudentNumber();
      studentNumbers.add(snumber);
    }
    return studentNumbers;
  }

  //判断学号是否存在
  public boolean isHasStudentNumber(String studentNumber) {
    List<String> snumbers = getStudentNumbers();
    for (String s : snumbers) {
      if (studentNumber.equals(s)) {
        return true;
      }
    }
    return false;
  }

}
