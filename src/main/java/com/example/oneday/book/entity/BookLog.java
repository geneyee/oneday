package com.example.oneday.book.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import groovy.transform.ToString;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ToString
@Setter // 엔티티에서 setter 말고 다른거 사용 생각해보기
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BookLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Integer bookLogId;
	
	@ManyToOne(fetch = FetchType.LAZY) // 지연로딩(기본값) 
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	private Integer page;
	
	@CreationTimestamp
	private LocalDateTime insertDateTime;

	@Builder
	public BookLog(Integer bookLogId, Book book, String comment, Integer page, LocalDateTime insertDateTime) {
		super();
		this.bookLogId = bookLogId;
		this.book = book;
		this.comment = comment;
		this.page = page;
		this.insertDateTime = insertDateTime;
	}

	// 지연로딩 : 데이터를 사용할 때 쿼리를 실행시키는 방법
	// 빠른로딩 : 일단 데이터가 호출되면 무조건 관련 테이블의 데이터 불러옴
	
	
	

}
