package com.example.backend.controller;

import com.example.backend.model.MongoUser;
import com.example.backend.model.MongoUserDTOOut;
import com.example.backend.service.MongoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public String getUser(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
