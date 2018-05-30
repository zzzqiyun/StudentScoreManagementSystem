package model.domain;

/**
 * @author qiyunzhou
 * @date 2018/4/27
 * @time 20:15
 */
public class User {
 private int id;
 private String userName;
 private String userType;
 private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User(){}

  public User(int id, String userName, String userType, String password) {
    this.id = id;
    this.userName = userName;
    this.userType = userType;
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", userType='" + userType + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }
}
