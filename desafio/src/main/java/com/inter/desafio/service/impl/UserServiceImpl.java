package com.inter.desafio.service.impl;

import com.inter.desafio.model.dto.RequestUserDTO;
import com.inter.desafio.model.dto.ResponseUserDTO;
import com.inter.desafio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<ResponseUserDTO> getAll() {
        return null;
    }

    @Override
    public ResponseUserDTO getById(Long id) {
        return null;
    }

    @Override
    public ResponseUserDTO getByName(String name) {
        return null;
    }

    @Override
    public ResponseUserDTO saveUser(RequestUserDTO user) {
        return null;
    }

    @Override
    public ResponseUserDTO updateUserById(Long id) {
        return null;
    }

    @Override
    public String deleteUserById(Long id) {
        return null;
    }
}
