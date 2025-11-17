package com.br.rhsalvation.fiap.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;
  
@Entity
@Data
@ToString
@Table(name="tb_rh")
public class Rh {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String role = "ROLE_RH";

}
