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


@WebServlet("/review/writeReview.do")
public class ReviewWriteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String upload = req.getServletContext().getRealPath("/img");
	

		
		MultipartRequest mr = new MultipartRequest(
					req,
					upload,
					1025*1024*5,
					"utf-8",
					new DefaultFileRenamePolicy()
				);
	
		int onum =  Integer.parseInt(mr.getParameter("onum"));
		int pnum =  Integer.parseInt(mr.getParameter("pnum"));
		int num =  Integer.parseInt(mr.getParameter("num"));
		String id = mr.getParameter("id");
		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String orgfilename = mr.getOriginalFileName("file1");   //전송된 파일명
		String savefilename = mr.getFilesystemName("file1");  //저장된 파일명	

		File file1 = mr.getFile("file1");
		ReviewDao dao = ReviewDao.getInstance();
		ReviewDto dto = new ReviewDto(onum, pnum, num, 0, id, name, title, content, null, orgfilename, savefilename, null);		
		
		
		
		int n = dao.writeReview(dto);
		
		
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/review/listReview.do?pnum="+pnum);  
			//(req.getContextPath()+"&pnum="+pnum);				
		}else {
			resp.sendRedirect(req.getContextPath()+"/review/reviewResult2.jsp");
		}
	}
}
