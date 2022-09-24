package com.jcampos.ordermanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity
public class OrderDetail {

	@EmbeddedId
	private OrderDetailKey idOrderDetail;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "sold_price")
	private Double soldPrice;
	
	@Column(name = "subtotal")
	private Double subtotal;
}
