package com.testeapi.vagas.demo.web.services.interfaces;

import com.testeapi.vagas.demo.entities.User;
import com.testeapi.vagas.demo.web.dtos.UserCreateDTO;
import com.testeapi.vagas.demo.web.dtos.UserUpdateDTO;

import java.util.List;

public interface IUserService {
    List<User> getAllSortedByNameAsc();

    List<User> getAllSortedByNameDesc();

    List<User> getAllSortedByIdAsc();

    List<User> getAllSortedByIdDesc();

    User findById(Long id);

    User store(UserCreateDTO userCreateDTO);

    void delete(Long id);

    User update(Long id, UserUpdateDTO userUpdateDTO);

    User inativar(Long id);
}
