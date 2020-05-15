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
import com.market.admin.dto.CategoryListDto;

@WebServlet("/admin/category.do")
public class CategoryController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryDao dao = CategoryDao.getInstance();

		ArrayList<CategoryDto> catList = dao.selList();
		req.setAttribute("catList", catList);

		// ī�װ�,���� ����Ʈ �˻�
		ArrayList<CategoryListDto> catListAll = dao.selListAll();
		req.setAttribute("catListAll", catListAll);
		req.getRequestDispatcher("/index.jsp?page=admin/category.jsp").forward(req, resp);
	}
}
