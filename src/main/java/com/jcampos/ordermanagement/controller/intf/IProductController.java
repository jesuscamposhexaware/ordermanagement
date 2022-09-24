package com.jcampos.ordermanagement.controller.intf;

import java.util.List;

import com.jcampos.ordermanagement.dto.ProductDto;

public interface IProductController {
	
	public List<ProductDto> getAll();
	
	public ProductDto getById(Integer idProduct);

}
