package com.br.rhsalvation.fiap.demo.service;

import com.br.rhsalvation.fiap.demo.entity.Employee;
import com.br.rhsalvation.fiap.demo.enums.Role;
import com.br.rhsalvation.fiap.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository repository;

    public Employee register(Employee employee) {
        return repository.save(employee);
    }

    public Employee login(String email, String senha) {
        return repository.findByEmail(email)
                .filter(e -> e.getSenha().equals(senha))
                .orElse(null);
    }
}
