package com.bookstore.restapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookstore.restapi.entity.Book;
import com.bookstore.restapi.exception.BookNotFoundException;
import com.bookstore.restapi.repository.BookRepository;

import jakarta.validation.Valid;

@RestController
public class BookResource {
    private BookRepository repository;

    public BookResource(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public List<Book> retrieveAllBooks() {
        return this.repository.findAll();
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        this.repository.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> retrieveBook(@PathVariable int id) {
        Optional<Book> book = this.repository.findById(id);
        if (book.isEmpty())
            throw new BookNotFoundException("Book not found");

        return book;
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable int id) {
        this.repository.deleteById(id);
    }

    
}
