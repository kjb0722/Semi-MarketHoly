package com.market.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.admin.dao.OrderAdminDao;
import com.market.admin.dto.OrdDetailDto;
import com.market.admin.dto.OrderAdminDto;

@WebServlet("/admin/ordDetail.do")
public class OrdDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int onum = Integer.parseInt(req.getParameter("onum"));
		
		OrderAdminDao ordDao = OrderAdminDao.getInstance();
		ArrayList<OrdDetailDto> detailList = ordDao.selOrdDetail(onum);
		
		OrderAdminDto ordInfo = ordDao.selOrdInfo(onum);
		
		req.setAttribute("detailList", detailList);
		req.setAttribute("ordInfo", ordInfo);
		req.getRequestDispatcher("/index.jsp?page=admin/ordDetail.jsp").forward(req, resp);
	}
}
