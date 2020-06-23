package jpaMapping.mappingTest.domain;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="M_MEMBER")
public class Member {
	@Id @GeneratedValue
	@Column(name="MEMBER_ID")
	private long id;
	
	@Column(name="USER_NAME")
	private String username;
	
//	@Column(name="TEAM_ID")
//	private long teamId;
	
	
	//멤버입장에서Many, 팀입장에서One
	//Many => One 방향
	@ManyToOne
	//Team테이블에 Id와 조인. team과 TEAM_ID왜래키와 연관관계 매핑
	@JoinColumn(name="TEAM_ID")
	private Team team;
	
	@OneToOne
	@JoinColumn(name="LOCKER_ID")
	private Locker locker;
	//실무에서 다대다 매핑을 사용하지 않는다.
	//연결 테이블, 중간테이블(Member_Product)이 단순히 연결만 하고 끝나지 않음
	//주문시간 수량 같은 데이터가 추가로 들어갈 수 있는데, 연결테이블에는 매핑 정보만 들어간다.		//연결 테이블용 엔티티를 직접 추가하는 것이 좋다(@ManyToMany => @OneToMany, @ManyToOne)
	@ManyToMany
	@JoinTable(name="MEMBER_PRODUCT")
	private List<Product> products = new ArrayList<>();
}
