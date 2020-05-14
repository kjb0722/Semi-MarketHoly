package com.market.member.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/loginstart.do")
public class StartLoginController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath();
		ServletContext application = req.getServletContext();
		application.setAttribute("cp", contextPath);
		resp.sendRedirect(req.getContextPath()+"/index.jsp?page=member/login.jsp");
	}
	
}
