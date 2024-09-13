package com.assis.bookshop.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assis.bookshop.domain.model.BookModel;
import com.assis.bookshop.domain.repositore.BookRepository;
import com.assis.bookshop.dto.BookRecord;
import com.assis.bookshop.excepetions.AlreadyRegisteredUser;
import com.assis.bookshop.excepetions.DeleteRecord;
import com.assis.bookshop.excepetions.NotFoundException;

@Service
public class BookService {

    @Autowired
    private BookRepository bR;

    // Verfica se já possuim um registro na base, caso não tenha será salvo um novo registro.
    public BookModel insertBook(BookRecord bookRecord){
        BookModel bookModel = new BookModel();
        BeanUtils.copyProperties(bookRecord, bookModel);
        Optional<BookModel> bModel = bR.findByName(bookModel.getName());
        if(bModel.isPresent()){
            throw new AlreadyRegisteredUser();  
        }
        return bR.save(bookModel);
    }

    // Antes de retorna uma lista com os livros, primeiro é realizado uma verificação na base.
    public List<BookModel> getAllBooks(){
        List<BookModel> bookL = bR.findAll();
        if(bookL.isEmpty()){
            throw new NotFoundException();
        }
      return bR.findAll();
    }

    // Verifica se o Id informado existe, se existir retorna um livro correspondete ao Id.
    public Optional<BookModel> getByBookId(UUID id){
        Optional<BookModel> books = bR.findById(id);
        if(books.isEmpty()){
            throw new NotFoundException();
        }
        return books;
    }

    // Antes de realizar a atualizar de um registro, éfeito uma verificação pra vê se um dado existe na base, existindo realzia a atualização.
    public Optional<BookModel> updateBookModel(UUID id, BookRecord bookRecord){
        Optional<BookModel> book = bR.findById(id);
        if(book.isEmpty()){
            throw new NotFoundException();
        }
        BookModel bookModel = book.get();
        BeanUtils.copyProperties(bookRecord, bookModel);
        bR.save(bookModel);
        return bR.findById(id);
    }

    // Antes de deletar um dado no banco, é verificado a existência de um dado na base, avendo o dado é realizado a exclusão do livro.
    public Optional<BookModel> deleteById(UUID id){
        Optional<BookModel> bookId = bR.findById(id);
        if(bookId.isPresent()){
            bR.deleteById(id);
            throw new DeleteRecord();
        }else{
            throw new NotFoundException();
        }
        
    }
}
