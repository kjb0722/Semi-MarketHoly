package com.market.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mypage/startMypage.do")
public class StartMypageController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/index.jsp?page=/mypage/mypage.jsp");
		
		//resp.sendRedirect(req.getContextPath()+"/index.jsp?page=member/login.jsp");
		
		
	}
}
