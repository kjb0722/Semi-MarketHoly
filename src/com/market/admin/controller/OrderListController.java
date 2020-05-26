package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.market.admin.dao.OrderAdminDao;
import com.market.admin.dto.OrderAdminDto;

@WebServlet("/admin/orderList.do")
public class OrderListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final int PAGE_CNT = 10; // 글 목록 개수
		final double PAGE_BLOCK = 10.0; // 페이지 블록
		String spageNum = req.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * PAGE_CNT + 1;
		int endRow = (startRow + PAGE_CNT) - 1;

		String kind = req.getParameter("kind");
		if (kind == null) {
			kind = "";
		}
		String word = req.getParameter("word");
		if (word == null) {
			word = "";
		}
		String status = req.getParameter("status");
		if(status.equals("1")) {
			status = "1,2,3,4";
		}else {
			status = "5,6";
		}

		OrderAdminDao ordDao = OrderAdminDao.getInstance();

		ArrayList<OrderAdminDto> ordList = ordDao.selOrdList(startRow, endRow, kind, word, status);
		int pageCount = (int) Math.ceil(ordDao.selOrdCnt(startRow, endRow, kind, word, status) / PAGE_BLOCK);
		int startPageNum = (int) (Math.floor((pageNum - 1) / PAGE_BLOCK) * PAGE_BLOCK + 1);
		int endPageNum = (int) (startPageNum + (PAGE_BLOCK - 1));
		if (pageCount < endPageNum) {
			endPageNum = pageCount;
		}

		JSONArray jsonArr = new JSONArray();
		JSONArray jarr = new JSONArray();
		for (OrderAdminDto dto : ordList) {
			JSONObject json = new JSONObject();
			json.put("onum", dto.getOnum());
			json.put("id", dto.getId());
			json.put("statusName", dto.getStatusName());
			json.put("prodName", dto.getProdName());
			json.put("pay_yn", dto.getPay_yn());
			json.put("price", dto.getPrice());
			json.put("addr", dto.getAddr());
			json.put("pay_wayName", dto.getPay_wayName());
			json.put("use_point", dto.getUse_point());
			json.put("reg_date", dto.getReg_date());
			
			json.put("num", dto.getNum());
			json.put("rating", dto.getRating());
			json.put("status", dto.getStatus());
			
			jarr.put(json);
		}

		jsonArr.put(jarr);
		jsonArr.put(startPageNum);
		jsonArr.put(endPageNum);
		jsonArr.put(pageNum);
		jsonArr.put(pageCount);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jsonArr);
	}
}
