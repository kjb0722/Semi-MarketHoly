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


@WebServlet("/mypage/mypageOrderList.do")
public class MypageOrderListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("memberDto");
		MypageDao dao = MypageDao.getInstance();
		dao.orderList(dto.getId());
				
	}
}
