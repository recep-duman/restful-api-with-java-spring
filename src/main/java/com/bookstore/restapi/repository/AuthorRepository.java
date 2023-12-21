package com.bookstore.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.restapi.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
