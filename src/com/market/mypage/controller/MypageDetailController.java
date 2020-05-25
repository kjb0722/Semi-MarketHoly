package com.market.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.mypage.dao.MypageDao;
import com.market.mypage.dto.OrderDetailDto;

@WebServlet("/mypage/orderDetail.do")
public class MypageDetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int opnum = Integer.parseInt(req.getParameter("opnum"));
		int onum = Integer.parseInt(req.getParameter("onum"));		
		
		
		MypageDao dao = MypageDao.getInstance();
		ArrayList<OrderDetailDto> list = dao.orderDetail(onum,opnum);
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/index.jsp?page=mypage/mypage.jsp&mypage=mypage/orderDetail.jsp").forward(req, resp);
		
		
	}
}
