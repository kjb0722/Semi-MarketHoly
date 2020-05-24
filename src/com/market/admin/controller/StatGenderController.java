package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.market.admin.dao.StatDao;
import com.market.admin.dto.StatGenderDto;

@WebServlet("/admin/genderStat.do")
public class StatGenderController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StatDao statDao = StatDao.getInstance();
		ArrayList<StatGenderDto> statDto = statDao.SelGender();
		
		JSONArray jarr = new JSONArray();
		for(StatGenderDto dto : statDto) {
			JSONObject json = new JSONObject();
			json.put("gender",dto.getGender());
			json.put("cnt", dto.getCnt());
			jarr.put(json);
		}
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jarr);
	}
}
