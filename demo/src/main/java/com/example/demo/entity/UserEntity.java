package com.example.demo.entity;

import com.example.demo.entity.enums.ERole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, updatable = true)
    private String username;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true)
    private String mail;
    @Column(length = 3000)
    private String password;
    @Column(columnDefinition = "text")
    private String bio;
    @Column(columnDefinition = "text")
    private String contact;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();  //уникальный сет


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEntity", orphanRemoval = true)
    private List<Post> posts = new ArrayList();  //повторки

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;  //дата создания

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public UserEntity() {
    }

    public UserEntity(
            Long id,
            String username,
            String password,
            String mail,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.mail = mail;
    }

    @PrePersist //запись до передачи в бд
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    /**
     * SECURITY
     */


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Set<ERole> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }
}







