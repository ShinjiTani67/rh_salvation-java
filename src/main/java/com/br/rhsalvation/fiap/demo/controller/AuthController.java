package com.br.rhsalvation.fiap.demo.controller;

import com.br.rhsalvation.fiap.demo.dto.AuthResponse;
import com.br.rhsalvation.fiap.demo.dto.LoginRequest;
import com.br.rhsalvation.fiap.demo.dto.LoginResponse;
import com.br.rhsalvation.fiap.demo.dto.RegisterRequest;
import com.br.rhsalvation.fiap.demo.entity.Employee;
import com.br.rhsalvation.fiap.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        Employee employee = new Employee();
        employee.setNome(request.getNome());
        employee.setEmail(request.getEmail());
        employee.setSenha(request.getSenha());
        employee.setRole(request.getRole());

        authService.register(employee);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Employee employee = authService.login(request.getEmail(), request.getSenha());

        if (employee == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }

        return ResponseEntity.ok(
                new AuthResponse(
                        employee.getNome(),
                        employee.getRole(),
                        "fake-jwt-blabla-123"
                )
        );
    }
}