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

@WebServlet("/member/checkPwd.do")
public class CheckPwdController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = dao.intoChangeInfo(id,pwd);
		
	
		if(dto.getId() != null) {
			req.setAttribute("dto", dto);	
			req.getRequestDispatcher("/index.jsp?page=mypage/mypage.jsp&mypage=member/changeInfo.jsp").forward(req,resp);	
			
		
		}else if(dto.getId() == null){
			resp.sendRedirect(req.getContextPath()+"/member/checkPwdResult.jsp");
		}	
		
		
	}
}
