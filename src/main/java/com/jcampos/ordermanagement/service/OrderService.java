package com.jcampos.ordermanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcampos.ordermanagement.converter.DtoToOrderConverter;
import com.jcampos.ordermanagement.converter.OrderToDtoConverter;
import com.jcampos.ordermanagement.domain.Order;
import com.jcampos.ordermanagement.dto.OrderDto;
import com.jcampos.ordermanagement.repository.OrderRepository;
import com.jcampos.ordermanagement.service.intf.IOrderService;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderToDtoConverter orderToDtoConverter;
	
	@Autowired
	private DtoToOrderConverter dtoToOrderConverter;

	@Override
	public List<OrderDto> getOrdersByUserId(Long idUser) {
		
		return orderRepository.findByUserIdUser(idUser)
				.stream()
				.map(o -> orderToDtoConverter.convert(o))
				.collect(Collectors.toList());
		
	}

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		
		Order order = dtoToOrderConverter.convert(orderDto);
		orderRepository.save(order);
		
		return orderToDtoConverter.convert(order);
		
	}

	@Override
	public void deleteOrder(Long idOrder) {
		
		Optional<Order> order = orderRepository.findById(idOrder);
		
		if(order.isPresent())
			orderRepository.delete(order.get());
		
	}

	@Override
	public OrderDto updateOrder(Long id, OrderDto orderDto) {
		
		Optional<Order> order = orderRepository.findById(id);
		
		if(order.isPresent()) {
			Order inputOrder = dtoToOrderConverter.convert(orderDto);
			return orderToDtoConverter.convert(orderRepository.save(inputOrder));
		}
		return null;
	}

}
