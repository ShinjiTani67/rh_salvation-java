package com.br.rhsalvation.fiap.demo.dto;

import com.br.rhsalvation.fiap.demo.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String name;
    private Role role;
    private String token;
}