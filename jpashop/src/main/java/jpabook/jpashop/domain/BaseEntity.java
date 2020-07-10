package jpabook.jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

//공통 매핑 정보가 필요할 때 @MappedSuperclass를 사용한다.
//이 클래스를 상속받은 객체가 테이블을 생성할 때, 아래 필드 값들이 테이블에 추가된다.
@MappedSuperclass
public class BaseEntity {
	
	private String createBy;
	private LocalDateTime createdDate;
	private String lastModifiedBy;
	private LocalDateTime lastModifiedDate;
	
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	
}
