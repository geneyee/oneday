package com.example.oneday.book.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookCreateDTO { // 책 저장
	
	@NonNull // 값이 null일 경우 NullPointException 발생시킴
	private String title;
	
	@NonNull
	private Integer price;
}
