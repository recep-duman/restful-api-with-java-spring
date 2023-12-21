package com.bookstore.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.restapi.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
