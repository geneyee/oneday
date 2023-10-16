package com.example.oneday.book.dto;

import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class BookLogCreateDTO {
	
	@NonNull
	@Positive
	private Integer bookId;
	
	@NonNull
	private String comment;
	
	private Integer page;
}
