package com.tamdai.model.security.repository;

import com.tamdai.model.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPassword(String firstName, String password);

    UserEntity findUserByFirstName(String firstName);
}
