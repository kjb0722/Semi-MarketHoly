package com.market.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.cart.dao.CartDao;
import com.market.cart.dto.CartDto;
@WebServlet("/cart.do")
public class CartController extends HttpServlet{

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String id=req.getParameter("id");

			 
			CartDao dao=CartDao.getInstance();
			//회원정보 보낸거.
			CartDto cart=dao.getcart(id);
			cart.getId();
			cart.getPnum();
			cart.getName();
			cart.getEA();
			cart.getPrice();
			cart.getPercent();
			cart.getThumb_save();
			
			
			req.setAttribute("cart", cart);
			req.getRequestDispatcher("/index.jsp?page=member/cart.jsp").forward(req, resp);
		}
}
