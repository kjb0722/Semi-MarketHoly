package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.admin.dao.CategoryDao;
import com.market.admin.dto.CategoryDto;

@WebServlet("/admin/catUpdate.do")
public class CatUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int catNum = Integer.parseInt(req.getParameter("catNum"));
		String catName = req.getParameter("catName");
		CategoryDao dao = CategoryDao.getInstance();
		CategoryDto dto = new CategoryDto(catNum, -1, catName);
		int n = dao.updateCat(dto);
		
		JSONObject json = new JSONObject();
		json.put("n", n);
		resp.setContentType("text/plain;charset-utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		
//		if(n>0) {
//			resp.sendRedirect(req.getContextPath()+"/admin/category.do");
//		}else {
//			resp.sendRedirect(req.getContextPath()+"/error.do");
//		}
	}
}
