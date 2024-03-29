package com.junuo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求路径
        String uri = req.getRequestURI();
        int index = uri.lastIndexOf('/');
        // 2. 获取方法名
        String methodName = uri.substring(index + 1);

        // 3. 执行方法
//        3.1 获取UserServlet 字节码对象 Class
        Class<? extends BaseServlet> userServletClass = this.getClass();
        // 3.2 获取方法 Method对象
        try {
            Method method = userServletClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 3.3 执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
