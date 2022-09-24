package com.jcampos.ordermanagement.service.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.ProductDto;

public interface IProductService {
	
	public List<ProductDto> getAllProducts();
	
	public ProductDto getProductById(Integer idProduct);

}
