package com.br.rhsalvation.fiap.demo.controller;

import com.br.rhsalvation.fiap.demo.dto.LoginRequest;
import com.br.rhsalvation.fiap.demo.dto.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if(request.getEmail().equals("admin@teste.com") && 
           request.getSenha().equals("123456")) {

            return ResponseEntity.ok(new LoginResponse("fake-jwt-123"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body("Credenciais inválidas");
    }

}
