package jpaMapping.mappingTest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	@Column(name="TEAM_ID")
	private long teamId;
}
