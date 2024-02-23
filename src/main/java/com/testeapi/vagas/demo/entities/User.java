package com.testeapi.vagas.demo.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Getter @Setter @NoArgsConstructor @ToString
public class User  {

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

    @Column(name = "ativo", columnDefinition = "boolean default false")
    private boolean ativo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @Nullable
    private LocalDateTime inativado_em;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todo> todos;

    public User(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    @PrePersist
    protected void onCreate() {
        setCreated_at(LocalDateTime.now());
    }
}
