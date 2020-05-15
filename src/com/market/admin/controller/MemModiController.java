package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.market.member.dao.MemberDao;
import com.market.member.dto.MemberDto;

@WebServlet("/admin/memModify.do")
public class MemModiController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String email = req.getParameter("email");
		int gender = Integer.parseInt(req.getParameter("gender"));
		String phone = req.getParameter("phone");
		String addr = req.getParameter("addr");
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto(num,"","",name,-1,email,birth,phone,gender,addr,null,-1,"",null);
		int n = dao.updMemFromAdmin(dto);
		
		JSONObject json = new JSONObject();
		json.put("n", n);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
