package com.jcampos.ordermanagement.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderDetailDtoTests {

	@Test
    public void testGetsAddressDtoParameters() throws Exception {
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		orderDetailDto.getIdOrder();
		orderDetailDto.getIdProduct();
		orderDetailDto.getName();
		orderDetailDto.getPrice();
		orderDetailDto.getQuantity();
		orderDetailDto.getStock();
		orderDetailDto.getSubtotal();
		assertNotNull(orderDetailDto);
    }
	
	@Test
    public void testSetsAddressDtoParameters() throws Exception {
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		orderDetailDto.setIdOrder(1l);
		orderDetailDto.setIdProduct(1l);
		orderDetailDto.setName("");
		orderDetailDto.setPrice(1D);
		orderDetailDto.setQuantity(1);
		orderDetailDto.setStock(1);
		orderDetailDto.setSubtotal(1d);
		assertNotNull(orderDetailDto);
    }
	
	@Test
    public void testAddressDtoParameters() throws Exception {
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		OrderDetailDto orderDetailDto2 = new OrderDetailDto();
		
		assertNotNull(orderDetailDto.toString());
		assertNotNull(orderDetailDto.hashCode());
		assertTrue(orderDetailDto.equals(orderDetailDto2));
    }
}