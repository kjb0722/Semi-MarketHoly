package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.admin.dao.SaleDao;
import com.market.admin.dto.SaleDto;
import com.market.product.dao.ProductDao;
import com.market.product.dto.ProductDto;

@WebServlet("/admin/saleCatAdd.do")
public class SaleCatAddController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cnum = Integer.parseInt(req.getParameter("catNum"));
		int type = Integer.parseInt(req.getParameter("catTypeNum"));
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
		
		ProductDao dao = new ProductDao();
		int endRow = dao.getCount(cnum, type, "");
		ArrayList<ProductDto> list = dao.getList(0, endRow, "", cnum, type);
		SaleDao saleDao = SaleDao.getInstance();
		int resultCnt = 0;
		for (ProductDto dto : list) {
			int n = saleDao.insSale(new SaleDto(-1, dto.getPnum(), name, percent, startDate, endDate, "N"));
			if (n > 0) {
				resultCnt++;
			}
		}

		JSONObject json = new JSONObject();
		json.put("n", resultCnt);

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
