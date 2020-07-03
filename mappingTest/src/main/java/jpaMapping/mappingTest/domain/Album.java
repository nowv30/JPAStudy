package jpaMapping.mappingTest.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("A")//@discriminatorColumn으로 생성되는 dtype열의 입력값을 Album이 아니라 A로 표시한다. 
public class Album extends Item{

	private String artist;
}
