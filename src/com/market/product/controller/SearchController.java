package com.market.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.admin.dao.CategoryDao;
import com.market.admin.dto.CategoryDto;
import com.market.member.dto.MemberDto;
import com.market.product.dao.ProductDao;
import com.market.product.dto.ProductDto;
@WebServlet("/product/search.do")
public class SearchController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		String id="";
		if (memberDto != null) {
			id=memberDto.getId();
		}
		String keyword=req.getParameter("keyword");
		// 페이징
		String spageNum = req.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 9 + 1;
		int endRow = startRow + 8;

		ProductDao dao = new ProductDao();
		ArrayList<ProductDto> list = dao.getSearchList(startRow, endRow, keyword);
		int result = dao.getCount(0, 0, keyword);
		int pageCount = (int) Math.ceil(dao.getCount(0, 0, keyword) / 9.0);
		int startPageNum = ((pageNum - 1) / 5) * 5 + 1;
		int endPageNum = startPageNum + 4;
		if (pageCount < endPageNum) {
			endPageNum = pageCount;
		}
		req.setAttribute("keyword", keyword);
		req.setAttribute("id", id);
		req.setAttribute("list", list);
		req.setAttribute("result", result);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		
		req.getRequestDispatcher("/index.jsp?page=product/search_list.jsp").forward(req, resp);
	}
}
