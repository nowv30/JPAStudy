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
			//팀과 멤버 정보를 넣는다
			Team team = new Team();
			team.setName("TeamA");
			//persist실행하면서 id값 생성
			em.persist(team);
			
			//멤버정보를 넣으면서 팀에 소속시킨다.
			//연관관계가 없어서 외레키 식별자를 직접 다룬다.
			Member member = new Member();
			member.setUsername("member1");
			member.setTeamId(team.getId());
			em.persist(member);
			
			//멤버를 찾아온다.
			Member findMember = em.find(Member.class, member.getId());
			
			//멤버의 소속팀을 알고싶다.
			//연관관계라는 게 없어서 DB에서 값을 계속 가져와야한다. 객체지향적이지 않다.
			long findTeamId= findMember.getTeamId();
			Team findTeam = em.find(Team.class, findTeamId);
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
