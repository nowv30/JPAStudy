package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="S_MEMBER")
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)//기본값, 생략가능
	@Column(name="MEMBER_ID")
	private long id;
	
	private String name;
	
	private String city;
	
	private String street;
	
	private String zipcode;
}
