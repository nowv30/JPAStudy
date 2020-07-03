package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JPAMAIN {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		try {
			
			//예시코드 : 양방향 연관관계 Order OrderItem
			//Order order = new Order();
			//order.addOrderItem(new OrderItem());
			
			//예시코드 : 위의 양방향 관계 없이 코드를 짤 수 있다.
			//Order에 입력한 List를 사용하지 않아도 된다.
//			Order order = new Order();
//			em.persist(order);
//			
//			OrderItem orderItem = new OrderItem();
//			orderItem.setOrder(order);
//			em.persist(orderItem);
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			
		}finally {
			em.close();
			emf.close();

	}

	}
}
