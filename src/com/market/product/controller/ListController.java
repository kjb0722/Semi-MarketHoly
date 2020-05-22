package com.market.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.market.admin.dao.CategoryDao;
import com.market.admin.dto.CategoryDto;
import com.market.member.dto.MemberDto;
import com.market.product.dao.ProductDao;
import com.market.product.dto.ProductDto;

@WebServlet("/product/list.do")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		String id="";
		if (memberDto != null) {
			id=memberDto.getId();
		}
		// 카테고리
		String sCnum = req.getParameter("cnum");
		String list_filter = req.getParameter("list_filter");
		int cnum = 0;
		if (sCnum != null) {
			cnum = Integer.parseInt(req.getParameter("cnum"));
		}
		String sType = req.getParameter("type");
		int type = -1;
		if (sType != null) {
			type = Integer.parseInt(sType);
		}
		
		// 페이징
		String spageNum = req.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 9 + 1;
		int endRow = startRow + 8;

		ProductDao dao = new ProductDao();
		ArrayList<ProductDto> list = dao.getList(startRow, endRow, list_filter, cnum, type);

		int pageCount = (int) Math.ceil(dao.getCount(cnum, type,"") / 9.0);
		int startPageNum = ((pageNum - 1) / 5) * 5 + 1;
		int endPageNum = startPageNum + 4;
		if (pageCount < endPageNum) {
			endPageNum = pageCount;
		}
		// 카테고리 이름 가져오기
		CategoryDao cdao = CategoryDao.getInstance();
		//cnum에 해당하는 세부 카테고리 가져오기
		ArrayList<CategoryDto> clist = cdao.selSub(cnum);
		String cname = cdao.getName(type); //선택된 카테고리 이름
		String tname = cdao.getName(cnum); //선택된 세부카테고리 이름

		req.setAttribute("id", id);
		req.setAttribute("cname", cname);
		req.setAttribute("tname", tname);
		req.setAttribute("cnum", cnum);
		req.setAttribute("type", type);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("list_filter", list_filter);
		
		req.getRequestDispatcher("/index.jsp?page=product/list.jsp").forward(req, resp);

	}

}
