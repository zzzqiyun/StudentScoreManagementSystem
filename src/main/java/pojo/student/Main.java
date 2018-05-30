package pojo.student;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author qiyunzhou
 * @date 2018/5/15
 * @time 17:48
 */
public class Main extends ObjectOutputStream {

  //显示菜单
  public static void menu() {
    System.out.println("*********************输入选项进行下一步******************************");
    System.out.println("*********************1、添加学生及其成绩信息**************************");
    System.out.println("*********************2、修改学生及其成绩信息**************************");
    System.out.println("*********************3、删除学生及其成绩信息**************************");
    System.out.println("*********************4、查询全部学生及其成绩信息***********************");
    System.out.println("*********************5、根据学号查询该学生成绩信息**********************");
    System.out.println("*********************0、退出(操作完成后只有选择该步才能保存信息)**********");
  }


  public Main(OutputStream out, File f) throws IOException {
    super(out);
  }

  public Main() throws IOException {
  }

  private static File f = new File("src/student.txt");

  private static Map<String, Object> studentMap = new HashMap<>();
  private static Map<String, Student> allStudent = new HashMap<>();

  //获取操作同一文件的对象
  private static Main newInstance(File file, OutputStream out)
      throws IOException {
    f = file;//本方法最重要的地方：构建文件对象，使两个文件对象属于同一个
    return new Main(out, f);
  }

  //多次打开序列化文件存入对象
  @Override
  protected void writeStreamHeader() throws IOException {
    if (!f.exists() || (f.exists() && f.length() == 0)) {
      super.writeStreamHeader();
    } else {
      super.reset();
    }
  }

  //读取序列化文件获取学生信息，放入名为allStudent的Map中，增删该查都对该Map进行
  public void readfile() {
    Student student = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = new FileInputStream(f);
      ois = new ObjectInputStream(fis);
      while (true) {
        student = (Student) ois.readObject();
        allStudent.put(student.getStudentNumber(), student);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (EOFException e) {
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  //获取学生学号的集合，便于查询
  public List<String> getStudentsNumbers() {
    List<String> studentsNumbers = new ArrayList<>();
    for (Map.Entry<String, Student> map : allStudent.entrySet()) {
      String sn = map.getKey();
      studentsNumbers.add(sn);
    }
    return studentsNumbers;
  }

  public void add() {
    Student student = new Student();
    Scanner scan = new Scanner(System.in);
    System.out.println("插入学生及其成绩信息：");
    System.out.println("输入学号：");
    String studentNumber = scan.next();
    //判断学号是否和已有学生学号相同
    List<String> SNs = new ArrayList<>();
    SNs = getStudentsNumbers();
    for (String str : SNs) {
      Boolean flag = true;
      while (flag) {
        if (studentNumber.equals(str)) {
          System.out.println("学号已经存在，请重新输入学号：");
          studentNumber = scan.next();
        } else {
          flag = false;
        }
      }
    }
    System.out.println("输入姓名：");
    String studentName = scan.next();
    System.out.println("输入课程名：");
    String curriculumName = scan.next();
    System.out.println("输入成绩：");
    float curriculumGrade = scan.nextFloat();
    student.setStudentNumber(studentNumber);
    student.setStudentName(studentName);
    student.setCurriculumName(curriculumName);
    student.setCurriculumGrade(curriculumGrade);
    allStudent.put(student.getStudentNumber(), student);
  }


  //通过学号查询学生信息
  public void searchStudentByStudentNumber(String sname) {
    for (Map.Entry<String, Student> map : allStudent.entrySet()) {
      String sn = map.getKey();
      Student s = map.getValue();
      if (sname.equals(sn)) {
        System.out.println(s);
      }
    }
  }

  //根据学号修改学生信息
  public void modify(String sName) {
    Scanner scan = new Scanner(System.in);
    for (Map.Entry<String, Student> map : allStudent.entrySet()) {
      String sn = map.getKey();
      Student student = map.getValue();
      if (sName.equals(sn)) {
        System.out.println("输入修改信息：");
        System.out.println("学号：");
        String snumber = scan.next();
        student.setStudentNumber(snumber);
        System.out.println("姓名：");
        String sname = scan.next();
        student.setStudentName(sname);
        System.out.println("课程名：");
        String cname = scan.next();
        student.setCurriculumName(cname);
        System.out.println("成绩：");
        float cgrade = scan.nextFloat();
        student.setCurriculumGrade(cgrade);
        //allStudent.put(student.getStudentNumber(),student);
      }
    }
  }

  //查询所有学生信息
  public void searchAllStudent() {
    for (Map.Entry<String, Student> map : allStudent.entrySet()) {
      Student s = map.getValue();
      System.out.println(s);
    }
  }

  //根据学号删除学生信息
  public void delete(String sname) {
    Map<String, Student> tempMap = new HashMap<>();
    for (Map.Entry<String, Student> map : allStudent.entrySet()) {
      String sn = map.getKey();
      Student s = map.getValue();
      if (sname.equals(sn)) {
        System.out.println("删除该学成功,删除后所有的学生信息如下：");
      } else {
        tempMap.put(sn, s);
      }
    }

    allStudent.clear();
    for (Map.Entry<String, Student> m : tempMap.entrySet()) {
      String sn = m.getKey();
      Student s = m.getValue();
      allStudent.put(sn, s);
      System.out.print(sn);
      System.out.println(s);
    }
  }

  //程序结束是把allStudent的所有学生信息序列化回文件
  public void writeInFile() {

    Main main = null;
    OutputStream os = null;
    try {
      os = new FileOutputStream(f);
      main = Main.newInstance(f, os);
      for (Map.Entry<String, Student> map : allStudent.entrySet()) {
        Student s = map.getValue();
        main.writeObject(s);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws Exception {

    Main main = new Main();
    main.readfile();
    main.menu();
    Scanner scan = new Scanner(System.in);
    int chose;
    chose = scan.nextInt();
    while (true) {
      if (chose == 1) {//选择插入学生成绩
        String tag;
        main.add();
        System.out.println("是否继续插入：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          main.menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 2) {//修改学生成绩,考虑如何修改后更新到文件
        System.out.println("输入要修改学生的学号：");
        String name = scan.next();
        main.modify(name);
        String tag;
        System.out.println("是继续修改：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          main.menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 3) {//删除学生成绩
        System.out.println("输入要删除学生的学号：");
        String sname = scan.next();
        main.delete(sname);
        String tag;
        System.out.println("是继续修改：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          main.menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 4) {//查询全部学生成绩
        main.searchAllStudent();
        String tag;
        System.out.println("是否重查：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          main.menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 5) {//根据学生学号查询学生及其成绩信息
        System.out.println("输入要查询学生的学号：");
        String sname = scan.next();
        main.searchStudentByStudentNumber(sname);
        String tag;
        System.out.println("是否重查：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          main.menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 0) {//退出系统
        main.writeInFile();
        break;
      } else {
        System.out.println("输入有误！重新输入：");
        chose = scan.nextInt();
      }
    }
  }
}


