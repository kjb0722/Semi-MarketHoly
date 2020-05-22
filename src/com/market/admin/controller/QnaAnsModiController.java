package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.admin.dao.QnaAdminDao;
@WebServlet("/admin/qnaModify.do")
public class QnaAnsModiController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnum = Integer.parseInt(req.getParameter("qnum"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		QnaAdminDao qnaDao = QnaAdminDao.getInstance();
		int n = qnaDao.updAns(qnum, title, content);
		
		JSONObject json = new JSONObject();
		json.put("n", n);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
