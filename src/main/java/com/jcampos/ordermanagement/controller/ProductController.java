package com.jcampos.ordermanagement.controller;

import java.util.List;

import com.jcampos.ordermanagement.dto.ProductDto;

public interface ProductController {
	
	public List<ProductDto> getAll();
	
	public ProductDto getById(Long idProduct);

}
