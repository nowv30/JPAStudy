package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)//singleTable, joined 
@DiscriminatorColumn
public class Item extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name="ITEM_ID")
	private long id;
	
	@ManyToMany(mappedBy="items")
	private List<Category> categories = new ArrayList<Category>();
	
	private String name;
	
	private int price;
	
	private int stockQuanty;
	
}
