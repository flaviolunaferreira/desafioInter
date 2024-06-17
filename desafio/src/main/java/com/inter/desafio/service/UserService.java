package com.inter.desafio.service;

import com.inter.desafio.model.dto.RequestUserDTO;
import com.inter.desafio.model.dto.ResponseUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<ResponseUserDTO> getAll();

    ResponseUserDTO getById(Long id);
    ResponseUserDTO getByName(String name);
    ResponseUserDTO saveUser(RequestUserDTO user);
    ResponseUserDTO updateUserById(Long id);
    String deleteUserById(Long id);
}
