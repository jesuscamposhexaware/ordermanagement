package com.jcampos.ordermanagement.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import com.jcampos.ordermanagement.constant.ErrorMessage;
import com.jcampos.ordermanagement.converter.ProductToDtoConverter;
import com.jcampos.ordermanagement.domain.Product;
import com.jcampos.ordermanagement.dto.ProductDto;
import com.jcampos.ordermanagement.repository.ProductRepository;
import com.jcampos.ordermanagement.service.impl.ProductServiceImpl;

public class ProductServiceTests {


	private static ProductRepository productRepository;

	private static ProductToDtoConverter productToDtoConverter;
	
	private static ProductServiceImpl productServiceImpl;
	
	@BeforeAll
	public static void init(){
		productToDtoConverter = mock(ProductToDtoConverter.class); 
		
		productRepository = mock(ProductRepository.class);
		
		productServiceImpl = new ProductServiceImpl(productRepository, productToDtoConverter);
	}

	@Test
	public void  getAllProducts_ok() {

		ProductDto productorDto = new ProductDto();

		when(productToDtoConverter.convert(null)).thenReturn(productorDto);

		assertNotNull(productServiceImpl.getAllProducts());
	}
	
	@Test
	public void  getProductById_NotFound() {

		Optional<Product> orderOpt = Optional.empty();
		Long idProduct = Mockito.anyLong();
		
		when(productRepository.findById(idProduct)).thenReturn(orderOpt);

		ResponseStatusException responseStatusException =  assertThrows(ResponseStatusException.class, () -> {
			productServiceImpl.getProductById((idProduct));
		});

		String expectedMessage = MessageFormat.format(ErrorMessage.PRODUCT_NOT_FOUND, idProduct) ;
		String actualMessage = responseStatusException.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}
	
}
