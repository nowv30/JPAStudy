# 상속관계 매핑
- 관계형 데이터베이스는 상속관계라는 개념이 없다.
- 슈퍼타입 서브타입 관계라는 모델링 기법이 객체 상속과 유사하다.
- 상속관계 매핑 : 객체의 상속과 구조와 DB의 슈퍼타입 서브타입 관계를 매핑한다.

## 상속관계 매핑 종류
* 3 전략으로 생성되는 테이블은 객체입장에서는 모두 매핑 가능하다.
- 조인 전략
- 단일 테이블 전략
- 구현 클래스마다 테이블 전략


### * Item 클래스를 상속받는 Movie, Album, Book 클래스를 만든다.
 ```java
//Item.java
@Entity
@Data
@Inheritance(strategy =)//JOINED, SINGLE_TABLE, TABEL_PER_CLASS 
@DiscriminatorColumn//dtype 열이 생성되어 item을 상속받은 엔티티 이름이 테이블에 표시된다. 

    // Item 테이블만 단독으로 사용하지 않도록 추상메소드로 표현한다. 
public abstract class Item {

	@Id @GeneratedValue
	private long id;
	
	private String name;
	private int price;
}
/////////////////////////////////////////////////////////////

//Movie.java
@Entity
@Data
@DiscriminatorValue("M")//@discriminatorColumn으로 생성되는 dtype열의 입력값을 Movie가 아니라 B로 표시한다. 기본값은 엔티티 이름
public class Movie extends Item{
	
	private String diractor;
	private String actor;
}
////////////////////////////////////////////////////////////////

//Album.java
@Entity
@Data
@DiscriminatorValue("A")//@discriminatorColumn으로 생성되는 dtype열의 입력값을 Album이 아니라 A로 표시한다. 
public class Album extends Item{

	private String artist;
}
////////////////////////////////////////////////////////////////

//Book.java
@Entity
@Data
@DiscriminatorValue("B")
public class Book extends Item{
	
	private String author;
	private String isbn;
	
}
///////////////////////////////////////////////////////////////
```

### 1.join전략
- 데이터 정규화 가능, 외래키 참조 무결성 제약조건 활용가능, 저장공간 효율화

