package com.jcampos.ordermanagement.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jcampos.ordermanagement.domain.Product;
import com.jcampos.ordermanagement.dto.ProductDto;

@Component
public class ProductToDtoConverter implements Converter<Product, ProductDto>{

	@Override
	public ProductDto convert(Product source) {
		if(source == null)
			return null;
		
		ProductDto target = new ProductDto();
		target.setIdProdcut(source.getIdProduct());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setPrice(source.getPrice());
		target.setStock(source.getStock());
		target.setPicture(source.getPicture());
		
		return null;
	}

}
