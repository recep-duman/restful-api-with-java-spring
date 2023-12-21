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

import com.bookstore.restapi.entity.Author;
import com.bookstore.restapi.entity.Book;
import com.bookstore.restapi.exception.AuthorNotFoundException;
import com.bookstore.restapi.repository.AuthorRepository;
import com.bookstore.restapi.repository.BookRepository;

import jakarta.validation.Valid;

@RestController
public class AuthorResource {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public AuthorResource(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public List<Author> retrieveAllAuthors() {
        return this.authorRepository.findAll();
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
        this.authorRepository.save(author);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(author.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/authors/{id}")
    public Author retriveAuthor(@PathVariable int id) {
        Optional<Author> author = this.authorRepository.findById(id);
        if (author.isEmpty())
            throw new AuthorNotFoundException("Author not found");

        return author.get();
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable int id) {
        this.authorRepository.deleteById(id);
    }

    @GetMapping("/authors/{id}/books")
    public List<Book> retrieveBooksForAuthor(@PathVariable int id) {
        Optional<Author> author = this.authorRepository.findById(id);
        if (author.isEmpty())
            throw new AuthorNotFoundException("Author not found");

        return author.get().getBooks();
    }

    @PostMapping("/authors/{id}/books")
    public ResponseEntity<Book> createBookForAuthor(@PathVariable Integer id, @Valid @RequestBody Book book) {
        Optional<Author> author = this.authorRepository.findById(id);
        if (author.isEmpty())
            throw new AuthorNotFoundException("Author not found");

        book.setAuthor(author.get());
        Book savedBook = bookRepository.save(book);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
