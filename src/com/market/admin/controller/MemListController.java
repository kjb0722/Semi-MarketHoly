package com.market.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.market.member.dao.MemberDao;
import com.market.member.dto.MemberDto;

@WebServlet("/admin/memList.do")
public class MemListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final int PAGE_CNT = 10; // 글 목록 개수
		final double PAGE_BLOCK = 10.0; // 페이지 블록
		String spageNum = req.getParameter("pageNum");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * PAGE_CNT + 1;
		int endRow = (startRow + PAGE_CNT) - 1;
		
		String word = req.getParameter("word");
		String type = req.getParameter("type");
		MemberDao dao = MemberDao.getInstance();
		ArrayList<MemberDto> memList = dao.selSearchList(startRow, endRow, word, type);
		
		
		
		req.setAttribute("memList", memList);
		JSONArray jarr = new JSONArray();
		for(MemberDto dto : memList) {
			JSONObject json = new JSONObject();
			json.put("num",dto.getNum());
			json.put("id",dto.getId());
			json.put("pwd",dto.getPwd());
			json.put("name",dto.getName());
			json.put("rating",dto.getRating());
			json.put("email",dto.getEmail());
			json.put("birth",dto.getBirth());
			json.put("phone",dto.getPhone());
			json.put("gender",dto.getGender());
			json.put("addr",dto.getAddr());
			json.put("reg_date",dto.getReg_date());
			json.put("point",dto.getPoint());
			json.put("del_yn",dto.getDel_yn());
			json.put("del_date",dto.getDel_date());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(jarr);
	}
}
