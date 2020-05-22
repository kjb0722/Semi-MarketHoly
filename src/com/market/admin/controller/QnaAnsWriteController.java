package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.market.admin.dao.QnaAdminDao;
import com.market.member.dto.MemberDto;
import com.market.qna.dto.QnaDto;

@WebServlet("/admin/qnaAnsWrite.do")
public class QnaAnsWriteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");

		QnaAdminDao dao = QnaAdminDao.getInstance();

		int pnum = Integer.parseInt(req.getParameter("pnum"));
		int num = memberDto.getNum();
		int qnum = Integer.parseInt(req.getParameter("qnum"));
		String id = memberDto.getId();
		String name = memberDto.getName();
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		int ref = qnum;
		
		int n = dao.insAns(new QnaDto(pnum, num, qnum, id, name, title, content, ref, null, "N", "N", -1, 0));

		JSONObject json = new JSONObject();
		json.put("n", n);

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
