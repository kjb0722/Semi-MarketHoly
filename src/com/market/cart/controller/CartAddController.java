package com.market.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.market.cart.dao.CartDao;
import com.market.member.dto.MemberDto;
@WebServlet("/member/cartAdd.do")
public class CartAddController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//카트에 상품 insert   
		HttpSession session = req.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		if (memberDto == null) {
			resp.sendRedirect(req.getContextPath() + "/member/loginstart.do");
			return;
		}
		String id=memberDto.getId();
		int pnum= Integer.parseInt(req.getParameter("pnum"));
		int EA= Integer.parseInt(req.getParameter("EA"));
		CartDao dao=CartDao.getInstance();
		int n = dao.inCart(pnum, id, EA);
		if (n > 0) {
			resp.sendRedirect(req.getContextPath() + "/cart.do");
	
		} else {
			resp.sendRedirect(req.getContextPath() + "/error.do");	

		}
	}
}
