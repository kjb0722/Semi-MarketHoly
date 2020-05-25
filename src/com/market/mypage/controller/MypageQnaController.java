package com.market.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.member.dto.MemberDto;
import com.market.mypage.dao.MypageDao;
import com.market.mypage.dto.MypageReviewDto;
import com.market.qna.dao.QnaDao;
import com.market.qna.dto.QnaDto;

@WebServlet("/mypage/mypageQna.do")
public class MypageQnaController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		
		HttpSession session	= req.getSession();
		MemberDto dto  = (MemberDto)session.getAttribute("memberDto");
		String ids = dto.getId();
		
		MypageDao dao = MypageDao.getInstance();
		ArrayList<QnaDto> list= dao.mypageQna(ids, startRow, endRow);
		
		
		int count= dao.getCount2(ids);
		
		int pageCount=(int)Math.ceil(dao.getCount2(ids)/5.0);
		int startPageNum=((pageNum-1)/4)*4+1;
		int endPageNum=startPageNum+3;
		if(pageCount<endPageNum) {
			endPageNum=pageCount;
		}
		
		req.setAttribute("count", count);
		req.setAttribute("list",list);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("startPage",startPageNum);
		req.setAttribute("endPage",endPageNum);
		req.setAttribute("pageNum",pageNum);

		req.getRequestDispatcher("/index.jsp?page=mypage/mypage.jsp&mypage=mypage/mypageQna.jsp").forward(req, resp);	
	
	}
}
