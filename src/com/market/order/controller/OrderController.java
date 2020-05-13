package com.market.order.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.member.dao.MemberDao;
import com.market.member.dto.MemberDto;
@WebServlet("/order.do")
public class OrderController extends HttpServlet{
//주소,이름,휴대폰,이메일,적립금,       상품정보 리스트(장바구니에 담긴)
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String id=req.getParameter("id");

			
			MemberDao dao=MemberDao.getInstance();
			//회원정보 보낸거.
			MemberDto member=dao.getList(id);
			member.getAddr();
			member.getName();
			member.getPhone();
			member.getEmail();
			member.getPoint();
			
			//상품정보 리스트(장바구니에 담긴)거도 가져와야합니다.
			req.setAttribute("member", member);
			req.getRequestDispatcher("../order.jsp").forward(req, resp);
		}
}
