package com.testeapi.vagas.demo.services;

import com.testeapi.vagas.demo.entities.User;
import com.testeapi.vagas.demo.repositories.UserRepository;
import com.testeapi.vagas.demo.utils.TableFieldChecker;
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

    @Transactional
    public User store(User user) {
        return userRepository.save(user);
    }

}
