package com.jcampos.ordermanagement.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcampos.ordermanagement.controller.ProductController;
import com.jcampos.ordermanagement.dto.ProductDto;
import com.jcampos.ordermanagement.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements ProductController {

	@Autowired
	private ProductService productService;
	
	@Override
	@GetMapping
	public List<ProductDto> getAll() {
		return productService.getAllProducts();
	}

	@Override
	@GetMapping("/{id}")
	public ProductDto getById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

}
