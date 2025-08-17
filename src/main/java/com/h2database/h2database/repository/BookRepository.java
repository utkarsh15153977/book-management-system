package com.h2database.h2database.repository;

import com.h2database.h2database.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // ✅ import List from java.util

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
