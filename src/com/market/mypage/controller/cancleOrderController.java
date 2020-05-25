package com.market.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.mypage.dao.MypageDao;

@WebServlet("/mypage/cancleOrder.do")
public class cancleOrderController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int onum = Integer.parseInt(req.getParameter("onum"));
		
		MypageDao dao = MypageDao.getInstance();
		int n = dao.cancleOrder(onum);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/mypage/cancleResult.jsp");
		}else if(n ==0) {
			resp.sendRedirect(req.getContextPath()+"/mypage/cancleResult3.jsp");
		}else{
			resp.sendRedirect(req.getContextPath()+"/mypage/cancleResult2.jsp");
		}
		
		
	}
}
