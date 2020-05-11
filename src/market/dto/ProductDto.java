package market.dto;

import java.util.Vector;

public class ProductDto {
	public Vector<ProductDto> getProductList(){
		Vector<ProductDto> list=new Vector<ProductDto>();
		return list;
	}
	public ProductDto getProduct(int num) {
		ProductDto dto=new ProductDto();
		return dto;
	}
}
