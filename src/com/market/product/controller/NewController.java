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
@WebServlet("/product/new.do")
public class NewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//최근 일주일 등록 상품
		String spageNum = req.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 9 + 1;
		int endRow = startRow + 8;

		ProductDao dao = new ProductDao();
		ArrayList<ProductDto> list = dao.getNewList(startRow, endRow);
		int pageCount = (int) Math.ceil(dao.getCount(0, 0,"") / 9.0);
		int startPageNum = ((pageNum - 1) / 5) * 5 + 1;
		int endPageNum = startPageNum + 4;
		if (pageCount < endPageNum) {
			endPageNum = pageCount;
		}

		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("/index.jsp?page=product/new_list.jsp").forward(req, resp);
	}
}
