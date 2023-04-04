package com.junuo.servlet.user;

import com.alibaba.fastjson2.JSON;
import com.junuo.pojo.ResponseStatus;
import com.junuo.pojo.User;
import com.junuo.service.UserService;
import com.junuo.utils.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private final UserService userService = new UserService();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("OPTIONS"))
            return;
        ResponseStatus responseStatus = new ResponseStatus();
        resp.setContentType("text/json;charset=utf-8");

        HttpSession session = req.getSession(false);
        String checkCodeGen = (String)session.getAttribute("checkCode");
        String checkCode = req.getParameter("checkCode");

        if(!checkCodeGen.equalsIgnoreCase(checkCode)) {
            responseStatus.setStatusCode(0);
            responseStatus.setMsg("failed");
            responseStatus.setBzText("验证码错误");
            resp.getWriter().write(JSON.toJSONString(responseStatus));
            return;
        }

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        User user = userService.selectByUsernameAndPassword(userName, password);

        if(user == null) {
            responseStatus.setStatusCode(0);
            responseStatus.setMsg("failed");
            responseStatus.setBzText("用户名或密码错误");
            resp.getWriter().write(JSON.toJSONString(responseStatus));
        }
        else {
            session.setAttribute("isLogin", true);
            responseStatus.setStatusCode(1);
            responseStatus.setMsg("success");
            responseStatus.setBzText("登录成功");
            user.setPassword("******");
            responseStatus.setUser(user);
            resp.getWriter().write(JSON.toJSONString(responseStatus));
        }
    }

    public void checkCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(80, 35,os , 4);

        HttpSession session = req.getSession();
        session.setAttribute("checkCode", checkCode);
        resp.getOutputStream().write(os.toByteArray());
    }

    public void isLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = null;
        try{
            session = req.getSession(false);
        }
        catch (Exception e) {
            e.printStackTrace();
//            throw e;
        }
        finally {
            if(session != null) {
                resp.getWriter().write((String)session.getAttribute("isLogin"));
            }
            else {
                resp.getWriter().write("false");
            }
        }
    }

    public void selectAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.selectAll();
        resp.getWriter().write(JSON.toJSONString(users));
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int count = userService.deleteById(id);
        ResponseStatus responseStatus = new ResponseStatus();
        resp.setContentType("text/json;charset=utf-8");
        if(count == 1) {
            responseStatus.setStatusCode(1);
            responseStatus.setMsg("success");
            responseStatus.setBzText("删除成功");
        }
        else {
            responseStatus.setStatusCode(0);
            responseStatus.setMsg("failed");
            responseStatus.setBzText("删除出错");
        }
        resp.getWriter().write(JSON.toJSONString(responseStatus));
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String line = req.getReader().readLine();
        User user = JSON.parseObject(line, User.class);

        int count = userService.insertUser(user);

        ResponseStatus responseStatus = new ResponseStatus();
        resp.setContentType("text/json;charset=utf-8");
        if(count == 1) {
            responseStatus.setStatusCode(1);
            responseStatus.setMsg("success");
            responseStatus.setBzText("添加成功");
        }
        else {
            responseStatus.setStatusCode(0);
            responseStatus.setMsg("failed");
            responseStatus.setBzText("添加失败");
        }
        resp.getWriter().write(JSON.toJSONString(responseStatus));
    }
}
