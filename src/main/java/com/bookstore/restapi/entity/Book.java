package com.bookstore.restapi.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 3, message = "Book name must be at lease 3 characters")
    @NotNull
    private String name;

    @Size(min = 10, message = "Description must be at lease 10 characters")
    @NotNull
    private String description;

    @Past(message = "Create date should be in the past")
    @NotNull
    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore 
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(int id, @Size(min = 3, message = "Book name must be at lease 3 characters") @NotNull String name,
            @Size(min = 10, message = "Description must be at lease 10 characters") @NotNull String description,
            @Past(message = "Create date should be in the past") @NotNull LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

}
