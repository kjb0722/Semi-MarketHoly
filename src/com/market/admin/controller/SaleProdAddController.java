package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.admin.dao.SaleDao;
import com.market.admin.dto.SaleDto;

@WebServlet("/admin/saleProdAdd.do")
public class SaleProdAddController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		String name = req.getParameter("name");

		String startDateStr = req.getParameter("startDate");
		String endDateStr = req.getParameter("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date tempStartDate = null;
		java.util.Date tempEndDate = null;
		try {
			tempStartDate = sdf.parse(startDateStr);
			tempEndDate = sdf.parse(endDateStr);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		java.sql.Date startDate = new java.sql.Date(tempStartDate.getTime());
		java.sql.Date endDate = new java.sql.Date(tempEndDate.getTime());

		float percent = Float.parseFloat(req.getParameter("percent"));

		SaleDao dao = SaleDao.getInstance();
		int n = dao.insSaleProd(new SaleDto(-1, pnum, name, percent, startDate, endDate, "N"));

		JSONObject json = new JSONObject();
		json.put("n", n);

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
