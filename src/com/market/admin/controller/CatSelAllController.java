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

import com.market.admin.dao.CategoryDao;
import com.market.admin.dto.CategoryListDto;

@WebServlet("/admin/catSelAll.do")
public class CatSelAllController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryDao dao = CategoryDao.getInstance();
		ArrayList<CategoryListDto> catListAll = dao.selListAll();
		
		JSONArray jaar = new JSONArray();
		for(CategoryListDto dto : catListAll) {
			JSONObject json = new JSONObject();
			json.put("cnum", dto.getCnum());
			json.put("name", dto.getName());
			json.put("tnum", dto.getTnum());
			json.put("tname", dto.getTname());
			jaar.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jaar);
	}
}
