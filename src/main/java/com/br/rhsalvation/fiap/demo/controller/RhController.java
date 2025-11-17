package com.br.rhsalvation.fiap.demo.controller;

import com.br.rhsalvation.fiap.demo.dto.RhDTO;
import com.br.rhsalvation.fiap.demo.entity.Rh;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.br.rhsalvation.fiap.demo.repository.RhRepository;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/rh")
@Log
public class RhController {

    private final RhRepository repository;

    public RhController(RhRepository repository){
        this.repository = repository;
    }


    @GetMapping("/list")
public String listRh(Model model) {
    var rhList = service.getRh();
    rhList.forEach(r -> log.info("ID do RH: " + r.getUuid()));
    model.addAttribute("rh", rhList);
    return "rhlist";
}

@GetMapping("/test")
@ResponseBody
public String test() {
    return "Conectado com sucesso";
}

@GetMapping("/new")
public String newRh(Model model) {
    model.addAttribute("rh", new RhDTO());
    return "cadastro";
}

@PostMapping("/salvar")
public String saveRh(
        @Valid @ModelAttribute("rh") RhDTO rhDTO,
        BindingResult bindingResult,
        Model model
) {
    if (bindingResult.hasErrors()) {
        log.warning("Erros de validação ao salvar RH:");
        bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
        model.addAttribute("rh", rhDTO);
        return "cadastro";
    }

    log.info("Salvando RH: " + rhDTO);
    service.save(rhDTO);
    return "redirect:/?cadastro=sucesso";
}

@GetMapping("/edit/{uuid}")
public String editRh(@PathVariable UUID uuid, Model model) {
    RhDTO rh = service.findById(uuid);
    model.addAttribute("rh", rh);
    return "rhformulario";
}

@GetMapping("/delete/{uuid}")
public String deleteRh(@PathVariable UUID uuid) {
    service.deleteById(uuid);
    return "redirect:rhlist";
    }
}
