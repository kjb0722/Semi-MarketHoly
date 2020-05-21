package com.market.order.controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.cart.dao.CartDao;
import com.market.cart.dto.CartDto;
import com.market.member.dao.MemberDao;
import com.market.member.dto.MemberDto;
@WebServlet("/order.do")
public class OrderController extends HttpServlet{
//주소,이름,휴대폰,이메일,적립금,       상품정보 리스트(장바구니에 담긴)
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String id=req.getParameter("id");

			MemberDao dao=MemberDao.getInstance();
			MemberDto member=dao.getList(id);
	
			CartDao cdao=CartDao.getInstance();
			ArrayList<CartDto> cart=cdao.getcart(id);
			//하나하나의 바뀐가격도 가져오고 갯수도 가져와야함.
			int total=Integer.parseInt(req.getParameter("total"));//총 상품금액
			int EA=Integer.parseInt(req.getParameter("EA"));
			int finalprice=Integer.parseInt(req.getParameter("finalprice"));
			int shipping=Integer.parseInt(req.getParameter("shipping"));
			int DCprice=Integer.parseInt(req.getParameter("DCprice"));
			int changeEA=Integer.parseInt(req.getParameter("changeEA"));
			int sum=Integer.parseInt(req.getParameter("sum"));//하나하나의 바뀐가격
			
		
			//상품정보 리스트(장바구니에 담긴)거도 가져와야합니다.
			req.setAttribute("total", total);
			req.setAttribute("EA", EA);
			req.setAttribute("finalprice", finalprice);
			req.setAttribute("shipping", shipping);
			req.setAttribute("DCprice", DCprice);
			req.setAttribute("changeEA", changeEA);
			req.setAttribute("sum", sum);
			req.setAttribute("cart", cart);
			req.setAttribute("member", member);
			req.getRequestDispatcher("/index.jsp?page=member/order.jsp").forward(req, resp);
		}
}
