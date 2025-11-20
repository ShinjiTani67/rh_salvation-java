package com.br.rhsalvation.fiap.demo.entity;

import com.br.rhsalvation.fiap.demo.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.EMPLOYEE;
}
