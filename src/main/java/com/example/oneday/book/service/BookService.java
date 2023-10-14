package com.example.oneday.book.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.oneday.book.dto.BookCreateDTO;
import com.example.oneday.book.dto.BookEditResponseDTO;
import com.example.oneday.book.dto.BookReadResponseDTO;
import com.example.oneday.book.entity.Book;
import com.example.oneday.book.entity.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// 책 저장 method
	public Integer insert(BookCreateDTO bookCreateDTO) {
		
		// dto -> entity
		Book book = Book.builder() // BookBuilder 객체 생성
				.title(bookCreateDTO.getTitle())
				.price(bookCreateDTO.getPrice())
				.build(); // Book 객체 생성
		
		log.info("{}의 속성 : {}",book.getClass().getName(), book.toString());
		
		// entity DB에 저장
		this.bookRepository.save(book); // 
		log.info("{}의 속성 : {}",book.getClass().getName(), book.toString());
		
		return book.getBookId();
	}
	
	// 책 읽기 
	public BookReadResponseDTO read(Integer bookId) throws NoSuchElementException {
		
		// 넘어온 파라미터값 (bookId)가 존재하는지 확인
//		Book book = this.bookRepository.findById(bookId)
//				.orElseThrow(() -> new IllegalArgumentException("해당 bookId가 존재하지 않습니다."));
		
		Book book = this.bookRepository.findById(bookId).orElseThrow();
		
		// findById 의 반환값은 Optional 이다. Optional<Book>
		// Optional 객체는 값이 null 일 수도 있는 객체로 실제로 값이 없더라도 null을 반환하는게 아니라
		// Optional 변수 .isPresent() == false인 Optional 객체를 반환하게 된다.
		// Optional을 사용하게 되면 꼭 필요할 때만 null체크를 하면 되는 장점이 있다.
		// orElseThrow()는 optional의 메소드. 값이 null이면 예외 던진다. 
	
		// entity -> dto
//		BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();
//		bookReadResponseDTO.fromBook(book);
//		
//		return bookReadResponseDTO;
		
		return BookReadResponseDTO.BookFactory(book);
	}
	
	// 책 수정 화면 조회
	public BookEditResponseDTO edit(Integer bookId) throws NoSuchElementException {
		
		// 수정할 책 id 조회
		Book book = this.bookRepository.findById(bookId).orElseThrow();
		
		// entity -> dto
		return BookEditResponseDTO.BookFactory(book);
	}

}