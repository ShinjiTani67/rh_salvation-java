package com.br.rhsalvation.fiap.demo.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.rhsalvation.fiap.demo.repository.EmployeeRepository;

@Controller
@RequestMapping("/employee")
@Log
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    } 

}
