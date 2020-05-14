package com.market.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.member.dao.MemberDao;
import com.market.member.dto.MemberDto;

@WebServlet("/member/updateInfo.do")
public class UpdateInfoController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String curPwd = req.getParameter("curPwd");
		String nextPwd = req.getParameter("nextPwd");
		String checkPwd = req.getParameter("checkPwd");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		

		MemberDao dao = MemberDao.getInstance();
		int n = dao.updateInfo(id,curPwd,nextPwd,checkPwd,name,email,phone);
		
		
		if(n>0) {
			MemberDto dto = dao.login(id, nextPwd);
			
			HttpSession session = req.getSession();
			session.setAttribute("dto", dto);
			
			resp.sendRedirect(req.getContextPath()+"/main.do");	
		}else {
			resp.sendRedirect(req.getContextPath()+"/member/updateResult.jsp");
		}
		
		
		
	}
}
