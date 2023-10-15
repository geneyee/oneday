package com.example.oneday.book.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.example.oneday.book.entity.Book;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class BookEditDTO {
	
	@NonNull 
	@Positive // 값은 양수로 제한(즉, 1이상)
	private Integer bookId;
	
	@NonNull
	@NotBlank // != null && .equals("") == false
	private String title;
	
	@NonNull
	@Min(1000) // 가격은 1000이상이여야 함 미만이면 유효성 검사 실패
	private Integer price;
	
	
	// dto -> entity (클라이언트가 입력한 값을 entity에 저장)
	// 빌더 패턴 대신 메소드 생성(책 입력 기능과 비교)
	// 메소드를 사용하면 서비스 레이어에서 값을 채우는 논리가 커맨드 객체로 이동되므로 코드가 더 분산되는 효과 
	// 빌더 패턴을 사용하지 않은 이유는 수정 기능을 만들고 있기 때문에 jpa에 의해 이미 값이 채워진 엔티티를 다루기 때문..
	public Book fill(Book book) {
		book.setTitle(this.title);
		book.setPrice(this.price);
		return book;
	}

}
