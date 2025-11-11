package com.br.rhsalvation.fiap.demo.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.rhsalvation.fiap.demo.repository.RhRepository;

@Controller
@RequestMapping("/rh")
@Log
public class RhController {

    private final RhRepository repository;

    public RhController(RhRepository repository){
        this.repository = repository;
    }

}
