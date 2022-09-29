package com.jcampos.ordermanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="orderdetail")
public class OrderDetail {

	@EmbeddedId
	private OrderDetailKey key;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "sold_price")
	private Double soldPrice;
	
	@Column(name = "subtotal")
	private Double subtotal;
}
