package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Movie extends Item{
	private String diractor;
	private String actor;
	
	public String getDiractor() {
		return diractor;
	}
	public void setDiractor(String diractor) {
		this.diractor = diractor;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	
}
