package com.market.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.member.dto.MemberDto;
import com.market.mypage.dao.MypageDao;
import com.market.mypage.dto.MypagePointDto;

@WebServlet("/mypage/mypageShowPoint.do")
public class MypageShowPointController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberDto dto =(MemberDto)session.getAttribute("memberDto");
	
		MypageDao dao = MypageDao.getInstance();
		MypagePointDto pointDto = dao.pointList(dto.getId());
		
		req.setAttribute("pointDto", pointDto);
		req.getRequestDispatcher("/index.jsp?page=mypage/mypage.jsp&mypage=mypage/pointList.jsp").forward(req, resp);
		
	}	
}
