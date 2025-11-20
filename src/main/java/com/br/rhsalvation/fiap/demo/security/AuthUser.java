package com.br.rhsalvation.fiap.demo.security;

import com.br.rhsalvation.fiap.demo.enums.Role;

public interface AuthUser {
    String getEmail();
    Role getRole();
}
