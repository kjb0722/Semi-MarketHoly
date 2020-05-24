package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.market.admin.dao.StatDao;
import com.market.admin.dto.StatMonDto;

@WebServlet("/admin/monthSell.do")
public class StatMonSellController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String year = req.getParameter("year");
		Date startDate = Date.valueOf(year + "-01-01");
		Date endDate = Date.valueOf(year + "-12-31");

		StatDao statDao = StatDao.getInstance();
		StatMonDto numDto = statDao.selMonSell(startDate, endDate);
		StatMonDto amountDto = statDao.selMonAmount(startDate, endDate);

		JSONArray jarr = new JSONArray();
		if (numDto != null) {
			JSONObject json = new JSONObject();
			json.put("jan", numDto.getJan());
			json.put("feb", numDto.getFeb());
			json.put("mar", numDto.getMar());
			json.put("apr", numDto.getApr());
			json.put("may", numDto.getMay());
			json.put("jun", numDto.getJun());
			json.put("jul", numDto.getJul());
			json.put("aug", numDto.getAug());
			json.put("sep", numDto.getSep());
			json.put("oct", numDto.getOct());
			json.put("nov", numDto.getNov());
			json.put("dec", numDto.getDec());
			jarr.put(json);
		}

		if (amountDto != null) {
			JSONObject json = new JSONObject();
			json.put("jan", amountDto.getJan());
			json.put("feb", amountDto.getFeb());
			json.put("mar", amountDto.getMar());
			json.put("apr", amountDto.getApr());
			json.put("may", amountDto.getMay());
			json.put("jun", amountDto.getJun());
			json.put("jul", amountDto.getJul());
			json.put("aug", amountDto.getAug());
			json.put("sep", amountDto.getSep());
			json.put("oct", amountDto.getOct());
			json.put("nov", amountDto.getNov());
			json.put("dec", amountDto.getDec());
			jarr.put(json);
		}

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jarr);
	}
}
