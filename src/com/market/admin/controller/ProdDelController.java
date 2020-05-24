package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.product.dao.ProductDao;

@WebServlet("/admin/prodDel.do")
public class ProdDelController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		
		ProductDao prodDao = new ProductDao();
		int n = prodDao.delProd(pnum);
		
		JSONObject json = new JSONObject();
		json.put("n", n);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
