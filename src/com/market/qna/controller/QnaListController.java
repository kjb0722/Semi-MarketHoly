package com.market.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.qna.dao.QnaDao;
import com.market.qna.dto.QnaDto;

@WebServlet("/qna/qnaList.do")
public class QnaListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		
		QnaDao dao = QnaDao.getInstance();
		
		//페이지에 해당하는 글목록 가져오기
		ArrayList<QnaDto> list=dao.list(startRow, endRow);
		//전체 페이지갯수 구하기
		int pageCount=(int)Math.ceil(dao.getCount()/10.0);
		int startPageNum=((pageNum-1)/10)*10+1;
		int endPageNum=startPageNum+9;
		if(pageCount<endPageNum) {
			endPageNum=pageCount;
		}
		req.setAttribute("list",list);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("startPageNum",startPageNum);
		req.setAttribute("endPageNum",endPageNum);
		req.setAttribute("pageNum",pageNum);
		req.getRequestDispatcher("/index.jsp?page=/qna/listQna.jsp").forward(req, resp);
	}
}
