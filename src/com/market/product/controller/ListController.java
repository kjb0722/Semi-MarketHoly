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
import com.sun.xml.internal.txw2.Document;
@WebServlet("/product/list.do")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//index에서 cnum넘어오면 name 가져올수 있음 만약 type이 -1이 아니면 cnum이 type 인 애(대분류)도 가져오기
		int cnum=Integer.parseInt(req.getParameter("cnum"));
		
		String filter=req.getParameter("filter");
		String spageNum=req.getParameter("pageNum");
		String op=req.getParameter("op");
		String keyword=req.getParameter("keyword");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		ProductDao dao=new ProductDao();
		ArrayList<ProductDto>list=dao.getList(startRow, endRow,filter);
		int pageCount=(int)Math.ceil(dao.getCount(op,keyword)/5.0);
		int startPageNum=((pageNum-1)/4)*4+1;
		int endPageNum=startPageNum+3;
		if(pageCount<endPageNum) {
			endPageNum=pageCount;
		}
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("op", op);
		req.setAttribute("keyword", keyword);
		req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
	}
	
}
