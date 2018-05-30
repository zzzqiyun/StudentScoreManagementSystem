package service;

import java.util.List;
import model.dao.UserDao;
import model.domain.User;

/**
 * @author qiyunzhou
 * @date 2018/5/23
 * @time 09:21
 */
public class UserService {

  public List<User> getUserInfo(){
    UserDao user  = new UserDao();
   List<User> users =  user.findUser();
   return  users;
  }

}
