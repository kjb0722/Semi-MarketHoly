package com.market.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.member.dto.MemberDto;

@WebServlet("/qna/startQnaWrite.do")
public class StartQnaWriteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		HttpSession session = req.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("memberDto");
		
		if(dto==null) {
			resp.sendRedirect(req.getContextPath()+"/qna/notLoginResult.jsp");
		}else {
		
		
		
			req.setAttribute("memDto", dto);
			req.setAttribute("pnum", pnum);
						
			req.getRequestDispatcher("/index.jsp?page=qna/writeQna.jsp").forward(req, resp);	
		}
		
		
		//resp.sendRedirect(req.getContextPath()+"/index.jsp?page=product/detail.jsp"
		//		+ "&tabpage=qna/writeQna.jsp?pnum="+pnum+"&id="+id+"&num="+num+"&name="+name);
	
		
	}
	
	
}
