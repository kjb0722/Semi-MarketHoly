package Cart.market.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.market.pay.dao.PayDao;
import com.market.pay.dto.PayDto;
@WebServlet("/pay.do")
public class PayController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PayDao dao=new PayDao();
	PayDto dto=new PayDto(onum, num, opnum, status, pay_yn, reg_date, end_date, id, price, use_point, sale_price, pay_way, pnum, EA, addr, pname)
	dao.insertdrders(dto);	
	dao.insertorderproduct(dto);
	

}
