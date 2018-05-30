package pojo.student2;

import java.util.List;
import java.util.Scanner;
import model.domain.Curriculum;
import model.domain.Student;

/**
 * @author qiyunzhou
 * @date 2018/5/24
 * @time 08:45 增加：增加学生信息；增加学生成绩信息 查询：根据学号查询学生信息；根据学号查询学生成绩；查询所有的学生信息；查询所有的学生成绩；
 * 修改：根据学号修改学生信息；根据学号修改学生成绩 删除：根据学生学号删除学生成绩
 */
public class ControllerMain {

  static StudentDao studentDao = new StudentDao();
  static CurriculumDao curriculumDao = new CurriculumDao();

  public static void main(String[] args) throws Exception {
    menu();
    Scanner scan = new Scanner(System.in);
    int chose;
    chose = scan.nextInt();
    while (true) {
      if (chose == 1) {//选择插入学生成绩
         studentDao.addStudent();
        String tag;
        System.out.println("是否继续插入：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 2) {//修改学生成绩,考虑如何修改后更新到文件
        System.out.println("输入要修改学生的学号：");
        String name = scan.next();
        studentDao.update(name);
        System.out.println("是继续修改：yes继续，任意键退出！");
        String tag;
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 3) {//删除学生成绩
        String tag;
        System.out.println("输入要删除学生的学号：");
        String snumber = scan.next();
        studentDao.delete(snumber);
        System.out.println("是继续删除：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 4) {//查询全部学生成绩
        List<Student> students = studentDao.selectAll();
        for (Student s : students) {
          System.out.println(s);
        }
        String tag;
        System.out.println("是否重查：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 5) {//根据学生学号查询学生及其成绩信息
        System.out.println("输入要查询学生的学号：");
        String snumber = scan.next();
        Student student = studentDao.select(snumber);
        System.out.println(student);
        String tag;
        System.out.println("是否重查：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 6) {//根据学号查询该学生所有成绩信息
        System.out.println("输入学生学号查询学生的所有科目及成绩信息：");
        String snumber = scan.next();
        List<Curriculum> curriculums = curriculumDao.selectAll(snumber);
        for (Curriculum c:curriculums
        ) {
          System.out.println(c);
        }
        String tag;
        System.out.println("是否查询：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 7) {//根据学号和课程号查询该学生某科成绩信息
        System.out.println("输入学生学号和课程名查询该学生某科成绩信息");
        System.out.println("学号：");
        String snumber = scan.next();
        System.out.println("课程名：");
        String cname = scan.next();
        Curriculum curriculum = curriculumDao.getSingleScore(snumber,cname);
        System.out.println(curriculum);
        String tag;
        System.out.println("是否修改：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 8) {//根据学号和课程名删除该学生某科成绩信息
        System.out.println("输入学生学号和课程号来删除该学生某科成绩信息");
        System.out.println("学号：");
        String snumber = scan.next();
        System.out.println("课程名：");
        String cname = scan.next();
        curriculumDao.deleteCurriculumscore(snumber,cname);
        System.out.println("删除成功！");
        String tag;
        System.out.println("是否继续删除：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      } else if (chose == 9) {//添加学生的成绩信息
       Boolean isSuccess = curriculumDao.addCurriculum();
       if(isSuccess){
         System.out.println("添加成功！");
       }
        String tag;
        System.out.println("是否继续添加：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      }
      else if (chose == 10) {//根据学号和课程号修改该学生某科成绩信息
        System.out.println("输入学生学号和课程名修改该学生某科成绩信息");
        System.out.println("学号：");
        String snumber = scan.next();
        System.out.println("课程名：");
        String cname = scan.next();
        boolean isSuccess= curriculumDao.update(snumber,cname);
        if(isSuccess){
          System.out.println("修改成功！");
        }
        String tag;
        System.out.println("是否修改：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      }
      else if (chose == 11) {//根查询所有的课程成绩信息
        List<Curriculum> curriculumList = CurriculumDao.selectAllCurriculum();
        for (Curriculum c: curriculumList) {
          System.out.println(c);
        }
        System.out.println("查询所有的课程成绩信息");
        String tag;
        System.out.println("是否修改：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      }
      else if (chose == 12) {//根据学号删除该学生成绩信息
        System.out.println("输入要删除所有成绩的学生学号：");
        String sn = scan.next();
        boolean isSuccess = curriculumDao.deleteBystudentNumber(sn);
        if(isSuccess){
          System.out.println("删除成功！");
        }
        String tag;
        System.out.println("是否修改：yes继续，任意键退出！");
        tag = scan.next();
        if (!"yes".equals(tag)) {
          menu();
          chose = scan.nextInt();
          continue;
        }
      }

      else if (chose == 0) {//退出系统
        break;
      } else {
        System.out.println("输入有误！重新输入：");
        chose = scan.nextInt();
      }
    }
  }

  //显示菜单
  public static void menu() {
    System.out.println("                      输入选项进行下一步                     ");
    System.out.println("                       学生操作                            ");
    System.out.println("****************1、添加学生信息*****************************");
    System.out.println("****************2、修改学生信息*****************************");
    System.out.println("****************3、删除学生信息*****************************");
    System.out.println("****************4、查询全部学生信息**************************");
    System.out.println("****************5、学号查询该学生信息************************");
    System.out.println("                        成绩操作                           ");
    System.out.println("****************6、学号查询该学生所有成绩信息*****************");
    System.out.println("****************7、学号和课程名查询学生某科成绩信息************");
    System.out.println("****************8、学号和课程号删除该学生某科成绩信息***********");
    System.out.println("****************9、添加学生的成绩信息*************************");
    System.out.println("****************10、学号和课程名修改学生某科成绩信息************");
    System.out.println("****************11、查询所有的成绩信息************************");
    System.out.println("****************12、学号删除该学生的所有成绩信息****************");
    System.out.println("****************0、退出*************************************");
  }
}
