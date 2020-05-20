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

@WebServlet("/admin/qnaUnanswerList.do")
public class QnaUnanswerListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		QnaAdminDao qnaDao = QnaAdminDao.getInstance();
		ArrayList<QnaAdminDto> qnaList = qnaDao.selUnanswerList();

		JSONArray jarr = new JSONArray();
		for (QnaAdminDto dto : qnaList) {
			JSONObject json = new JSONObject();
			json.put("qnum", dto.getQnum());
			json.put("cname", dto.getCname());
			json.put("pname", dto.getPname());
			json.put("title", dto.getTitle());
			json.put("name", dto.getName());
			json.put("reg_date", dto.getReg_date());
			json.put("content", dto.getContent());
			json.put("pnum", dto.getPnum());
			jarr.put(json);
		}

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jarr);
	}
}
