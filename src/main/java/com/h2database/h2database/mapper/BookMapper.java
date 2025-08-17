package com.h2database.h2database.mapper;
import com.h2database.h2database.dto.BookRequest;
import com.h2database.h2database.model.Book;
import com.h2database.h2database.dto.BookResponse;
public class BookMapper {
    public static Object toResponse;

    public static Book toEntity(BookRequest req) {
        Book b = new Book();
        b.setTitle(req.getTitle());
        b.setAuthor(req.getAuthor());
        b.setIsbn(req.getIsbn());
        b.setPrice(req.getPrice());
        b.setPublishedAt(req.getPublishedAt());
        return b;
    }
    public static BookResponse toResponse(Book b) {
        BookResponse r = new BookResponse();
        r.setId(b.getId());
        r.setTitle(b.getTitle());
        r.setAuthor(b.getAuthor());
        r.setIsbn(b.getIsbn());
        r.setPrice(b.getPrice());
        r.setPublishedAt(b.getPublishedAt());
        return r;
    }

    public static Object toResponse(Object o) {
    }
}
