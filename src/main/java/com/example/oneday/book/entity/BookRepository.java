package com.example.oneday.book.entity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	// like '%title%' as contains 의 list + 페이징
	public List<Book> findByTitleContains(String title, Pageable pageable);

}
