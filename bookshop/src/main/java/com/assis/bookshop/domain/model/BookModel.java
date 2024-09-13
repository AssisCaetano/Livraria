package com.assis.bookshop.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBook;
    private String name;
    private Long quantity;
    private Double price;
    private String author;
    private String edition;
    private String description;

}
