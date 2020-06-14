package jpaMapping.mappingTest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
}
