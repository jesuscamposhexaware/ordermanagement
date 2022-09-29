package com.jcampos.ordermanagement.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderDtoTests {

	@Test
    public void testGetsAddressDtoParameters() throws Exception {
		OrderDto orderDto = new OrderDto();
		orderDto.getCity();
		orderDto.getCountry();
		orderDto.getCreatedAt();
		orderDto.getGiftMessage();
		orderDto.getGiftMessageType();
		orderDto.getIdOrder();
		orderDto.getIdUser();
		orderDto.getOrderDetails();
		orderDto.getReceiverName();
		orderDto.getState();
		orderDto.getStatus();
		orderDto.getStreetAddress();
		orderDto.getTotal();
		orderDto.getUpdatedAt();
		orderDto.getZipCode();
		
		assertNotNull(orderDto);
    }
	
	@Test
    public void testSetsAddressDtoParameters() throws Exception {
		OrderDto orderDto = new OrderDto();
		orderDto.setCity("");
		orderDto.setCountry("");
		orderDto.setCreatedAt("");
		orderDto.setGiftMessage("");
		orderDto.setGiftMessageType("");
		orderDto.setIdOrder(1l);
		orderDto.setIdUser(1l);
		orderDto.setOrderDetails(new ArrayList<>());
		orderDto.setReceiverName("");
		orderDto.setState("");
		orderDto.setStatus("");
		orderDto.setStreetAddress("");
		orderDto.setTotal(1d);
		orderDto.setUpdatedAt("");
		orderDto.setZipCode("");
		
		assertNotNull(orderDto);
    }
	
	@Test
    public void testAddressDtoParameters() throws Exception {
		OrderDto orderDto = new OrderDto();
		
		OrderDto orderDto2 = new OrderDto();
		
		assertNotNull(orderDto.toString());
		assertNotNull(orderDto.hashCode());
		assertTrue(orderDto.equals(orderDto2));
		assertFalse(orderDto.equals(null));
    }
}