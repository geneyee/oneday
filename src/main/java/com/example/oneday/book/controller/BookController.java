package com.example.oneday.book.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.oneday.book.dto.BookCreateDTO;
import com.example.oneday.book.dto.BookEditDTO;
import com.example.oneday.book.dto.BookEditResponseDTO;
import com.example.oneday.book.dto.BookReadResponseDTO;
import com.example.oneday.book.service.BookService;

//@Validated
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
	
	// 책 수정 처리
	@PostMapping("/book/edit/{bookId}")
	public ModelAndView update(@Validated BookEditDTO bookEditDTO, Errors errors) {
		
		// 유효성 검사 에러 시 
		if (errors.hasErrors()) {
			String errorMessage = 
					errors // 에러 객체에서 
					.getFieldErrors() // 에러 목록을 가져와서
					.stream()	// 스트림으로 바꾼 후 
					.map(x -> x.getField() + " : " + x.getDefaultMessage())	// "필드명 : 에러 메시지" 형태로 각 항목에 적용하고
					.collect(Collectors.joining("\n")); // 줄바꿈 문자("\")로 합친다
			
			return this.error422(errorMessage, String.format("/book/edit/%s", bookEditDTO.getBookId()));
		}
		
		this.bookService.update(bookEditDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(String.format("redirect:/book/read/%s", bookEditDTO.getBookId()));
		return mav;
		
		/*
		// stream 
		List<String> errorMessasges = new LinkedList<>();
		for (FieldError fieldError : errors.getFieldErrors()) {
			String lineErrorMessage = fieldError.getField() + " : " + fieldError.getDefaultMessage();
			errorMessasges.add(lineErrorMessage);
		}
		String errorMessage = String.join("\n", errorMessasges);
		*/
	}
	
	

	
	// NoSuchElementException이 발생하면 실행되는 메소드
	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView noSuchElementExceptionHanlder(NoSuchElementException ex) {
		return this.error422("책 정보가 없습니다.", "/book/list");
	}

	// @ExceptionHandler(NoSuchElementException.class)
	public ModelAndView error422(String message, String location) {
		
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message", message);
		mav.addObject("location",location);
		mav.setViewName("common/error/422");
		
		return mav;
	}
	
	
	

}
