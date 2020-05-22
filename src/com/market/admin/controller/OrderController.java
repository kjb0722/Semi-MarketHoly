package com.market.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.common.dao.CommonDao;
import com.market.common.dto.CommonDto;

@WebServlet("/admin/order.do")
public class OrderController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommonDao comDao = CommonDao.getInstance();
		ArrayList<CommonDto> comList = comDao.selComList("주문관리 combobox");
		ArrayList<CommonDto> statusList = comDao.selComList("주문관리 상태 combobox");
		ArrayList<CommonDto> statList = comDao.selComList("주문상태");
		req.setAttribute("comList", comList);
		req.setAttribute("statusList", statusList);
		req.setAttribute("statList", statList);
		req.getRequestDispatcher("/index.jsp?page=admin/order.jsp").forward(req, resp);
	}
}
