package com.inter.desafio.service;

import com.inter.desafio.model.dto.RequestUserDTO;
import com.inter.desafio.model.dto.ResponseUserDTO;
import com.inter.desafio.model.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity getAll();

    ResponseUserDTO getById(Long id);
    ResponseUserDTO getByName(String name);
    ResponseUserDTO saveUser(RequestUserDTO user);
}
