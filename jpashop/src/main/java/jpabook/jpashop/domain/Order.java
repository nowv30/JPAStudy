package jpabook.jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="ORDERS")
public class Order {
	
	@Id @GeneratedValue
	@Column(name="ORDER_ID")
	private long id;
	
	@Column(name="MEMBER_ID")
	private long memberId;
	
	private LocalDateTime orderdate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
}
