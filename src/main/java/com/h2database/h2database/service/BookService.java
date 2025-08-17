package com.h2database.h2database.service;

import com.h2database.h2database.dto.BookRequest;
import com.h2database.h2database.dto.BookResponse;
import com.h2database.h2database.exception.BookNotFoundException;
import com.h2database.h2database.mapper.BookMapper;
import com.h2database.h2database.model.Book;
import com.h2database.h2database.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    public final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public BookResponse create(BookRequest bookRequest) {
        if(repo.existsByIsbn(bookRequest.getIsbn())) {
            throw new IllegalArgumentException("ISBN already exists");
        }
        Book saved = (Book) repo.save(BookMapper.toEntity(bookRequest));
        return BookMapper.toResponse(saved);
    }

    //Select all the books Select * from BOOK;
    public List<BookResponse> findAll() {
        return repo.findAll().stream().map(BookMapper::toResponse).toList();
    }
    //Get Book by Id
    public BookResponse findById(Long id) {
        Book b = repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return BookMapper.toResponse(b);
    }

    //Update Book
    @Transactional
    public BookResponse update(BookRequest bookRequest, long id) throws Throwable {
        Book b = (Book) repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        b.setTitle(bookRequest.getTitle());
        b.setAuthor(bookRequest.getAuthor());
        if (!b.getIsbn().equals(bookRequest.getIsbn()) && repo.existsByIsbn(bookRequest.getIsbn())) {
            throw new IllegalArgumentException("ISBN already exists");
        }
        b.setIsbn(bookRequest.getIsbn());
        b.setPrice(bookRequest.getPrice());
        b.setPublishedAt(bookRequest.getPublishedAt());
        return BookMapper.toResponse(b);
    }
    public List<BookResponse> searchByTitle(String q) {
        return repo.findByTitleContainingIgnoreCase(q)
                .stream()
                .map(BookMapper::toResponse)
                .collect(Collectors.toList());
    }
}
