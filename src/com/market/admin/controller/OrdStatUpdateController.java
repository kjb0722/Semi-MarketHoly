package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.admin.dao.OrderAdminDao;

@WebServlet("/admin/ordStatUpdate.do")
public class OrdStatUpdateController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int status = Integer.parseInt(req.getParameter("status"));
		String[] onums = req.getParameterValues("onums[]");
		String[] num = req.getParameterValues("num[]");
		String[] rating = req.getParameterValues("rating[]");

		OrderAdminDao ordDao = OrderAdminDao.getInstance();
		int n = ordDao.updStatus(onums, num, rating, status);

		JSONObject json = new JSONObject();
		json.put("n", n);

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
