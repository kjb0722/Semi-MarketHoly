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
import com.market.review.dao.ReviewDao;
import com.market.review.dto.ReviewDto;

@WebServlet("/product/detail.do")
public class DetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pnum=Integer.parseInt(req.getParameter("pnum"));
		ProductDao dao=new ProductDao();
		ProductDto dto=dao.getDetail(pnum);
		req.setAttribute("dto",dto);
		
		//리뷰 콜
		String spageNum=req.getParameter("pageNum");
		System.out.println(spageNum);
		
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		
		
		
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		ReviewDao dao1 = ReviewDao.getInstance();
		dao1.listReview(startRow,endRow);
		
		ReviewDao reviewDao = ReviewDao.getInstance();
		ArrayList<ReviewDto> list = reviewDao.listReview(startRow,endRow);
		int pageCount=(int)Math.ceil(dao1.getCount()/5.0);
		int startPageNum=((pageNum-1)/4)*4+1;
		int endPageNum=startPageNum+3;
		if(pageCount<endPageNum) {
			endPageNum=pageCount;
		}
		
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPageNum);
		req.setAttribute("endPage", endPageNum);
		req.setAttribute("pageNum", pageNum);
		

		//req.getRequestDispatcher("/index.jsp?page=review/reviewList.jsp").forward(req, resp);
		
		req.getRequestDispatcher("/index.jsp?page=product/detail.jsp").forward(req, resp);
	}
}
