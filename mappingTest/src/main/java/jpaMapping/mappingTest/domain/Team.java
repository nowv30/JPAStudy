package jpaMapping.mappingTest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Team {

	@Id @GeneratedValue
	@Column(name="TEAM_ID")
	private long id;
	
	private String name;
}
