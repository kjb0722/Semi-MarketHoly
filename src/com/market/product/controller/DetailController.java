package com.market.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.market.admin.dto.ProdInfoDto;
import com.market.product.dao.ProductDao;
import com.market.product.dto.ProductDto;
import com.market.review.dao.ReviewDao;
import com.market.review.dto.ReviewDto;

@WebServlet("/product/detail.do")
public class DetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		String sPnum = req.getParameter("pnum");
		int pnum = 1;
		if (sPnum != null) {
			pnum = Integer.parseInt(req.getParameter("pnum"));
		}
		String sCnum = req.getParameter("cnum");
		int cnum = 1;
		if (sCnum != null) {
			cnum = Integer.parseInt(sCnum);
		}
		ProductDao dao = new ProductDao();
		ProductDto dto = dao.getDetail(pnum);

		ProdInfoDto prodInfo = dao.getProdInfo(pnum);

		req.setAttribute("dto", dto);
		req.setAttribute("id", id);
		req.setAttribute("plag", 1);
		req.setAttribute("cnum", cnum);
		req.setAttribute("prodInfo", prodInfo);

		req.getRequestDispatcher("/index.jsp?page=product/detail.jsp").forward(req, resp);
		// 여기서 또 값을 넘겨준다.
	}
}