- 조회시 조인 많이 사용, 조회쿼리 복잡함. 데이터 저장 시 insert 두번 호출
```java
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn//dtype 열이 생성되어 item을 상속받은 엔티티 이름이 테이블에 표시된다. 

    // Item 테이블만 단독으로 사용하지 않도록 추상메소드로 표현한다. 
public abstract class Item {

	@Id @GeneratedValue
	private long id;
	
	private String name;
	private int price;
}

//main메소드에서 값을 입력하고 실행(3 전략 모두 동일하다.)
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
        //뒷부분 생략

//콘솔 표시: 테이블 생성
Hibernate: 
    
    create table Album (
       artist varchar(255),
        id bigint not null,
        primary key (id)
    )
Hibernate: 
    
    create table Book (
       author varchar(255),
        isbn varchar(255),
        id bigint not null,
        primary key (id)
    )
Hibernate: 
    
    create table Item (
       DTYPE varchar(31) not null,
        id bigint not null,
        name varchar(255),
        price integer not null,
        primary key (id)
    )
Hibernate: 
    
    create table Movie (
       actor varchar(255),
        diractor varchar(255),
        id bigint not null,
        primary key (id)
    )

//콘솔 표시 : Movie입력값 표시
Hibernate: 
    call next value for hibernate_sequence
Hibernate: 
    insert //insert 1번
    into
        Item
        (name, price, DTYPE, id) 
    values
        (?, ?, 'M', ?)
Hibernate: 
    insert //insert 2번
    into
        Movie
        (actor, diractor, id) 
    values
        (?, ?, ?)
Hibernate: 
    select
        movie0_.id as id2_2_0_,
        movie0_1_.name as name3_2_0_,
        movie0_1_.price as price4_2_0_,
        movie0_.actor as actor1_6_0_,
        movie0_.diractor as diractor2_6_0_ 
    from
        Movie movie0_ 
    inner join
        Item movie0_1_ 
            on movie0_.id=movie0_1_.id 
    where
        movie0_.id=?

findMovie=Movie(diractor=aaa, actor=bbb)
```
### 2. 단일 테이블 전략
- 조인이 필요없으니 조회성능 빠르다. 조회쿼리가 단순하다.
- 자식 엔티티가 매핑한 칼럼은 모두 null이 허용된다, 단일 테이블 크기가 커질 수 있으며 상황에 따라 조회성능이 느려질 수 있다.
```java
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn//dtype 열이 생성되어 item을 상속받은 엔티티 이름이 테이블에 표시된다. 

    // Item 테이블만 단독으로 사용하지 않도록 추상메소드로 표현한다. 
public abstract class Item {

	@Id @GeneratedValue
	private long id;
	
	private String name;
	private int price;
}

//콘솔 확인 : ITEM 테이블에 Movie, Album, Book 엔티티의 필드값이 다 들어간다. 하위 필드는 생성 안된다.
Hibernate: 
    
    create table Item (
       DTYPE varchar(31) not null,
        id bigint not null,
        name varchar(255),
        price integer not null,
        artist varchar(255),
        actor varchar(255),
        diractor varchar(255),
        author varchar(255),
        isbn varchar(255),
        primary key (id)
    )

// 입력값 확인
Hibernate: 
    call next value for hibernate_sequence
Hibernate: 
    insert 
    into
        Item
        (name, price, actor, diractor, DTYPE, id) //movie 하위 값 들어감. 입력되지 않는 값들은 null이 된다.(Album, Book 값들)
    values
        (?, ?, ?, ?, 'M', ?)
Hibernate: 
    select
        movie0_.id as id2_0_0_,
        movie0_.name as name3_0_0_,
        movie0_.price as price4_0_0_,
        movie0_.actor as actor6_0_0_,
        movie0_.diractor as diractor7_0_0_ 
    from
        Item movie0_ 
    where
        movie0_.id=? 
        and movie0_.DTYPE='M'
findMovie=Movie(diractor=aaa, actor=bbb)
```


### 3. 구현 클래스마다 테이블 전략
- 쓰면 안된다. DB설계자와 orm 전문가 둘 다 싫어한다.
- 테이블간 연관관계를 확인하기 어렵다. 여러 자식 테이블을 함께 조회할 때 성능이 느리다(Union sql), 자식테이블을 통합해서 쿼리하기 어렵다.

```java
@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn//dtype 열이 생성되어 item을 상속받은 엔티티 이름이 테이블에 표시된다. 

    // Item 테이블만 단독으로 사용하지 않도록 추상메소드로 표현한다. 
public abstract class Item {

	@Id @GeneratedValue
	private long id;
	
	private String name;
	private int price;
}

//콘솔 표시 : Item 테이블이 생성되지 않고 하위테이블에 item 값들이 들어간다.
Hibernate: 
    
    create table Album (
       id bigint not null,
        name varchar(255),
        price integer not null,
        artist varchar(255),
        primary key (id)
    )
Hibernate: 
    
    create table Book (
       id bigint not null,
        name varchar(255),
        price integer not null,
        author varchar(255),
        isbn varchar(255),
        primary key (id)
    )
Hibernate: 
    
    create table Movie (
       id bigint not null,
        name varchar(255),
        price integer not null,
        actor varchar(255),
        diractor varchar(255),
        primary key (id)
    )
//입력값 확인
Hibernate: 
    call next value for hibernate_sequence
Hibernate: 
    insert //item테이블 없이 movie테이블에 인서트 1번
    into
        Movie 
        (name, price, actor, diractor, id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    select
        movie0_.id as id1_2_0_,
        movie0_.name as name2_2_0_,
        movie0_.price as price3_2_0_,
        movie0_.actor as actor1_6_0_,
        movie0_.diractor as diractor2_6_0_ 
    from
        Movie movie0_ 
    where
        movie0_.id=?
findMovie=Movie(diractor=aaa, actor=bbb)
```