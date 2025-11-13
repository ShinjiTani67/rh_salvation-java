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

@GetMapping
public String home(Model model, Authentication authentication) {
    if (authentication != null) {
        String email = authentication.getName();
        Optional<Rh> optionalRh = rhRepository.findByEmail(email);

        if (optionalRh.isPresent()) {
            Rh rh = optionalRh.get();
            model.addAttribute("rh", rh);

            Address address = rh.getAddress();
            if (address != null) {
                model.addAttribute("address", address);

                List<FloodZone> floodZones = floodZoneRepository.findByAddress_Uuid(address.getUuid());
                model.addAttribute("floodZones", floodZones);
            } else {
                model.addAttribute("floodZones", List.of());
            }
        }
    }
    return "rh";
}
}
