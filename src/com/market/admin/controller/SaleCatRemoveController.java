package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.admin.dao.SaleDao;

@WebServlet("/admin/saleCatRemove.do")
public class SaleCatRemoveController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cnum = Integer.parseInt(req.getParameter("catNum"));
		int type = Integer.parseInt(req.getParameter("catTypeNum"));
		SaleDao dao = SaleDao.getInstance();
		int n = dao.delSale(cnum, type);
		
		JSONObject json = new JSONObject();
		json.put("n", n);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
