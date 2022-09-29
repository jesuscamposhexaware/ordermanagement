package com.jcampos.ordermanagement.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AddressDtoTest {

	@Test
    public void testGetsAddressDtoParameters() throws Exception {
		AddressDto addressDto = new AddressDto();
		addressDto.getCity();
		addressDto.getCountry();
		addressDto.getIdAddress();
		addressDto.getIdUser();
		addressDto.getState();
		addressDto.getSteetAddress();
		addressDto.getZipCode();
		assertNotNull(addressDto);
    }
	
	@Test
    public void testSetsAddressDtoParameters() throws Exception {
		AddressDto addressDto = new AddressDto();
		addressDto.setCity("");
		addressDto.setCountry("");
		addressDto.setIdAddress(1l);
		addressDto.setIdUser(1l);
		addressDto.setState("");
		addressDto.setSteetAddress("");
		addressDto.setZipCode(1);
		assertNotNull(addressDto);
    }
	
	@Test
    public void testAddressDtoParameters() throws Exception {
		AddressDto addressDto = new AddressDto();
		
		AddressDto addressDto2 = new AddressDto();
		
		assertNotNull(addressDto.toString());
		assertNotNull(addressDto.hashCode());
		assertTrue(addressDto.equals(addressDto2));
    }
}