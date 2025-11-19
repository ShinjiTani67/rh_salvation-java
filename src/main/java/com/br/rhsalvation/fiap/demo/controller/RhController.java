package com.br.rhsalvation.fiap.demo.controller;

import com.br.rhsalvation.fiap.demo.dto.RhDTO;
import com.br.rhsalvation.fiap.demo.service.RhService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/rh")
@Log
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RhController {

    private final RhService service;

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
            bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
            model.addAttribute("rh", rhDTO);
            return "cadastro";
        }

        service.save(rhDTO);
        return "redirect:/rh/list";
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
        return "redirect:/rh/list";
    }
}
