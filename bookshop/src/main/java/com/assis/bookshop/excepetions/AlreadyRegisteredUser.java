package com.assis.bookshop.excepetions;

public class AlreadyRegisteredUser extends RuntimeException{

    public AlreadyRegisteredUser(){
        super("Registro já existente na base! ");
    }
    
    public AlreadyRegisteredUser(String message){
        super(message);	
    }
}
