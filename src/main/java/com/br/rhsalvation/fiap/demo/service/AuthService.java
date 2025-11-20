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

        if (email.equals("rh@teste.com") && senha.equals("123456")) {
            Employee rh = new Employee();
            rh.setNome("Administrador RH");
            rh.setRole(Role.RH);
            return rh;
        }

        if (email.equals("employee@teste.com") && senha.equals("123456")) {
            Employee emp = new Employee();
            emp.setNome("Colaborador");
            emp.setRole(Role.EMPLOYEE);
            return emp;
        }

        return repository.findByEmail(email)
                .filter(e -> e.getSenha().equals(senha))
                .orElse(null);

    }
}
