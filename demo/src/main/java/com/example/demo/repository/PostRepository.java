package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PostRepository extends JpaRepository<Post, Long> {

    //select*from post as p where user="user" sort desc
    List<Post> findAllByUserOrderByCreatedDateDesc(UserEntity userEntity);

    List<Post> findAllByOrderByCreatedDateDesc(UserEntity userEntity);

    Optional<Post> findPostByIdAndUser(Long id, UserEntity userEntity);
}
