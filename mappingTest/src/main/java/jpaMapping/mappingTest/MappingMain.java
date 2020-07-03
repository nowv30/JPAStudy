package jpaMapping.mappingTest;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpaMapping.mappingTest.domain.Member;
import jpaMapping.mappingTest.domain.Movie;
import jpaMapping.mappingTest.domain.Team;

public class MappingMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMapping");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		
		try {
			
			Movie movie = new Movie();
			movie.setDiractor("aaa");
			movie.setActor("bbb");
			movie.setName("ccc");
			movie.setPrice(10000);

			em.persist(movie);
			em.flush();
			em.clear();
			
			Movie findMovie = em.find(Movie.class, movie.getId());
			System.out.println("findMovie="+findMovie);
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
