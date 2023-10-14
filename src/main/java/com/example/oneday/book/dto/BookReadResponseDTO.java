package com.example.oneday.book.dto;

import java.time.LocalDateTime;

import com.example.oneday.book.entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 타임리프에서 필요함 (읽을때)
@NoArgsConstructor
public class BookReadResponseDTO { // 책 조회
	
	private Integer bookId;
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	
	// dto -> entity
	public BookReadResponseDTO fromBook(Book book) {
		this.bookId = book.getBookId();
		this.title = book.getTitle();
		this.price = book.getPrice();
		this.insertDateTime = book.getInsertDateTime();
		return this;
	}
	
	// 팩토리 패턴
	public static BookReadResponseDTO BookFactory(Book book) {
		BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();
		bookReadResponseDTO.fromBook(book);
		return bookReadResponseDTO;
	}
	
	
	/*
	 * // 만약 책 정보 외에 리뷰 정보도 받아야 한다면
	public BookReadResponseDTO fromReview(Review review) {
		public static BookReadResponseDTO BookFactory(Review review);
		return BookReadResponseDTO.fromBook(book).fromReview(review);
	}
	*/
}
