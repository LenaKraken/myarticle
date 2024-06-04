package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByUsername(String username); //не будет ошибки если попытка вернуть пустоту

    Optional<UserEntity> findUserByMail(String mail);

    Optional<UserEntity> findUserById(Long id);


}
