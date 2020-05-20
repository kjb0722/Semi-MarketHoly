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

import com.market.admin.dao.SaleDao;
import com.market.admin.dto.SaleProdListDto;

@WebServlet("/admin/saleProdList.do")
public class SaleProdListController extends HttpServlet {
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
		
		int catNum = Integer.parseInt(req.getParameter("catNum"));
		int catTypeNum = Integer.parseInt(req.getParameter("catTypeNum"));
		
		SaleDao dao = SaleDao.getInstance();
		ArrayList<SaleProdListDto> prodList = dao.selProdList(startRow, endRow, catNum, catTypeNum);
		
		int pageCount = (int) Math.ceil(dao.selProdCount() / PAGE_BLOCK);
		int startPageNum = (int) (Math.floor((pageNum - 1) / PAGE_BLOCK) * PAGE_BLOCK + 1);
		int endPageNum = (int) (startPageNum + (PAGE_BLOCK - 1));
		if (pageCount < endPageNum) {
			endPageNum = pageCount;
		}
		
		JSONArray jsonArr = new JSONArray();
		JSONArray jarr = new JSONArray();
		for (SaleProdListDto dto : prodList) {
			JSONObject json = new JSONObject();
			json.put("pnum", dto.getPnum());
			json.put("cnum", dto.getCnum());
			json.put("name", dto.getName());
			json.put("reg_date", dto.getReg_date());
			json.put("price", dto.getPrice());
			json.put("stock", dto.getStock());
			json.put("type", dto.getType());
			json.put("thumb_save", dto.getThumb_save());
			json.put("onSaleName", dto.getOnSaleName()+"");
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
