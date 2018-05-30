package model.dao;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.domain.User;
import utils.DBUtils;

/**
 * @author qiyunzhou
 * @date 2018/4/27
 * @time 20:52
 */
public class UserDao {
  private String url ="jdbc:mysql://localhost:3306/studentManagementSys";
  private String userName ="root";
  private String passworld="zhouqiyun";

  PreparedStatement ps = null;
  static ResultSet rs = null;
  Connection connection = null;

  public List<User> findUser() {
    List<User> userList = new ArrayList<>();
    String sql = "select * from db_user";
    try {
      connection = DBUtils.getConnetion(url,userName,passworld);
      ps = connection.prepareStatement(sql);
      rs = ps.executeQuery();

      List<User> usersList = new ArrayList<>();
      while (rs.next()) {
        int id = rs.getInt(1);
        String userName = rs.getString(2);
        String userType = rs.getString(3);
        String password = rs.getString(4);
        User user = new User(id,userName,userType,password);
        userList.add(user);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      DBUtils.closeConnection(connection);
    }
    return userList;
  }

  public void addUser(User user) {
    String str = "insert into db_user(userName,userType) values(?,?)";
    Connection connection = null;
    try {
      connection = DBUtils.getConnetion(url,userName,passworld);
      ps = connection.prepareStatement(str);
      ps.setString(1, user.getUserName());
      ps.setString(2, user.getUserType());
      int i = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      DBUtils.closeConnection(connection);
    }
  }
}
