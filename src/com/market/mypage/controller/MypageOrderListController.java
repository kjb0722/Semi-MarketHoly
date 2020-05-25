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
import com.market.mypage.dto.OrderListDto;


@WebServlet("/mypage/mypageOrderList.do")
public class MypageOrderListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("memberDto");
		MypageDao dao = MypageDao.getInstance();	
		
		ArrayList<OrderListDto> list = dao.orderList(dto.getId());
		
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/index.jsp?page=mypage/mypage.jsp&mypage=mypage/orderList.jsp").forward(req, resp);
		//mypage/orderList.jsp
		// /mypage/mypageOrderList.do
	}
}
