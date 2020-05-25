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
import com.market.product.dao.ProductDao;
import com.market.product.dto.ProductDto;

@WebServlet("/main.do")
public class MainController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
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
		
		//"추천상품"
		ProductDao pdao = new ProductDao();
		ArrayList<ProductDto> list = pdao.getList(1, 3, null, 0, 0);
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
