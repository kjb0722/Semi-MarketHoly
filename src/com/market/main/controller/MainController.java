package com.market.main.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.admin.dao.CategoryDao;
import com.market.admin.dto.CategoryDto;

@WebServlet("/main.do")
public class MainController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		ServletContext app = req.getServletContext();
		String cp = req.getContextPath();
		app.setAttribute("cp", cp);
		
		//"전체 카테고리"
		CategoryDao dao = CategoryDao.getInstance();
		ArrayList<CategoryDto> catList = dao.selList();
		app.setAttribute("catList", catList);

		//"세부 카테고리"
		ArrayList<CategoryDto> catTypeList = dao.selTypeList();
		app.setAttribute("catTypeList", catTypeList);
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
