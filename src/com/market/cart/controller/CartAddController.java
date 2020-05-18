package com.market.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import com.market.cart.dao.CartDao;
@WebServlet("/member/cartAdd.do")
public class CartAddController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//카트에 상품 insert   
		String id=req.getParameter("id");
		int pnum= Integer.parseInt(req.getParameter("pnum"));
		int EA= Integer.parseInt(req.getParameter("EA"));
		CartDao dao=CartDao.getInstance();
		int n = dao.inCart(pnum, id, EA);
	
		JSONObject json = new JSONObject();
		json.put("n", n);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		
	}
	
}
