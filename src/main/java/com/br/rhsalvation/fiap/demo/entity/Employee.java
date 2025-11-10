package com.br.rhsalvation.fiap.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="tb_user")
public class Employee {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 11)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String role = "ROLE_USER";
}
