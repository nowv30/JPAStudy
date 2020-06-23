package jpaMapping.mappingTest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Locker {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	@OneToOne(mappedBy="locker")
	private Member member;
}
