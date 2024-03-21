package com.testeapi.vagas.demo.web.services.implementation;

import com.testeapi.vagas.demo.entities.User;
import com.testeapi.vagas.demo.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.repositories.UserRepository;
import com.testeapi.vagas.demo.web.services.interfaces.IUserService;
import com.testeapi.vagas.demo.web.dtos.UserCreateDTO;
import com.testeapi.vagas.demo.web.dtos.UserUpdateDTO;
import com.testeapi.vagas.demo.web.dtos.mapper.UserMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> getAllSortedByNameAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return userRepository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public List<User> getAllSortedByNameDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        return userRepository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public List<User> getAllSortedByIdAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return userRepository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public List<User> getAllSortedByIdDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return userRepository.findAll(sort);
    }

    @Transactional(readOnly = false)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário de id #%s não foi encontrado", id))
        );
    }

    @Transactional
    public User store(UserCreateDTO userCreateDTO) {
        return userRepository.save(UserMapper.toModel(userCreateDTO));
    }

    @Transactional
    public void delete(Long id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }

    @Transactional
    public User update(Long id, UserUpdateDTO userUpdateDTO) {
        User user = this.findById(id);

        user.setCpf(userUpdateDTO.getCpf());
        user.setName(userUpdateDTO.getName());
        user.setEmail(userUpdateDTO.getEmail());

        return userRepository.save(user);
    }

    @Transactional
    public User inativar(Long id) {
        User user = this.findById(id);

        user.setAtivo(User.INATIVO);

       return userRepository.save(user);
    }
}
