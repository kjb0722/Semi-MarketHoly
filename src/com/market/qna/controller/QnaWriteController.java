package com.market.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.qna.dao.QnaDao;
import com.market.qna.dto.QnaDto;

@WebServlet("/qna/qnaWrite.do")
public class QnaWriteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		int num = Integer.parseInt(req.getParameter("num"));
		String id = req.getParameter("id");
		String sqnum=req.getParameter("qnum");
		String locker = req.getParameter("locker");
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		if(locker==null){
			locker="N";
		}
		
		
		int qnum=0;
		int ref=0;
		if(sqnum!=null && !sqnum.equals("")) {
			qnum=Integer.parseInt(sqnum);
			ref=Integer.parseInt(req.getParameter("ref"));
		}
		
		QnaDto dto = new QnaDto(pnum, num, qnum, id, name, title, content, ref, null, null, locker);
		QnaDao dao = QnaDao.getInstance();
		dao.writeQna(dto);
		
	}
}
