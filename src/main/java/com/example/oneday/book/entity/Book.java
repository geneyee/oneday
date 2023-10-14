package com.example.oneday.book.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter // setter의 사용은 지양해야 한다. setter 대신 메소드 사용 
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // access = AccessLevel.PROTECTED : 의미없는 객체 생성 방지
//@Builder // 생성자에 붙여서 사용도 가능하다. 
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	
	@Column(length = 200)
	private String title;
	
	private Integer price;
	
	@CreationTimestamp
	private LocalDateTime insertDateTime;

	
	@Builder // @AllArgsConstructor 대체
	public Book(Integer bookId, String title, Integer price, LocalDateTime insertDateTime) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.insertDateTime = insertDateTime;
	}

	
	
	// Setter 대신 메서드 만든다면 우선 알아야 할 것
	// JPA는 엔티티의 인스턴스를 생성하고 DB의 값을 읽어서 setter를 통해 인스턴스의 멤버변수 값을 채워나간다.
	
	// 여기서 알 수 있는 것 
	// 1. 파라미터 없는 기본 생성자 필요한 이유 -> JPA가 엔티티 인스턴스 생성하므로(객체 만든다는 의미)
	// 2. setter 대신 메서드를 어떻게 만들것인지 고민
	
	
}
