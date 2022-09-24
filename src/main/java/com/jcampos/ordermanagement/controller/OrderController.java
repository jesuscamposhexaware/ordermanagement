package com.jcampos.ordermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcampos.ordermanagement.controller.intf.IOrderController;
import com.jcampos.ordermanagement.dto.OrderDetailDto;
import com.jcampos.ordermanagement.dto.OrderDto;

@RestController
@RequestMapping("/order")
public class OrderController implements IOrderController {
	
	@GetMapping("/{id}")
	@Override
	public List<OrderDetailDto> getDetailsByOrderId(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping
	@Override
	public List<OrderDto> getByUserId(@RequestParam Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping
	@Override
	public OrderDto create(@Valid @RequestBody OrderDto orderDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping("/{id}")
	@Override
	public OrderDto delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@PutMapping("/{id}")
	@Override
	public OrderDto update(@PathVariable Integer id, @Valid @RequestBody OrderDto orderDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
