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
import com.sun.media.sound.AlawCodec;
@WebServlet("/pay.do")
public class PayController extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");	
			HttpSession session = req.getSession();
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
			PayDao paydao = new PayDao();
			
			//orders
			int use_point=0;
			if(use_point>0) {
				use_point=Integer.parseInt(req.getParameter("usepoint"));
			}
			
			int point=Integer.parseInt(req.getParameter("point"));
			System.out.println("use_point:"+use_point+",point:"+point);
			if (use_point>0 && point >= use_point) {
				
				paydao.updatepoint(use_point, id);
				System.out.println("업");
			}
			int finalprice=Integer.parseInt(req.getParameter("finalprice"));
			int sale_price=Integer.parseInt(req.getParameter("DCprice"));
			int pay_way=Integer.parseInt(req.getParameter("chpay"));
			String chaddr = req.getParameter("chaddr");
			String addr = "";
			if(chaddr.equals("oraddr")) {
				addr = req.getParameter("oraddr");
			}else {
				addr = req.getParameter("nwaddr");
			}
			
			PayDto odto=new PayDto(0, num, 0, "Y", null, null, id, finalprice, use_point, sale_price, pay_way, addr);
			int n = paydao.insertord(odto,id,pnum,pname,EA,cartPrice);
			
				
			if (n>0) {
				req.getRequestDispatcher("/index.jsp?page=member/pay.jsp").forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/error.do");
			}
			
	}
}
