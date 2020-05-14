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


@WebServlet("/product/list.do")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int cnum=Integer.parseInt(req.getParameter("cnum"));
		String sType=req.getParameter("type");
		int type = -1;
		if(sType != null) {
			type = Integer.parseInt(sType);
		}
		
		
		String filter=req.getParameter("filter");
		String spageNum=req.getParameter("pageNum");
		String list_filter=req.getParameter("list_filter");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		ProductDao dao=new ProductDao();
		ArrayList<ProductDto>list=dao.getList(startRow, endRow,filter,cnum,type);
		
		int pageCount=(int)Math.ceil(dao.getCount()/5.0);
		int startPageNum=((pageNum-1)/4)*4+1;
		int endPageNum=startPageNum+3;
		if(pageCount<endPageNum) {
			endPageNum=pageCount;
		}
		
		req.setAttribute("type", type);
		req.setAttribute("cnum", cnum);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("list_filter", list_filter);
		req.getRequestDispatcher("/index.jsp?page=product/list.jsp").forward(req, resp);
		
	}
	
}
