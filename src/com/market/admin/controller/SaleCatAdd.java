package com.market.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/saleCatAdd.do")
public class SaleCatAdd extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int catNum = Integer.parseInt(req.getParameter("catNum"));
		int catTypeNum = Integer.parseInt(req.getParameter("catTypeNum"));
		
	}
}
