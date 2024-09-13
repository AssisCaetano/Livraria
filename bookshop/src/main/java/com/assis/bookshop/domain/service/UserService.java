package com.assis.bookshop.domain.service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.assis.bookshop.domain.model.UserModel;
import com.assis.bookshop.domain.model.BookModel;
import com.assis.bookshop.domain.repositore.UserRepository;
import com.assis.bookshop.dto.UserRecord;
import com.assis.bookshop.excepetions.AlreadyRegisteredUser;
import com.assis.bookshop.excepetions.DeleteRecord;
import com.assis.bookshop.excepetions.FullException;
import com.assis.bookshop.excepetions.NotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository uR;

    // Salva um usuário na base, mais é verificado a existência de um usuário, caso não possua seguinda com o cadastro.
    public UserModel saveUser(UserRecord userRecord){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecord, userModel);
        Optional<UserModel> userName = uR.findByName(userModel.getName());
        if(userName.isPresent()){
            throw new AlreadyRegisteredUser();
        }
        return uR.save(userModel);
    }

    // Retorna todos os usuários do base mais antes de retornar verifica se a lista está vazia.
    public List<UserModel> getAllUsers(){
        List<UserModel> lUser = uR.findAll();
        if(lUser.isEmpty()){
            throw new NotFoundException();
        }
        return uR.findAll();
    }

    // Busca um usuário pelo id.
    public Optional<UserModel> getByUserId(UUID id){
        Optional<UserModel> uModel = uR.findById(id);
            if(uModel.isEmpty()){
                throw new NotFoundException();
            }else{
                
                return uR.findById(id);
            }
    }

    // Atualizando um usuário na base, mais antes verifica se esse usuário existe, se exitir o usuário será atualizado.
    public Optional<UserModel> upadateModel(UUID id, UserRecord userRecord){
        Optional<UserModel> userId = uR.findById(id);
        if(userId.isEmpty()){
            throw new NotFoundException();
        }
        UserModel userModel = userId.get();
        BeanUtils.copyProperties(userRecord, userModel);
        uR.save(userModel);
        return uR.findById(id);
    }

    // Ao deletar um usuário é verificado se o mesmo existe na base, caso exista, é processeguido com a excluão do usuário.
    public Optional<UserModel> deleteUser(UUID id){
        Optional<UserModel> delUser = uR.findById(id);
        if(delUser.isPresent()){
            uR.deleteById(id);
            throw new DeleteRecord();
        }else{ 
            throw new NotFoundException();
        }
    }

    // Adicina um livro ao usuário, mais antes de adicionar, é feito uma verificação ná base e verifica a existencia de um usuário existindo será adicionado um livro
    // na lista de livros do usuário.
    public UserModel rentedBooks(UUID id, BookModel bookModel){
        Optional<UserModel> uModel = uR.findById(id);
        if(uModel.isPresent()){
            UserModel userModel = uModel.get();
            userModel.getBookModel().add(bookModel);
            return uR.save(userModel);
        }else{
            throw new FullException();
        } 
    }
}
