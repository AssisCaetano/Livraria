package com.assis.bookshop.excepetions;

public class FullException extends RuntimeException{

   public FullException(){
    super("Falha ao processar a Informação: ");
   }

   public FullException(String message){
    super(message);
   }
    
}
