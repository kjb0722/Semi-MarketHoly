package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.admin.dao.ProdDao;
import com.market.admin.dto.ProdInfoDto;

@WebServlet("/admin/prodModify.do")
public class ProdModifyController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		String name = req.getParameter("name");
		String description = req.getParameter("desc");
		int price = Integer.parseInt(req.getParameter("price"));
		int stock = Integer.parseInt(req.getParameter("stock"));
		String unit = req.getParameter("unit");
		String volume = req.getParameter("volume");
		String origin = req.getParameter("origin");
		String pack_type = req.getParameter("pack_type");
		String shelf_life = req.getParameter("shelf_life");
		String guidance = req.getParameter("guidance");
		ProdInfoDto prodInfo = new ProdInfoDto(pnum, unit, volume, origin, pack_type, shelf_life, guidance);
		
		ProdDao prodDao = ProdDao.getInstance();
		int n = prodDao.updProd(pnum, name, description, price, stock, prodInfo);

		JSONObject json = new JSONObject();
		json.put("n", n);

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
