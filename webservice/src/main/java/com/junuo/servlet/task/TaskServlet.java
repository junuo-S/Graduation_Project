package com.junuo.servlet.task;

import com.alibaba.fastjson.JSON;
import com.junuo.pojo.ResponseStatus;
import com.junuo.pojo.Task;
import com.junuo.service.RobotService;
import com.junuo.service.TaskService;
import com.junuo.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/task/*")
public class TaskServlet extends BaseServlet {
	private final TaskService taskService = new TaskService();
	private final RobotService robotService = new RobotService();

	public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Task> tasks = taskService.selectAll();
		resp.getWriter().write(JSON.toJSONString(tasks));
	}

	public void setDone(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String ids = req.getParameter("id");
		int id = Integer.parseInt(ids);
		int count = taskService.setDone(id);
		ResponseStatus responseStatus = new ResponseStatus();
		if(count == 1) {
			responseStatus.setStatusCode(1);
			responseStatus.setMsg("success");
			responseStatus.setBzText("已结束任务");
		}
		else {
			responseStatus.setStatusCode(0);
			responseStatus.setMsg("failed");
			responseStatus.setBzText("结束任务出错");
		}

		String robotId = taskService.selectRobotIdById(id);
		robotService.setRunning(robotId, 0);
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(responseStatus));
	}

	public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String ids = req.getParameter("id");
		int id = Integer.parseInt(ids);
		String robotId = taskService.selectRobotIdById(id);
		robotService.setRunning(robotId, 0);

		int count = taskService.deleteById(id);
		ResponseStatus responseStatus = new ResponseStatus();
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
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(responseStatus));
	}

	public void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String line = req.getReader().readLine();
		Task task = JSON.parseObject(line, Task.class);
		int count = taskService.addTask(task);
		ResponseStatus responseStatus = new ResponseStatus();
		if(count == 1) {
			responseStatus.setStatusCode(1);
			responseStatus.setMsg("success");
			responseStatus.setBzText("新增成功");
		}
		else {
			responseStatus.setStatusCode(0);
			responseStatus.setMsg("failed");
			responseStatus.setBzText("新增失败");
		}
		robotService.setRunning(task.getRobotId(), 1);
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(responseStatus));
	}
}
