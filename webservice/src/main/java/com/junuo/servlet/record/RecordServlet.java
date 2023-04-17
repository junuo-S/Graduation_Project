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
}
