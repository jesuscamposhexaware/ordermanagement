package com.jcampos.ordermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jcampos.ordermanagement.dto.ProductDto;
import com.jcampos.ordermanagement.service.intf.IProductService;

@Service
public class ProductService implements IProductService {

	@Override
	public List<ProductDto> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto getProductById(Integer idProduct) {
		// TODO Auto-generated method stub
		return null;
	}

}
