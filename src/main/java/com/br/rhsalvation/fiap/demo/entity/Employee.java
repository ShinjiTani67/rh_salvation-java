package com.br.rhsalvation.fiap.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String role = "ROLE_USER";
}
