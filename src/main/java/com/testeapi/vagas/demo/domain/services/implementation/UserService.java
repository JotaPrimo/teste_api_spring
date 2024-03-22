package com.testeapi.vagas.demo.domain.services.implementation;

import com.testeapi.vagas.demo.domain.entities.User;
import com.testeapi.vagas.demo.domain.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.domain.repositories.jpa.IUserRepository;
import com.testeapi.vagas.demo.domain.services.interfaces.IUserService;
import com.testeapi.vagas.demo.web.records.user.UserCreateDTO;
import com.testeapi.vagas.demo.web.records.user.UserResponseDTO;
import com.testeapi.vagas.demo.web.records.user.UserUpdateDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
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
    public UserResponseDTO store(UserCreateDTO userCreateRequest) {
        // criando entity de user a partir de dto
        User user = userCreateRequest.toEntity();

        // salvando no banco
        userRepository.save(user);

        // retornando response
        return UserResponseDTO.userToResponseDto(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }

    @Transactional
    public User update(Long id, UserUpdateDTO userUpdateDTO) {
        User user = this.findById(id);

        user.setCpf(userUpdateDTO.cpf());
        user.setName(userUpdateDTO.name());
        user.setEmail(userUpdateDTO.email());

        return userRepository.save(user);
    }

    @Transactional
    public User inativar(Long id) {
        User user = this.findById(id);

        user.setAtivo(User.INATIVO);

       return userRepository.save(user);
    }
}
