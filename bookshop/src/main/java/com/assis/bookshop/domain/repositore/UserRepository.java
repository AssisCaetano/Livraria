package com.assis.bookshop.domain.repositore;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assis.bookshop.domain.model.UserModel;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByName(String name);
    
}
