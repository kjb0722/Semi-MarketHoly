package com.market.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.member.dto.MemberDto;
import com.market.review.dao.ReviewDao;
import com.market.review.dto.ReviewNumbersDto;

@WebServlet("/review/startWriteReview.do")
public class StartWriteReviewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		
		
		HttpSession session = req.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("memberDto");
		

		if(dto.getId()==null) {
			resp.sendRedirect(req.getContextPath()+"/review/reviewResult.jsp");
		}else {
			
			int num = dto.getNum();
			String name = dto.getName();
			String id = dto.getId();
		
			req.setAttribute("num", num);
			req.setAttribute("name", name);
			req.setAttribute("id", id);
			

			ReviewDao dao = ReviewDao.getInstance();
			ReviewNumbersDto numbersDto = dao.getNumbers(dto.getId(),pnum);
			
			if(numbersDto!=null) {
				req.setAttribute("numbersDto", numbersDto);
				req.getRequestDispatcher("/index.jsp?page=review/writeReview.jsp").forward(req, resp);
			
				//resp.sendRedirect(req.getContextPath()+"/index.jsp?page=mypage/mypage.jsp&mypage=member/recheckPwd.jsp");
				
			}else {
				resp.sendRedirect(req.getContextPath()+"/review/reviewResult.jsp");
			}
		}	
	}
}
