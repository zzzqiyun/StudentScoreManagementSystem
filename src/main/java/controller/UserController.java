package controller;

import com.sun.tools.internal.ws.processor.model.Model;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.naming.Context;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.domain.User;
import service.UserService;

/**
 * @author qiyunzhou
 * @date 2018/5/21
 * @time 19:15
 */
public class UserController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UserService userService = new UserService();
    List<User> users = userService.getUserInfo();
    req.setAttribute("users",users);
    req.getRequestDispatcher("/user.jsp").forward(req,resp);
  }
}
