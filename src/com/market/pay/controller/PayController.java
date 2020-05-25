package com.market.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.member.dao.MemberDao;
import com.market.member.dto.MemberDto;
import com.market.pay.dao.PayDao;
import com.market.pay.dto.PayDto;
@WebServlet("/pay.do")
public class PayController extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");	
			HttpSession session = req.getSession();
			PayDao dao=new PayDao();
			MemberDao mdao=MemberDao.getInstance();
			MemberDto dto=(MemberDto) session.getAttribute("memberDto");
			String id=dto.getId();
			dto=mdao.getDto(id);
			int num=dto.getNum();
			
			
			//order_product
			String [] pnum=req.getParameterValues("pnum");
			String [] cartPrice=req.getParameterValues("cartPrice");
			String [] EA=req.getParameterValues("EA");
			String [] pname=req.getParameterValues("pname");
			
			//orders
			String addr=req.getParameter("addr");
			int use_point=Integer.parseInt(req.getParameter("usepoint"));
			int finalprice=Integer.parseInt(req.getParameter("finalprice"));
			int sale_price=Integer.parseInt(req.getParameter("DCprice"));
			int pay_way=Integer.parseInt(req.getParameter("chpay"));
			
			PayDto odto=new PayDto(0, num, 0, "Y", null, null, id, finalprice, use_point, sale_price, pay_way, addr);
			PayDto opdto=null;
			for (int i = 0; i < pnum.length; i++) {
				opdto=new PayDto(0, Integer.parseInt(pnum[i]), pname[i], Integer.parseInt(EA[i]), Integer.parseInt(cartPrice[i]));
			}
				
			PayDao paydao = new PayDao();
			int n = paydao.insertorders(odto,opdto,id);
			if (n>0) {
				
				req.getRequestDispatcher("/index.jsp?page=member/pay.jsp").forward(req, resp);
			}
			resp.sendRedirect(req.getContextPath()+"/error.do");
			
	}
}
