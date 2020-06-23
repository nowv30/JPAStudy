package jpaMapping.mappingTest;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpaMapping.mappingTest.domain.Member;
import jpaMapping.mappingTest.domain.Team;

public class MappingMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMapping");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		
		try {
			
			//3. 연관관계의 주인------------------------
			// 테이블의 외래키를 가지고 있는 곳이 주인이다.
			// Member 엔티티의 Team 객체가 주인. 진짜매핑 등록과 수정이 된다.
			// Team 엔티티의 List<member>가 가짜매핑 읽기만 가능.
			
		// team에 member를 추가할 때, team.getMembers().add(member);하면 member에 team_id 테이블에 null 이 뜬다. 값이 안들어간다는 뜻
		// member 엔티티에 team을 지정해야 한다. member.setTeam(팀이름);을 입력하면 팀에서 맴버들을 조회(읽어올)수 있다.
			
			Team team = new Team();
			team.setName("newTeam");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("newMember");
			// 순수한 객체 관계를 고려하면 양쪽 다 값을 입력해줘야 한다.
			team.getMembers().add(member);
			//연관관계 주인에 값 설정
			member.setTeam(team);
			
			em.persist(member);

			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
