package com.jcampos.ordermanagement.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcampos.ordermanagement.controller.OrderController;
import com.jcampos.ordermanagement.dto.OrderDto;
import com.jcampos.ordermanagement.service.OrderService;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
	@Override
	public OrderDto getById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}

	@GetMapping("/user/{value}")
	@Override
	public List<OrderDto> getByUserId(@PathVariable Long value) {
		return orderService.getOrdersByUserId(value);
	}

	@PostMapping
	@Override
	public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto orderDto) {
		return new ResponseEntity<OrderDto>(orderService.createOrder(orderDto),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> delete(Long id) {
		orderService.deleteOrder(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	@Override
	public OrderDto update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
		return orderService.updateOrder(id, orderDto);
	}

}
