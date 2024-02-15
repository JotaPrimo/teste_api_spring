package com.testeapi.vagas.demo.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_users")
@Getter @Setter @NoArgsConstructor @ToString
public class User implements Serializable {

    public static final boolean ATIVO = true;
    public static final boolean INATIVO = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TINYINT DEFAULT 1")
    private boolean ativo;

    private LocalDateTime created_at;

    @Nullable
    private LocalDateTime inativado_em;

    public User(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }
}
