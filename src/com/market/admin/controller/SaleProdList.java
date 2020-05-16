package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.market.product.dao.ProductDao;
import com.market.product.dto.ProductDto;

@WebServlet("/admin/saleProdList.do")
public class SaleProdList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int catNum = Integer.parseInt(req.getParameter("catNum"));
		int catTypeNum = Integer.parseInt(req.getParameter("catTypeNum"));
		System.out.println(catNum);
		System.out.println(catTypeNum);
		ProductDao dao = new ProductDao();
		ArrayList<ProductDto> prodList = dao.getList(0, 10, null, catTypeNum, catTypeNum);

		JSONArray jarr = new JSONArray();
		for (ProductDto dto : prodList) {
			JSONObject json = new JSONObject();
			json.put("pnum", dto.getPnum());
			json.put("cnum", dto.getCnum());
			json.put("name", dto.getName());
			json.put("reg_date", dto.getReg_date());
			json.put("price", dto.getPrice());
			json.put("stock", dto.getStock());
			json.put("type", dto.getType());
			json.put("thumb_org", dto.getThumb_org());
			json.put("thumb_save", dto.getThumb_save());
			json.put("description", dto.getDescription());
			json.put("detail_org", dto.getDetail_org());
			json.put("detail_save", dto.getDetail_save());
			json.put("del_yn", dto.getDel_yn());
			jarr.put(json);
		}

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jarr);
	}
}
