package com.market.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.member.dto.MemberDto;
import com.market.mypage.dao.MypageDao;
import com.market.mypage.dto.MypageReviewDto;
import com.market.review.dao.ReviewDao;
import com.market.review.dto.ReviewDto;

@WebServlet("/mypage/mypageReview.do")
public class MypageReviewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		HttpSession session	= req.getSession();
		MemberDto dto  = (MemberDto)session.getAttribute("memberDto");
		MypageDao dao = MypageDao.getInstance();
		ArrayList<MypageReviewDto> list = dao.mypageReview(dto.getId());
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/index.jsp?page=mypage/mypage.jsp&mypage=mypage/mypageReview.jsp").forward(req, resp);		
		
		//resp.sendRedirect(req.getContextPath()+"/index.jsp?page=mypage/mypage.jsp&mypage=member/recheckPwd.jsp");
	}
}
