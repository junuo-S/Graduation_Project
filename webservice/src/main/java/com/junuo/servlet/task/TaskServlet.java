package com.junuo.servlet.task;

import com.alibaba.fastjson.JSON;
import com.junuo.pojo.Task;
import com.junuo.service.TaskService;
import com.junuo.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/task/*")
public class TaskServlet extends BaseServlet {
	private final TaskService taskService = new TaskService();

	public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Task> tasks = taskService.selectAll();
		resp.getWriter().write(JSON.toJSONString(tasks));
	}
}
