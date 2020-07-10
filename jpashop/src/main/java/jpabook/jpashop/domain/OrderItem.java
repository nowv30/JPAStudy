package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class OrderItem extends BaseEntity{
	
	@Id @GeneratedValue @Column(name="ORDER_ITEM_ID")
	private long id;
	
	@ManyToOne//다대일 관계
	@JoinColumn(name="ORDER_ID")
	private Order order;
	
	@ManyToOne//다대일관계
	@JoinColumn(name="ITEM_ID")
	private Item item;
	
	private int orderPrice;
	
	private int count;

	public void add(OrderItem orderItem) {
		// TODO Auto-generated method stub
		
	}
	
	
}
