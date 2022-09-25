package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;

import com.jcampos.ordermanagement.domain.Product;
import com.jcampos.ordermanagement.dto.ProductDto;

public class ProductToDtoConverter implements Converter<Product, ProductDto>{

	@Override
	public ProductDto convert(Product source) {
		// TODO Auto-generated method stub
		return null;
	}

}
