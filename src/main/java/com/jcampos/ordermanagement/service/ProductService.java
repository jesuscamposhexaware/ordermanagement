package com.jcampos.ordermanagement.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jcampos.ordermanagement.constant.ErrorMessage;
import com.jcampos.ordermanagement.converter.ProductToDtoConverter;
import com.jcampos.ordermanagement.domain.Product;
import com.jcampos.ordermanagement.dto.ProductDto;
import com.jcampos.ordermanagement.repository.ProductRepository;
import com.jcampos.ordermanagement.service.intf.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductToDtoConverter productToDtoConverter;
	
	@Override
	public List<ProductDto> getAllProducts() {
		
		return productRepository.findAll()
				.stream()
				.map(p -> productToDtoConverter.convert(p))
				.collect(Collectors.toList());
		
	}

	@Override
	public ProductDto getProductById(Long idProduct) {
		Optional<Product> product = productRepository.findById(idProduct);
		
		if(product.isPresent())
			return productToDtoConverter.convert(product.get());
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				MessageFormat.format(ErrorMessage.PRODUCT_NOT_FOUND, idProduct));
	}

}
