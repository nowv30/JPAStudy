package jpaMapping.mappingTest.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
//InheritanceType.SINGLE_TABLE이면 @DiscriminatorColumn이 자동 적용(dtype생성)
//InheritanceType.TABLE_PER_CLASS를 하면 추상클래스인 Item테이블은 생기지 않는다. 
//단 데이터를 찾을 때 item 테이블이 없으므로 id값을 모든 테이블(Movie, Album, Book)에서 찾아야 한다.
@DiscriminatorColumn//dtype 열이 생성되어 item을 상속받은 엔티티 이름이 테이블에 표시된다. 
public abstract class Item {

	@Id @GeneratedValue
	private long id;
	
	private String name;
	private int price;
	
	
}
