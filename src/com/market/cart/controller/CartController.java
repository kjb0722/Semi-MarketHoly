package com.market.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.cart.dao.CartDao;
import com.market.cart.dto.CartDto;
@WebServlet("/cart.do")
public class CartController extends HttpServlet{

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			HttpSession session=req.getSession();
			String id=(String)session.getAttribute("id");
			 
			CartDao dao=CartDao.getInstance();
			//회원정보 보낸거.
			CartDto cart=dao.getcart(id);
		
			
			req.setAttribute("cart", cart);
			req.getRequestDispatcher("/index.jsp?page=member/cart.jsp").forward(req, resp);
		}
}
