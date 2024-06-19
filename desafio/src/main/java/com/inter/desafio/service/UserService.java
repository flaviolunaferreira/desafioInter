package com.inter.desafio.service;

import com.inter.desafio.model.dto.user.RequestUserDTO;
import com.inter.desafio.model.dto.user.ResponseUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<ResponseUserDTO> getAll();

    ResponseUserDTO getById(Long id);
    List<ResponseUserDTO> getByName(String name);
    ResponseUserDTO saveUser(RequestUserDTO user);
    ResponseUserDTO updateUserById(Long id, RequestUserDTO request);
    String deleteUserById(Long id);
}
