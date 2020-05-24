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
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String curPwd = req.getParameter("curPwd");
		String nextPwd = req.getParameter("nextPwd");
		String checkPwd = req.getParameter("checkPwd");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		
	
		MemberDao dao = MemberDao.getInstance();
		
		
		if(curPwd.equals("") || nextPwd.equals("") || checkPwd.equals("")) {
			int n= dao.updateInfo2(id,name,email,phone);	
			MemberDto dto = dao.getDto(id);
			
			HttpSession session = req.getSession();
			session.setAttribute("memberDto", dto);
			resp.sendRedirect(req.getContextPath()+"/main.do");
			
				
		}else {
			dao.updateInfo(id,curPwd,nextPwd,checkPwd,name,email,phone);
			MemberDto dto = dao.getDto(id);
			
			HttpSession session = req.getSession();
			session.setAttribute("memberDto", dto);
			resp.sendRedirect(req.getContextPath()+"/main.do");
		}
		
	}
}
