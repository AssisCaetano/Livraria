package com.assis.bookshop.excepetions;

public class DeleteRecord extends RuntimeException{
    
    public DeleteRecord(){
        super("Registro deletado com sucesso! ");
    }

    public DeleteRecord(String messagae){
        super(messagae);
    }
}
