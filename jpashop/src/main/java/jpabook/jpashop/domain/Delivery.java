package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Delivery {
	
	@Id @GeneratedValue
	@Column(name="DELIVERY_ID")
	private long id;
	
	@OneToOne
	@JoinColumn(name="ORDER_ID")
	private Order order;
	
	private String city;
	private String street;
	private String zipcode;
	private DeliveryStatus status;
	
}
