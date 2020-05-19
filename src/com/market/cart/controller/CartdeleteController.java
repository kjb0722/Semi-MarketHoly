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

@WebServlet("/cartdel.do")
public class CartdeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CartDao dao=CartDao.getInstance();
	
		String []cartnum=req.getParameterValues("undercheck");
		System.out.println("cartnum:" + cartnum);
		for (int j = 0; j < cartnum.length; j++) {
			dao.deletecart(Integer.parseInt(cartnum[j]));
		}
	
		JSONObject json = new JSONObject();
		json.put("code", "success");
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
			
	}

}
