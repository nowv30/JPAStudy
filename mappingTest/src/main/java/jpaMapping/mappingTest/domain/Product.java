package jpaMapping.mappingTest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Product {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "product")
	private List<MemberProduct> memberProducts = new ArrayList<>();
	
	private int count;
}
