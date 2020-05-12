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
	
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String birth = req.getParameter("birth");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		String addr= req.getParameter("addr");
	
		MemberDto dto = new MemberDto(0, id, pwd, name, 10, email, birth, phone, Integer.parseInt(gender), addr, null, 1000, "N", null);
		
		//일반회원 rating은 10입니다.
		//기본포인트 1000원
		
		MemberDao dao = MemberDao.getInstance();
		int n = dao.join(dto);
		String code = "success";
		if(n<=0) {
			code="fail";
		}
		req.setAttribute("code", code);
		req.getRequestDispatcher("/member/result.jsp").forward(req, resp);
	}
}
