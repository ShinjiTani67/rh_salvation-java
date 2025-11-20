package com.br.rhsalvation.fiap.demo.controller;

import com.br.rhsalvation.fiap.demo.entity.Employee;
import com.br.rhsalvation.fiap.demo.enums.Role;
import com.br.rhsalvation.fiap.demo.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final EmployeeRepository repository;

    public UserRestController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee user) {

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        user.setRole(Role.EMPLOYEE); // padrão
        repository.save(user);

        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}