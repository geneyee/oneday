package com.example.oneday.book.dto;

import java.time.LocalDateTime;

import com.example.oneday.book.entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookEditResponseDTO { // 책 수정 화면 조회
	
	private Integer bookId;
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	
	// dto -> entity
	public BookEditResponseDTO fromBook(Book book) {
		this.bookId = book.getBookId();
		this.title = book.getTitle();
		this.price = book.getPrice();
		this.insertDateTime = book.getInsertDateTime();
		return this;
	}
	
	// 팩토리 패턴
	public static BookEditResponseDTO BookFactory(Book book) {
		BookEditResponseDTO bookEditResponseDTO = new BookEditResponseDTO();
		bookEditResponseDTO.fromBook(book);
		return bookEditResponseDTO;
	}

}
