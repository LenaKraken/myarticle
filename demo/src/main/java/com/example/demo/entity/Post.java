package com.example.demo.entity;

import com.example.demo.entity.enums.ETage;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String title;
    private String captoin; //описание
    private Integer likes;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likedUsers = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    @Column(updatable = false)
    private LocalDateTime createdDate;
    private Set<ETage> tage = new HashSet<>();  //уникальный сет

    @PrePersist //запись до передачи в бд
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
