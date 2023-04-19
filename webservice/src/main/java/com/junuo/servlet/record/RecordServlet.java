package com.junuo.servlet.record;

import com.alibaba.fastjson.JSON;
import com.junuo.pojo.Record;
import com.junuo.service.RecordService;
import com.junuo.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/record/*")
public class RecordServlet extends BaseServlet {
	private final RecordService recordService = new RecordService();
	public void selectAllByRobotIdAndBeltId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String robotId = req.getParameter("robotId");
		String beltId = req.getParameter("beltId");
		List<Record> records = recordService.selectAllByRobotIdAndBeltId(robotId, beltId);
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(records));
	}

	public void selectOneByRobotIdAndBeltId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String robotId = req.getParameter("robotId");
		String beltId = req.getParameter("beltId");
		String index = req.getParameter("beginIndex");
		int beginIndex = Integer.parseInt(index);
		Record record = recordService.selectOneByRobotIdAndBeltId(robotId, beltId, beginIndex);
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(record));
	}

	public void selectLastByRobotIdAndBeltIdAndRoller(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String robotId = req.getParameter("robotId");
		String beltId = req.getParameter("beltId");
		String rollers = req.getParameter("roller");
		int roller = Integer.parseInt(rollers);
		Record record = recordService.selectLastByRobotIdAndBeltIdAndRoller(robotId, beltId, roller);
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(record));
	}
}
