package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="S_MEMBER")
public class Member extends BaseEntity{

	@Id @GeneratedValue(strategy = GenerationType.AUTO)//기본값, 생략가능
	@Column(name="MEMBER_ID")
	private long id;
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();//관례상 ArrayList로 쓴다. 데이터 없이 입력해서 널포인트 에러 생기지 않는다.
	
	private String name;
	
	private String city;
	
	private String street;
	
	private String zipcode;
	
}
