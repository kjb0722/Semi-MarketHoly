package com.market.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.admin.dao.CategoryDao;
import com.market.admin.dto.CategoryDto;

@WebServlet("/admin/catTypeAdd.do")
public class CatTypeAddController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int type = Integer.parseInt(req.getParameter("cat"));
		String catTypeName = req.getParameter("catTypeName");
		CategoryDao dao = CategoryDao.getInstance();
		CategoryDto dto = new CategoryDto(-1, type, catTypeName);
		int n = dao.insTypeCat(dto);
		if (n > 0) {
			resp.sendRedirect(req.getContextPath() + "/admin/category.do");
		} else {
			resp.sendRedirect(req.getContextPath() + "/error.do");
		}
	}
}
