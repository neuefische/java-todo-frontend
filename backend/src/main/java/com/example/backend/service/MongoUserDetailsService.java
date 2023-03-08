package com.example.backend.service;

import com.example.backend.model.MongoUser;
import com.example.backend.model.MongoUserDTOOut;
import com.example.backend.repository.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoUserDetailsService implements UserDetailsService {
    private final MongoUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final IDService idService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUser optionalMongoUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(optionalMongoUser.username(), optionalMongoUser.password(),
                List.of(new SimpleGrantedAuthority(("ROLE_" + optionalMongoUser.role()))));
    }
    public MongoUserDTOOut saveUser(MongoUser user) {
        if (user.username() == null || user.username().length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }

        if (user.password() == null || user.password().length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }
        if (repository.existsByUsername(user.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        MongoUser newUser = repository.save(
                new MongoUser(passwordEncoder.encode(user.password()), user.username(), idService.generateID(),  "BASIC"));
        return new MongoUserDTOOut(newUser.username(), newUser.id(), newUser.role());
    }
    public MongoUserDTOOut findByUsername(String username) {
        MongoUser user = repository.findByUsername(username).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new MongoUserDTOOut(user.username(), user.id(), user.role());
    }
}
