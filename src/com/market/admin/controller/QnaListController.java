package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.market.admin.dao.QnaAdminDao;
import com.market.admin.dto.QnaAdminDto;

@WebServlet("/admin/qnaList.do")
public class QnaListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String kind = req.getParameter("kind");
		if (kind == null) {
			kind = "";
		}
		String word = req.getParameter("word");
		if (word == null) {
			word = "";
		}

		QnaAdminDao dao = QnaAdminDao.getInstance();
		ArrayList<QnaAdminDto> qnaList = dao.selQnaList(kind, word);

		JSONArray jarr = new JSONArray();
		for (QnaAdminDto dto : qnaList) {
			JSONObject json = new JSONObject();
			json.put("qnum", dto.getQnum());
			json.put("cname", dto.getCname());
			json.put("pname", dto.getPname());
			json.put("title", dto.getTitle());
			json.put("writer", dto.getName());
			json.put("reg_date", dto.getReg_date());
			json.put("content", dto.getContent());
			jarr.put(json);
		}

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jarr);
	}
}
