package com.inter.desafio.service.impl;

import com.inter.desafio.exception.DuplicateValue;
import com.inter.desafio.exception.NotFound;
import com.inter.desafio.model.dto.user.RequestUserDTO;
import com.inter.desafio.model.dto.user.ResponseUserDTO;
import com.inter.desafio.model.entity.UserEntity;
import com.inter.desafio.model.repository.UserRepository;
import com.inter.desafio.uteis.RetornaCpfOuCnpj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetByName() {
        UserEntity userEntity = new UserEntity();
        userEntity.setNome("John Doe");
        userEntity.setEmail("john.doe@example.com");
        userEntity.setCpfCnpj("12345678901"); // CPF válido

        when(userRepository.findByNomeIgnoreCase(anyString())).thenReturn(Collections.singletonList(userEntity));

        List<ResponseUserDTO> result = userService.getByName("John Doe");

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNome());
    }

    @Test
    void testSaveUserComCpfOuCnpjValido() {
        RequestUserDTO userDTO = new RequestUserDTO();
        userDTO.setNome("Test User");
        userDTO.setEmail("test@example.com");
        userDTO.setCpfCnpj("12345678909"); // CPF válido para fins de teste

        UserEntity userEntity = userDTO.newUser();
        userEntity.setId(1L);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        ResponseUserDTO result = userService.saveUser(userDTO);

        assertEquals("Test User", result.getNome());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("12345678909", result.getCpfCnpj());

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testSaveUserComCpfOuCnpjInvalido() {
        RequestUserDTO userDTO = new RequestUserDTO();
        userDTO.setNome("Test User");
        userDTO.setEmail("test@example.com");
        userDTO.setCpfCnpj("123"); // CPF/CNPJ inválido

        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(userDTO));

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testUpdateUserById() {
        RequestUserDTO request = new RequestUserDTO();
        request.setNome("Updated User");
        request.setEmail("updated@example.com");
        request.setCpfCnpj("12345678909");

        UserEntity existingUser = new UserEntity();
        existingUser.setId(1L);
        existingUser.setNome("Existing User");
        existingUser.setEmail("existing@example.com");
        existingUser.setCpfCnpj("98765432109");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        ResponseUserDTO result = userService.updateUserById(1L, request);

        assertEquals("Updated User", result.getNome());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("12345678909", result.getCpfCnpj());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
    }
}
