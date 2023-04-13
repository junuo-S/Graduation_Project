package com.junuo.servlet.belt;

import com.alibaba.fastjson.JSON;
import com.junuo.pojo.Belt;
import com.junuo.pojo.Robot;
import com.junuo.service.BeltService;
import com.junuo.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/belt/*")
public class BeltServlet extends BaseServlet {
	private final BeltService beltService = new BeltService();
	public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Belt> belts = beltService.selectAll();
		resp.getWriter().write(JSON.toJSONString(belts));
	}

	public void getFreeBelts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Belt> freeBelts = beltService.getFreeBelts();
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().write(JSON.toJSONString(freeBelts));
	}
}
