package com.testeapi.vagas.demo.domain.services.interfaces;

import com.testeapi.vagas.demo.domain.entities.User;
import com.testeapi.vagas.demo.web.dtos.UserUpdateDTO;
import com.testeapi.vagas.demo.web.records.user.UserCreateRequest;
import com.testeapi.vagas.demo.web.records.user.UsuarioResponse;

import java.util.List;

public interface IUserService {
    List<User> getAllSortedByNameAsc();

    List<User> getAllSortedByNameDesc();

    List<User> getAllSortedByIdAsc();

    List<User> getAllSortedByIdDesc();

    User findById(Long id);

    UsuarioResponse store(UserCreateRequest userCreateRequest);

    void delete(Long id);

    User update(Long id, UserUpdateDTO userUpdateDTO);

    User inativar(Long id);
}
