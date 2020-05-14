package com.market.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.member.dao.MemberDao;

@WebServlet("/member/findId.do")
public class FindIdController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		MemberDao dao = MemberDao.getInstance();
		String id = dao.findId(name,email);

		if(id!=null) {
			req.setAttribute("id", id);	//req.setAttribute있으면 무조권 forward방식
			req.getRequestDispatcher("/index.jsp?page=/member/foundId.jsp").forward(req,resp);	
		}
	}
}
