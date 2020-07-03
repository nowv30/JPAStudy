package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Category {
	
	@Id @GeneratedValue
	@Column(name="CATEGORY_ID")
	private long id;
	
	//다대다 매핑
	//중간테이블을 만든다고 생각
	//실전에선 manytomany 쓰지말고 중간테이블 이용해서 1:n, n:1로 만들어라
	@ManyToMany
	@JoinTable(name="CATEGORY_ITEM",
			joinColumns = @JoinColumn(name="CATEGORY_ID"),//내가 조인할 것
			inverseJoinColumns = @JoinColumn(name="ITEM_ID"))//반대쪽이 조인할 것
	private List<Item> items = new ArrayList<Item>();
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	private Category parent;
	
	@OneToMany(mappedBy="parent")
	private List<Category> child = new ArrayList<>();
}
