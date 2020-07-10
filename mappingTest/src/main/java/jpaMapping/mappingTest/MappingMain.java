package jpaMapping.mappingTest;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpaMapping.mappingTest.domain.Member;


public class MappingMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMapping");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		
		try {
			Member member1 = new Member();
			member1.setUsername("member1");
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			em.persist(member2);
			
			em.flush();
			em.clear();
			

			Member m1 = em.find(Member.class, member1.getId());
			Member m2 = em.getReference(Member.class, member2.getId());
			
			System.out.println("m1==m2: "+(m1 instanceof Member));
			System.out.println("m1==m2: "+(m2 instanceof Member));
			
//			Member findMember = em.getReference(Member.class, member1.getId());			
			//출력되는 값은 프록시라 하는 가짜 엔티티가 출력된다.
			//findMember= class jpaMapping.mappingTest.domain.Member$HibernateProxy$Zi2bbZbV
//			System.out.println("findMember= "+findMember.getClass());
			
//			System.out.println("findMember.Id= "+findMember.getId());
//			System.out.println("findMember.User="+findMember.getUsername());
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
