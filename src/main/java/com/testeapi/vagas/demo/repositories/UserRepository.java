package com.testeapi.vagas.demo.repositories;

import com.testeapi.vagas.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
