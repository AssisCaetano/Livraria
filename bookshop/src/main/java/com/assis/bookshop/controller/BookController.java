package com.assis.bookshop.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assis.bookshop.domain.model.BookModel;
import com.assis.bookshop.domain.service.BookService;
import com.assis.bookshop.dto.BookRecord;

@RestController
public class BookController {
    
    @Autowired
    private BookService bS;

    @PostMapping
    public ResponseEntity<BookModel> insertBook(@RequestBody @Validated BookRecord bookModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(bS.insertBook(bookModel));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bS.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookModel>> getByBookId(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(bS.getByBookId(id));
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Optional<BookModel>> updateBookModel(@PathVariable(value = "id")UUID id, @RequestBody BookRecord bookModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(bS.updateBookModel(id, bookModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookModel> deleteById(@PathVariable ("id")UUID id){
        bS.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
