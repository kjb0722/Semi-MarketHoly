package com.market.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.admin.dao.CategoryDao;
import com.market.admin.dto.CategoryDto;

@WebServlet("/admin/product.do")
public class ProductController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryDao dao = CategoryDao.getInstance();
		
		ArrayList<CategoryDto> catList = dao.selList();
		req.setAttribute("catList", catList);
		
		req.getRequestDispatcher("/index.jsp?page=admin/product.jsp").forward(req, resp);
	}
}
