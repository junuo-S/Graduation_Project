package com.junuo.servlet.robot;

import com.alibaba.fastjson.JSON;
import com.junuo.pojo.Robot;
import com.junuo.service.RobotService;
import com.junuo.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/robot/*")
public class RobotServlet extends BaseServlet {
	private final RobotService robotService = new RobotService();
	public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Robot> robots = robotService.selectAll();
		resp.getWriter().write(JSON.toJSONString(robots));
	}

	public void getFreeRobots(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Robot> freeRobots = robotService.getFreeRobots();
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(freeRobots));
	}

	public void selectByRobotId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String robotId = req.getParameter("robotId");
		Robot robot = robotService.selectByRobotId(robotId);
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(robot));
	}

	public void getBattery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String robotId = req.getParameter("robotId");
		int battery = robotService.getBattery(robotId);
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(battery));
	}
}
