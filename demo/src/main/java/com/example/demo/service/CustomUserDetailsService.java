package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findUserByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username: " + username));
        return build(userEntity);
    }

    public UserEntity loadUserById  (long id) {
        return userRepository.findUserById(id).orElse(null);
    }

    public static UserEntity build(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(eRole -> new SimpleGrantedAuthority(eRole.name()))
                .collect(Collectors.toList());
        return new UserEntity(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getMail(),
                authorities
        );

    }

}
