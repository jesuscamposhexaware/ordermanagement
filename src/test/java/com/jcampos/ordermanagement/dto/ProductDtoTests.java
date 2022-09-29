package com.jcampos.ordermanagement.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductDtoTests {

	@Test
    public void testGetsAddressDtoParameters() throws Exception {
		ProductDto productDto = new ProductDto();
		productDto.getDescription();
		productDto.getIdProduct();
		productDto.getName();
		productDto.getName();
		productDto.getPicture();
		productDto.getPrice();
		productDto.getStock();
		assertNotNull(productDto);
    }
	
	@Test
    public void testSetsAddressDtoParameters() throws Exception {
		ProductDto productDto = new ProductDto();
		productDto.setDescription("");
		productDto.setIdProduct(1L);
		productDto.setName("");
		productDto.setName("");
		productDto.setPicture("");
		productDto.setPrice(1.1);
		productDto.setStock(1);
		assertNotNull(productDto);
    }
	
	@Test
    public void testAddressDtoParameters() throws Exception {
		ProductDto productDto = new ProductDto();
		
		ProductDto productDto2 = new ProductDto();
		
		assertNotNull(productDto.toString());
		assertNotNull(productDto.hashCode());
		assertTrue(productDto.equals(productDto2));
    }
}