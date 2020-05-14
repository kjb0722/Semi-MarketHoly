package com.market.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.member.dao.MemberDao;

@WebServlet("/member/findPwd.do")
public class FindPwdController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		
		MemberDao dao = MemberDao.getInstance();
		String pwd = dao.findPwd(name,id,email);
		
		if(pwd!=null) {
			req.setAttribute("pwd", pwd);	//req.setAttribute있으면 무조권 forward방식
			req.getRequestDispatcher("/index.jsp?page=/member/foundPwd.jsp").forward(req,resp);	
			
		}
		
	}
}
