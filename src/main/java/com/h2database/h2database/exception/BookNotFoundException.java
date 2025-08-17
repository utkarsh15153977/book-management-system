package com.h2database.h2database.exception;
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) { super("Book not found: " + id); }
}
