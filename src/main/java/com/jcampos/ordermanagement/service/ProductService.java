package com.jcampos.ordermanagement.service;

import java.util.List;

import com.jcampos.ordermanagement.dto.ProductDto;

public interface ProductService {
	
	public List<ProductDto> getAllProducts();
	
	public ProductDto getProductById(Long idProduct);

}
