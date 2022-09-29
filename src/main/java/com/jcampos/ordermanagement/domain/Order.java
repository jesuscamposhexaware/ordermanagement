package com.jcampos.ordermanagement.domain;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table( name ="`order`")
public class Order {

	@Id
	@Column(name = "id_order")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@Column(name = "status")
	private String status;
	
	//@Lob
	@Column(name = "gift_message")
	private String giftMessage;
	
	@Column(name = "gift_message_type")
	private String giftMessageType;
	
	@Column(name = "receiver_name")
	private String receiverName;
	
	@Column(name = "total")
	private Double total;
	
	@Column(name = "street_address")
	private String streetAddress;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "created_at")
	private Instant createdAt;
	
	@Column(name = "updated_at")
	private Instant updatedAt;
	
	@Transient
	private List<OrderDetail> orderDetails;
}
