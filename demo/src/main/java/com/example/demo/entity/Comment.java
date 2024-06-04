package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Long userId;
    @Column(columnDefinition = "text", nullable = false)
    private String messege;
    @Column(updatable = false)
    private LocalDateTime createdDate;  //дата создания

    @PrePersist //запись до передачи в бд
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
