package jpaMapping.mappingTest.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class MemberProduct {
	@Id @GeneratedValue
	private long id;
	
	//ManyToMany를 사용하기 어렵다(연결테이블에 추가할 필드가 생기는데 추가할 수 없다.)
	//연결테이블을 엔티티로 승격(엔티티로 만든다)시키고 연결테이블을 연관관계 주인으로 하면 이 테이블에 필드를 마음대로 추가할 수 있다.
	//연결테이블 엔티티를 만들고 @ManyToOne, OneToMany(member, product)를 추가한다.
	//
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	//마음대로 추가한다.
	private int count;
	private int price;
	
	private LocalDateTime orderDateTime;
}
