package com.example.oneday.book.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.oneday.book.dto.BookCreateDTO;
import com.example.oneday.book.dto.BookEditResponseDTO;
import com.example.oneday.book.dto.BookReadResponseDTO;
import com.example.oneday.book.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	// 책 저장 화면
	@GetMapping("/book/create")
	public String create() {
		return "book/create";
	}
	
	// 책 저장
	@PostMapping("/book/create")
	public String insert(BookCreateDTO bookCreateDTO) {
		
		Integer bookId = this.bookService.insert(bookCreateDTO);
		
		return String.format("redirect:/book/read/%s", bookId);
	}
	
	// 책 정보 읽기
	@GetMapping("/book/read/{bookId}")
	public ModelAndView read(@PathVariable Integer bookId) {
		
		ModelAndView mav = new ModelAndView();
		
		try {
		BookReadResponseDTO bookReadResponseDTO = this.bookService.read(bookId); //bookReadResponseDTO
		mav.addObject("bookReadResponseDTO", bookReadResponseDTO);
		mav.setViewName("book/read");
		}catch(NoSuchElementException ex) { // 반복되는 예외 처리 @ControllerAdvice로 해보기
			mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
			mav.addObject("message","책 정보가 없습니다.");
			mav.addObject("location", "/book");
			mav.setViewName("common/error/422");
		}
		
		
		return mav;
	}
	
	// 책 수정 화면
	@GetMapping("/book/edit/{bookId}")
	public ModelAndView edit(@PathVariable Integer bookId) throws NoSuchElementException {
		
		ModelAndView mav = new ModelAndView();
		
		BookEditResponseDTO bookEditResponseDTO = this.bookService.edit(bookId);
		mav.addObject("bookEditResponseDTO", bookEditResponseDTO);
		mav.setViewName("book/edit");
		return mav;
	}
	
	
	// NoSuchElementException이 발생하면 실행되는 메소드
	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView noSuchElementExceptionHanlder(NoSuchElementException ex) {
		
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message", "책 정보가 없습니다.");
		mav.addObject("location","/book/list");
		mav.setViewName("common/error/422");
		
		return mav;
	}
	
	
	

}
