package com.testeapi.vagas.demo.services;

import com.testeapi.vagas.demo.entities.User;
import com.testeapi.vagas.demo.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
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
    public User store(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }

    @Transactional
    public User update(Long id, User userUpdate) {
        User user = this.findById(id);

        user.setCpf(userUpdate.getCpf());
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());

        return userRepository.save(user);
    }

    @Transactional
    public User inativar(Long id) {
        User user = this.findById(id);

        user.setAtivo(User.INATIVO);

       return userRepository.save(user);
    }
}
