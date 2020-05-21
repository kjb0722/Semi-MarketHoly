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
			String []cartnum=req.getParameterValues("undercheck");
			String []total=req.getParameterValues("total");
			int finalprice=Integer.parseInt(req.getParameter("finalprice"));//총 상품금액
			String []EA=req.getParameterValues("undercheck");
			String []DCprice=req.getParameterValues("DCprice");
			String []sum=req.getParameterValues("sum");
			int shipping=Integer.parseInt(req.getParameter("shipping"));
			
//			for (int i=0;i<cartnum.length;i++) {
//			Integer.parseInt(cartnum[i]);
//			Integer.parseInt(EA[i]);
//			Integer.parseInt(DCprice[i]);
//			Integer.parseInt(sum[i]);
//			
		
			//상품정보 리스트(장바구니에 담긴)거도 가져와야합니다.
			req.setAttribute("cartnum", cartnum);
			req.setAttribute("total", total);
			req.setAttribute("EA", EA);
			req.setAttribute("finalprice", finalprice);
			req.setAttribute("shipping", shipping);
			req.setAttribute("DCprice", DCprice);
			req.setAttribute("sum", sum);
			req.setAttribute("cart", cart);
			req.setAttribute("member", member);
			req.getRequestDispatcher("/index.jsp?page=member/order.jsp").forward(req, resp);
		}
}
