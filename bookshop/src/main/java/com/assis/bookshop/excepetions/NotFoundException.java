package com.assis.bookshop.excepetions;

public class NotFoundException extends RuntimeException{
    
    public NotFoundException(){
        super("Sem dados para exibir: ");
    }

    public NotFoundException(String message){
        super(message);
    }
}
