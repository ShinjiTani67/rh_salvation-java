package com.br.rhsalvation.fiap.demo.controller;

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
