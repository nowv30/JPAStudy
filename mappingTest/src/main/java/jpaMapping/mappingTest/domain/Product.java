package jpaMapping.mappingTest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Product {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	//실무에서 다대다 매핑을 사용하지 않는다.
	//연결 테이블, 중간테이블(Member_Product)이 단순히 연결만 하고 끝나지 않음
	//주문시간 수량 같은 데이터가 추가로 들어갈 수 있는데, 연결테이블에는 매핑 정보만 들어간다.
	//연결 테이블용 엔티티를 직접 추가하는 것이 좋다(@ManyToMany => @OneToMany, @ManyToOne)
	@ManyToMany(mappedBy = "products")
	private List<Member> members = new ArrayList<>();
}
