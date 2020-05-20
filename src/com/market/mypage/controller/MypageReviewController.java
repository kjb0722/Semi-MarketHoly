package com.market.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.member.dto.MemberDto;
import com.market.review.dao.ReviewDao;

@WebServlet("/mypage/mypageReview.do")
public class MypageReviewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		MemberDto dto = (MemberDto)req.getSession();
		
		
		ReviewDao dao = ReviewDao.getInstance();
		dao.mypageReview(dto);
		
		
	}
}
