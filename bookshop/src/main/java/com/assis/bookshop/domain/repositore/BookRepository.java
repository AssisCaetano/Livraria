package com.assis.bookshop.domain.repositore;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assis.bookshop.domain.model.BookModel;

public interface BookRepository extends JpaRepository<BookModel, UUID>{
    Optional<BookModel> findByName(String name);
}
