# 프록시

- em.find 말고 em.getReference메소드가 있다.
- em.find()는 데이터베이스를 통해서 실제 엔티티 객체를 조회한다.
-em.getReference()는 데이터베이스 조회를 미루는 가짜(프록시)엔티티 객체를 조회한다. db에 쿼리가 안날라가도 객체가 조회된다.

## 프록시 특징
- 실제 클래스를 상속 받아서 만들어짐
- 실제 클래스와 겉 모양이 같다.
- 사용하는 입장에서는 진짜 객체인지 프록시 객체인지 구분하지 않고 사용하면 된다.(이론상)

- 프록시 객체는 실제 객체의 참조(target)를 보관
- 프록시 객체를 호출하면 프록시 객체는 실제 객체의 메소드 호출

- 프록시 객체는 처음 사용할 때 한 번만 초기화
- **프록시 객체를 초기화할 때, 프록시객체가 실제 엔티티로 바뀌는 것이 아님**
- 초기화되면 프록시 객체를 통해서 실제 엔티티에 접근 가능

- 프록시 객체는 원본 엔티티를 상속받음. 따라서 타입 체크시 주의해야함(==비교 안된다. instance of 사용한다.)
```java
//em.find()로 찾은 값을 담은 객체를 타입 비교(==)
Member member1 = new Member();
			member1.setUsername("member1");
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			em.persist(member2);
			
			em.flush();
			em.clear();
			

			Member m1 = em.find(Member.class, member1.getId());
			Member m2 = em.find(Member.class, member2.getId());
			
			System.out.println("m1==m2"+(m1.getClass()==m2.getClass()));

//콘솔창
//m1==m2true 둘은 같은 타입이다.
///////////////////////////////////////////////////////

//em.getReference()와 비교할 때
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
			
			System.out.println("m1==m2"+(m1.getClass()==m2.getClass()));
//콘솔창
//m1==m2false 둘은 다른 타입이다.
///////////////////////////////////////////////////////////

// 타입 비교

//...생략
System.out.println("m1==m2: "+(m1 instanceof Member));
System.out.println("m1==m2: "+(m2 instanceof Member));
//콘솔창
//m1==m2: true
//m1==m2: true
```

- 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티를 반환한다.

- 준영속 상태일 때 프록시를 초기화하면 문제발생(LazyInitializationException 예외)

## 프록시 객체의 초기화
- em.getReference(Member.class, member.Id), member.getName() 실행 시 과정
1. 클라이언트 getName 요청시 MemberProxy 객체를 가져온다.
2. MemberProxy에 참조(target) 값이 없으면 영속성 컨텍스트에 Member객체를 요청한다.(초기화 요청)
3. 영속성 컨텍스트가 DB를 조회하고 값을 가져온다.
4. 가져온 값으로 실제 Entity를 생성한다.
5. Entity에서 target.getName()으로 Member의 이름을 받아온다.
6. 초기화 후에는 호출시 target의 값을 그냥 바로 가져온다.

## 프록시 확인
- 프록시 인스턴스의 초기화 여부 확인
  PersistenceUnitUtil().isLoaded(Object entity)
- 프록시 클래스 확인 방법
  entity.getClass.getName() 출력
- 프록시 강제 초기화
  org.Hibernate.initialize(entity);
- 참고: JPA 표준은 강제 초기화 없음
  강제호출 : member.getName()
