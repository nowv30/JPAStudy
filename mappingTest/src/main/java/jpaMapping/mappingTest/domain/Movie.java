package jpaMapping.mappingTest.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("M")
public class Movie extends Item{
	
	private String diractor;
	private String actor;
}
