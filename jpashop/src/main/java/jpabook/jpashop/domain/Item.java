package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Item {
	
	@Id @GeneratedValue
	@Column(name="ITEM_ID")
	private long id;
	
	private String name;
	
	private int price;
	
	private int stockQuanty;
	
}
