package jpaMapping.mappingTest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Team {

	@Id @GeneratedValue
	@Column(name="TEAM_ID")
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy="team")//팀 입장에서 하나, 회원 입장에서 여럿. One => Many / mappedBy는 내가 Member의 team으로 연결되있다는 뜻 
	List<Member> members = new ArrayList<Member>();//ArrayList 초기화가 관례. add할 때 널포인트에러 안난다.
}
