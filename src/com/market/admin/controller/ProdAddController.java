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
import com.market.admin.dto.ProdInfoDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/admin/prodAdd.do")
public class ProdAddController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext app = req.getServletContext();
		String path = app.getRealPath("/img");
		MultipartRequest mr = new MultipartRequest(req, path, 1024 * 1024 * 50, "utf-8", new DefaultFileRenamePolicy());
		String catType = mr.getParameter("catType");
		int cnum = Integer.parseInt(catType.substring(0,catType.indexOf("|")));
		int type = Integer.parseInt(catType.substring(catType.indexOf("|")+1));
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
		
		String unit = mr.getParameter("unit");
		String volume = mr.getParameter("volume");
		String origin = mr.getParameter("origin");
		String pack_type = mr.getParameter("pack_type");
		String shelf_life = mr.getParameter("shelf_life");
		String guidance = mr.getParameter("guidance");
		ProdInfoDto infoDto = new ProdInfoDto(-1, unit, volume, origin, pack_type, shelf_life, guidance);
		
		int n = dao.insProd(dto, infoDto);
		if (n > 0) {
			resp.sendRedirect(req.getContextPath() + "/admin/product.do");
		} else {
			resp.sendRedirect(req.getContextPath() + "/error.do");
		}
	}
}
