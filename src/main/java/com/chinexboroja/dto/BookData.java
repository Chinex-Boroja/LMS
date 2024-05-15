package com.chinexboroja.dto;

import java.time.LocalDateTime;

public class BookData {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime publicationYear;
    private String isbn;

    public BookData() {
    }

    public BookData(Long id, String title, String author, LocalDateTime publicationYear, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(LocalDateTime publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
