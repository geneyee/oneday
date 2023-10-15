package com.example.oneday.book.dto;

import lombok.Getter;

@Getter
public class BookListReponseDTO {
	
	private Integer bookId;
	private String title;
	
	public BookListReponseDTO(Integer bookId, String title) {
		this.bookId = bookId;
		this.title = title;
	}
	
	// 생성자를 통해 객체를 생성하는 방법은 변하지 않는 immutable객체를 생성할 때 자주 사용하는 패턴
	// 멤버 변수는 private으로 선언되어 있고 setter도 없기 때문에 객체를 생성할 때 외에는 값을 바꿀 수 없다
	// AllArgsConstructor 어노테이션을 쓰지 않고 생성자를 작성한 이유는
	// 추후 응답 객체가 바뀔 떄 필수가 아닌 항목들이 추가될 수 있기 때문이다.
	// 예를 들어서 '공동저자'항목이 추가 된다고 가정해보면
	// 모든 책이 공동저자를 가지는 것은 아니기 때문에 '공동저자'는 필수가 아닌 항목이다.
	// 이럴때는 일반적으로 공동저자가 있는 생성자와 공동저자가 없는 생성자를 따로 만들어서 코드가 의도를 나타내게 하는 방법을 사용한다.

}
