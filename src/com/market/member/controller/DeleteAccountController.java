package com.market.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.member.dao.MemberDao;

@WebServlet("/member/deleteAccount.do")
public class DeleteAccountController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String curpwd = req.getParameter("curpwd");
		
		MemberDao dao = MemberDao.getInstance();
		int n = dao.delAccount(id);
		
		if(n>0) {
			HttpSession session =req.getSession();
			session.invalidate();
			resp.sendRedirect(req.getContextPath()+"/member/delSucess.jsp");
		}
		
		
		
		
	}
	
	
}
