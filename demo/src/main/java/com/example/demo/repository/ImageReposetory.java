package com.example.demo.repository;

import com.example.demo.entity.ImageModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ImageReposetory extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByUserId(Long userId);

    Optional<ImageModel> findByPostId(Long postId);

}
