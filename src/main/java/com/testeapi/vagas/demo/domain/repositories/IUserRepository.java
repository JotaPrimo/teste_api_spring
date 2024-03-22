package com.testeapi.vagas.demo.domain.repositories;

import com.testeapi.vagas.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
