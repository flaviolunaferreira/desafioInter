package com.inter.desafio.controller;

import com.inter.desafio.model.dto.RequestUserDTO;
import com.inter.desafio.model.dto.ResponseUserDTO;
import com.inter.desafio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseUserDTO>> getAll() {
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

    @PutMapping("/id/{id}")
    public ResponseEntity<ResponseUserDTO> updateUserById(@PathVariable Long id, @RequestBody RequestUserDTO request) {
        return ResponseEntity.ok().body(userService.updateUserById(id, request));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.deleteUserById(id));
    }
}
