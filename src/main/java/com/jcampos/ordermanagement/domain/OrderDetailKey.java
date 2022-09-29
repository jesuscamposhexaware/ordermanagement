package com.jcampos.ordermanagement.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class OrderDetailKey implements Serializable{
	
	private static final long serialVersionUID = 6341801785411784451L;

	@ManyToOne
	@JoinColumn(name = "id_order")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;

}
