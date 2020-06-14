package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="ORDERS")
public class Order {
	
	@Id @GeneratedValue
	@Column(name="ORDER_ID")
	private long id;
	
	@ManyToOne//다대일 관계(order가 n, member가 1)
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	//비즈니스 적으로 가치있다.
	//주문서를 중심으로 연관된 아이템 목록을 찾는 경우가 있을 수 있다.
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	private LocalDateTime orderdate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	//연관관계 편의메소드(양방향 연관관계 설정 시 실수하지 않도록 해준다.)
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
		
	}
}
