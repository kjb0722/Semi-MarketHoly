package com.market.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.review.dao.ReviewDao;
import com.market.review.dto.ReviewDto;

@WebServlet("/member/listReview.do")
public class ListReviewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReviewDao reviewDao = ReviewDao.getInstance();
		ArrayList<ReviewDto> list = reviewDao.listReview();
		
		req.setAttribute("list", list);
		//System.out.println(list.get(0).getId());
		req.getRequestDispatcher("/review/reviewList.jsp").forward(req, resp);
		//"/index.jsp?page=/member/joinResult.jsp"
	}
}
