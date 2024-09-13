package com.assis.bookshop.controller;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.assis.bookshop.domain.model.BookModel;
import com.assis.bookshop.domain.model.UserModel;
import com.assis.bookshop.dto.UserRecord;
import com.assis.bookshop.domain.service.UserService;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService uS;
    
    @PostMapping("/user")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Validated UserRecord userModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(uS.saveUser(userModel));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(uS.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<UserModel>> getByUserId(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(uS.getByUserId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Optional<UserModel>> upadateModel(@PathVariable(value = "id")UUID id, @RequestBody UserRecord userModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(uS.upadateModel(id, userModel));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity <UserModel> deleteUser(@PathVariable(value = "id")UUID id){
        uS.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Adicionando um book ao usuário
    @PostMapping("/bookModel/{id}")
    public ResponseEntity<UserModel> rentedBooks(@PathVariable(value = "id")UUID id, @RequestBody BookModel bookModel){
        UserModel updatedUser = uS.rentedBooks(id, bookModel);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}
