package com.example.backend.controller;

import com.example.backend.model.MongoUser;
import com.example.backend.model.MongoUserDTOOut;
import com.example.backend.service.MongoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {
    private final MongoUserDetailsService mongoUserDetailsService;

    @PostMapping("/")
    public MongoUserDTOOut createUser(@RequestBody MongoUser user){
        return mongoUserDetailsService.saveUser(user);
    }
    @GetMapping("/me")
    public MongoUserDTOOut getUser(Principal principal){
        return mongoUserDetailsService.findByUsername(principal.getName());
    }
    @PostMapping("/login")
    public MongoUserDTOOut login(Principal principal) {
        return getUser(principal);
    }
    @PostMapping("/logout")
    public void logout() {
        // logout is handled by Spring Security
    }
}
