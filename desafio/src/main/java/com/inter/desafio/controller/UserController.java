package com.inter.desafio.controller;

import com.inter.desafio.model.dto.RequestUserDTO;
import com.inter.desafio.model.dto.ResponseUserDTO;
import com.inter.desafio.model.entity.UserEntity;
import com.inter.desafio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<UserEntity> getAll() {
        return ResponseEntity.ok().body( userService.getAll() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getById( @PathVariable Long id ) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ResponseUserDTO> getByName( @PathVariable String nome ) {
        return ResponseEntity.ok().body(userService.getByName(nome));
    }

    @PostMapping("/")
    public ResponseEntity<ResponseUserDTO> saveUser(@RequestBody RequestUserDTO user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }
}
