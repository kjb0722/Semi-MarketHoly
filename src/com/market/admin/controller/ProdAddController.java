package com.market.admin.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.admin.dao.ProdDao;
import com.market.admin.dto.ProdDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/admin/prodAdd.do")
public class ProdAddController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext app = req.getServletContext();
		String path = app.getRealPath("/img");
		MultipartRequest mr = new MultipartRequest(req, path, 1024 * 1024 * 50, "utf-8", new DefaultFileRenamePolicy());
		int cnum = Integer.parseInt(mr.getParameter("cat"));
		int type = Integer.parseInt(mr.getParameter("catType"));
		int price = Integer.parseInt(mr.getParameter("price"));
		int stock = Integer.parseInt(mr.getParameter("stock"));
		String name = mr.getParameter("name");
		String description = mr.getParameter("description");
		String thumb_org = mr.getOriginalFileName("thumb");
		if (thumb_org == null) {
			thumb_org = "";
		}
		String thumb_save = mr.getFilesystemName("thumb");
		if (thumb_save == null) {
			thumb_save = "";
		}
		String detail_org = mr.getOriginalFileName("thumb");
		if (detail_org == null) {
			detail_org = "";
		}
		String detail_save = mr.getFilesystemName("detail");
		if (detail_save == null) {
			detail_save = "";
		}
		ProdDao dao = ProdDao.getInstance();
		ProdDto dto = new ProdDto();
		dto.setCnum(cnum);
		dto.setType(type);
		dto.setName(name);
		dto.setPrice(price);
		dto.setStock(stock);
		dto.setDescription(description);
		dto.setThumb_org(thumb_org);
		dto.setThumb_save(thumb_save);
		dto.setDetail_org(detail_org);
		dto.setDetail_save(detail_save);
	
		int n = dao.insProd(dto);
		if (n > 0) {
			resp.sendRedirect(req.getContextPath() + "/admin/product.do");
		} else {
			resp.sendRedirect(req.getContextPath() + "/error.do");
		}
	}
}
