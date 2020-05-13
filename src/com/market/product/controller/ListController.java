package com.market.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.product.dao.ProductDao;
import com.market.product.dto.ProductDto;


@WebServlet("/product/list.do")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//index에서 cnum넘어오면 name 가져올수 있음 만약 type이 -1이 아니면 cnum이 type 인 애(대분류)도 가져오기
		int cnum=Integer.parseInt(req.getParameter("cnum"));
		int type=Integer.parseInt(req.getParameter("type"));
		
		String filter=req.getParameter("filter");
		String spageNum=req.getParameter("pageNum");
		String list_filter=req.getParameter("list_filter");
		String keyword=req.getParameter("keyword");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		ProductDao dao=new ProductDao();
		ArrayList<ProductDto>list=dao.getList(startRow, endRow,filter);
		
		int pageCount=(int)Math.ceil(dao.getCount()/5.0);
		int startPageNum=((pageNum-1)/4)*4+1;
		int endPageNum=startPageNum+3;
		if(pageCount<endPageNum) {
			endPageNum=pageCount;
		}
		
		req.setAttribute("type", type);
		req.setAttribute("cnum", cnum);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("list_filter", list_filter);
		req.setAttribute("keyword", keyword);
		req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
		/*
		JSONArray jarr=new JSONArray();
		for(CommentsVo vo:list){
			JSONObject json=new JSONObject();
			json.put("num",vo.getNum());
			json.put("mnum",vo.getMnum());
			json.put("id",vo.getId());
			json.put("comments",vo.getComments());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);	
		*/
	}
	
}
