package com.h2database.h2database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(name = "uk_books_isbn", columnNames = "isbn")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;
    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;
    @Pattern(regexp = "^[0-9-]{10,17}$", message = "ISBN must contain digits and dashes")
    @Column(nullable = false, unique = true)
    private String isbn;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be >= 0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    private LocalDate publishedAt;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
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
