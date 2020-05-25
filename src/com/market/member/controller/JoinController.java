package com.market.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.member.dao.MemberDao;
import com.market.member.dto.MemberDto;




@WebServlet("/member/join.do")
public class JoinController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd1");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String birth = req.getParameter("birth");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		String addr= req.getParameter("addr");
		
		if(gender==null) {
			gender = "3";
		}
		
		MemberDto dto = new MemberDto(0, id, pwd, name, 10, email, birth, phone, Integer.parseInt(gender), addr, null, 1000, "N", null);
		
		
		
		MemberDao dao = MemberDao.getInstance();
		int n = dao.join(dto);
		String code = "success";
		
		if(n==-1) {	// 중복이메일
			code="fail1";
			req.setAttribute("code", code);
			req.getRequestDispatcher("/index.jsp?page=/member/joinResult.jsp").forward(req, resp);
		
		}else if(n==0) {	//중복아이디
			code="fail2";
			req.setAttribute("code", code);
			req.getRequestDispatcher("/index.jsp?page=/member/joinResult.jsp").forward(req, resp);
		}else {	//회원가입 성공
			resp.sendRedirect(req.getContextPath()+"/member/joinSucess.jsp");
			//resp.sendRedirect(req.getContextPath()+"/index.jsp?page=member/login.jsp");
		}
		
		
	}
}
