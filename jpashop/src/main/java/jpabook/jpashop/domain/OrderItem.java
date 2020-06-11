package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class OrderItem {
	
	@Id @GeneratedValue @Column(name="ORDER_ITEM_ID")
	private long id;
	
	@Column(name="ORDER_ID")
	private long orderId;
	
	@Column(name="ITEM_ID")
	private long itemId;
	
	private int orderPrice;
	
	private int count;
}
