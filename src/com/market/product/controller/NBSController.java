package com.market.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.product.dao.ProductDao;
import com.market.product.dto.ProductDto;
@WebServlet("/product/nbs.do")
public class NBSController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.최근 일주일 등록 상품
		//2.인기상품
		//3.세일상품
		String filter=req.getParameter("filter");
		
		String spageNum = req.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 9 + 1;
		int endRow = startRow + 8;

		ProductDao dao = new ProductDao();
		ArrayList<ProductDto> list = dao.getNBSList(startRow, endRow,filter);
		int pageCount = (int) Math.ceil(dao.getNBSCount(filter) / 9.0);
		int startPageNum = ((pageNum - 1) / 5) * 5 + 1;
		int endPageNum = startPageNum + 4;
		if (pageCount < endPageNum) {
			endPageNum = pageCount;
		}
		
		req.setAttribute("filter", filter);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("/index.jsp?page=product/nbs_list.jsp").forward(req, resp);
	}
}
