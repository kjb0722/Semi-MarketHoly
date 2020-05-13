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

@WebServlet("/admin/member.do")
public class MemberController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//공통 테이블에서 검색 콤보박스용 데이터 불러오기
		CommonDao dao = CommonDao.getInstance();
		ArrayList<CommonDto> comList = dao.selComList("회원검색 combobox");
		req.setAttribute("comList", comList);
		
		req.getRequestDispatcher("/index.jsp?page=admin/member.jsp").forward(req, resp);
	}
}
