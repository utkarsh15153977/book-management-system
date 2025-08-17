package com.h2database.h2database.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookResponse {
    public static BookResponse created;
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private BigDecimal price;
    private LocalDate publishedAt;

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }
}
