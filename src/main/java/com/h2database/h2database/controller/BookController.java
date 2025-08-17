package com.h2database.h2database.controller;

import com.h2database.h2database.dto.BookRequest;
import com.h2database.h2database.dto.BookResponse;
import com.h2database.h2database.exception.BookNotFoundException;
import com.h2database.h2database.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/spi/book")
public class BookController {
    public final BookService srv;
    public BookController(BookService srv) {
        this.srv = srv;
    }

    //CREATE
    @PostMapping
    public ResponseEntity <BookResponse> create(@Valid @RequestBody BookRequest request) throws BookNotFoundException {
        BookResponse.created = srv.create(request);
        return ResponseEntity.created(URI.create("/api/books/" + created.getId())).body(created);
    }

    // READ ALL (optionally add pagination later)
    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll() {
        return ResponseEntity.ok(srv.findAll()); // 200
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(srv.findById(id)); // 200
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(srv.update(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        srv.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    // SEARCH by title
    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> search(@RequestParam("q") String keyword) {
        return ResponseEntity.ok(srv.searchByTitle(keyword)); // 200
    }
}