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
import com.market.admin.dto.StatDayDto;

@WebServlet("/admin/daySell.do")
public class StatDaySellController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		Date startDate = Date.valueOf(year + "-" + month + "-01");
		Date endDate = Date.valueOf(year + "-" + month + "-31");
		
		StatDao statDao = StatDao.getInstance();
		StatDayDto numDto = statDao.selDaySell(startDate, endDate);
		StatDayDto amountDto = statDao.selDayAmount(startDate, endDate);
		
		JSONArray jarr = new JSONArray();
		if (numDto != null) {
			JSONObject json = new JSONObject();
			json.put("day1", numDto.getDay1());
			json.put("day2", numDto.getDay2());
			json.put("day3", numDto.getDay3());
			json.put("day4", numDto.getDay4());
			json.put("day5", numDto.getDay5());
			json.put("day6", numDto.getDay6());
			json.put("day7", numDto.getDay7());
			json.put("day8", numDto.getDay8());
			json.put("day9", numDto.getDay9());
			json.put("day10", numDto.getDay10());
			json.put("day11", numDto.getDay11());
			json.put("day12", numDto.getDay12());
			json.put("day13", numDto.getDay13());
			json.put("day14", numDto.getDay14());
			json.put("day15", numDto.getDay15());
			json.put("day16", numDto.getDay16());
			json.put("day17", numDto.getDay17());
			json.put("day18", numDto.getDay18());
			json.put("day19", numDto.getDay19());
			json.put("day20", numDto.getDay20());
			json.put("day21", numDto.getDay21());
			json.put("day22", numDto.getDay22());
			json.put("day23", numDto.getDay23());
			json.put("day24", numDto.getDay24());
			json.put("day25", numDto.getDay25());
			json.put("day26", numDto.getDay26());
			json.put("day27", numDto.getDay27());
			json.put("day28", numDto.getDay28());
			json.put("day29", numDto.getDay29());
			json.put("day30", numDto.getDay30());
			json.put("day31", numDto.getDay31());
			jarr.put(json);
		}

		if (amountDto != null) {
			JSONObject json = new JSONObject();
			json.put("day1", amountDto.getDay1());
			json.put("day2", amountDto.getDay2());
			json.put("day3", amountDto.getDay3());
			json.put("day4", amountDto.getDay4());
			json.put("day5", amountDto.getDay5());
			json.put("day6", amountDto.getDay6());
			json.put("day7", amountDto.getDay7());
			json.put("day8", amountDto.getDay8());
			json.put("day9", amountDto.getDay9());
			json.put("day10", amountDto.getDay10());
			json.put("day11", amountDto.getDay11());
			json.put("day12", amountDto.getDay12());
			json.put("day13", amountDto.getDay13());
			json.put("day14", amountDto.getDay14());
			json.put("day15", amountDto.getDay15());
			json.put("day16", amountDto.getDay16());
			json.put("day17", amountDto.getDay17());
			json.put("day18", amountDto.getDay18());
			json.put("day19", amountDto.getDay19());
			json.put("day20", amountDto.getDay20());
			json.put("day21", amountDto.getDay21());
			json.put("day22", amountDto.getDay22());
			json.put("day23", amountDto.getDay23());
			json.put("day24", amountDto.getDay24());
			json.put("day25", amountDto.getDay25());
			json.put("day26", amountDto.getDay26());
			json.put("day27", amountDto.getDay27());
			json.put("day28", amountDto.getDay28());
			json.put("day29", amountDto.getDay29());
			json.put("day30", amountDto.getDay30());
			json.put("day31", amountDto.getDay31());
			jarr.put(json);
		}

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jarr);
	}
}
