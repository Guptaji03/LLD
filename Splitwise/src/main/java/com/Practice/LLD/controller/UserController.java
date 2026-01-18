package com.Practice.LLD.controller;

import com.Practice.LLD.dto.UserDto;
import com.Practice.LLD.repositories.UserRepository;
import com.Practice.LLD.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Controller
public class UserController {

   private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userdto){
        UserDto user =  userService.createNewUser(userdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
