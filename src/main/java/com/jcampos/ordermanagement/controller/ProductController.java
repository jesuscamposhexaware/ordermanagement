package com.jcampos.ordermanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcampos.ordermanagement.controller.intf.IProductController;
import com.jcampos.ordermanagement.dto.ProductDto;

@RestController
@RequestMapping("/product")
public class ProductController implements IProductController {

	@Override
	@GetMapping
	public List<ProductDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/{id}")
	public ProductDto getById(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
