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

import test.dao.CommentsDao;


@WebServlet("/members/join.do")
public class JoinController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int num =1;
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String birth = req.getParameter("birth");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		String addr= req.getParameter("addr");
	
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setEmail(email);
		dto.setBirth(birth);
		dto.setPhone(phone);
		dto.setGender(Integer.parseInt("gender"));
		dto.setAddr(addr);
		
		
		MemberDao dao = MemberDao.getInstance();
		int n = dao.join(dto);
		
	}
}
