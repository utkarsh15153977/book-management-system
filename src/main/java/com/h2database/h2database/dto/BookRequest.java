package com.h2database.h2database.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookRequest {
    @NotBlank private String title;
    @NotBlank private String author;
    @NotBlank
    @Pattern(regexp = "^[0-9-]{10,17}$") private String isbn;
    @DecimalMin("0.0") private BigDecimal price;
    private LocalDate publishedAt;

    // getters/setters


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }
}
