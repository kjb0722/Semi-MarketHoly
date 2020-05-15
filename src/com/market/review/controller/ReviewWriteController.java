package com.market.review.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.review.dao.ReviewDao;
import com.market.review.dto.ReviewDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/member/writeReview.do")
public class ReviewWriteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String upload = req.getSession().getServletContext().getRealPath("${pageContext.request.contextPath }/img");

		MultipartRequest mr = new MultipartRequest(
					req,
					upload,
					1025*1024*5,
					"utf-8",
					new DefaultFileRenamePolicy()
				);
		File file1 = mr.getFile("file1");
		String writer= req.getParameter("writer");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		
		ReviewDao dao = ReviewDao.getInstance();
		ReviewDto dto = new ReviewDto(onum, pnum, num, rnum, id, name, title, content, regdate, del_yn, pwd);
		
		
		
		
		
		
	}
}
