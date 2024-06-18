package com.inter.desafio.controller;

import com.inter.desafio.model.dto.RequestUserDTO;
import com.inter.desafio.model.dto.ResponseUserDTO;
import com.inter.desafio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "End point´s do cadastro de usuarios disponíveis na aplicação.")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @Operation(summary = "Lista todos os usuários cadastrados")
    public ResponseEntity<List<ResponseUserDTO>> getAll() {
        return ResponseEntity.ok().body( userService.getAll() );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna o usuário que o id seja igual a requisição.")
    public ResponseEntity<ResponseUserDTO> getById( @PathVariable Long id ) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Lista usuários que contém o mesmo nome no banco de dados")
    public ResponseEntity<List<ResponseUserDTO>> getByName( @PathVariable String nome ) {
        return ResponseEntity.ok().body(userService.getByName(nome));
    }

    @PostMapping("/")
    @Operation(summary = "Salva um novo usuário no banco de dados")
    public ResponseEntity<ResponseUserDTO> saveUser(@RequestBody RequestUserDTO user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PutMapping("/id/{id}")
    @Operation(summary = "Atualiza o cadastro do usuário")
    public ResponseEntity<ResponseUserDTO> updateUserById(@Valid @PathVariable Long id, @RequestBody RequestUserDTO request) {
        return ResponseEntity.ok().body(userService.updateUserById(id, request));
    }

    @DeleteMapping("/id/{id}")
    @Operation(summary = "Apaga definitivamente um usuário no banco de dados")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.deleteUserById(id));
    }
}
