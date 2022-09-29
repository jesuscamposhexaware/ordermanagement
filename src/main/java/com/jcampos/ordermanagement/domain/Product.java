package com.jcampos.ordermanagement.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Product {

	@Id
	@Column(name = "id_product")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "stock")
	private Integer stock;
	
	@Column(name = "picture")
	private String picture;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(idProduct, other.idProduct)
				&& Objects.equals(name, other.name) && Objects.equals(picture, other.picture)
				&& Objects.equals(price, other.price) && Objects.equals(stock, other.stock);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, idProduct, name, picture, price, stock);
	}
	
	
	
}
