package com.br.rhsalvation.fiap.demo.controller;

import com.br.rhsalvation.fiap.demo.dto.EmployeeDTO;
import com.br.rhsalvation.fiap.demo.entity.Employee;
import com.br.rhsalvation.fiap.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.br.rhsalvation.fiap.demo.repository.EmployeeRepository;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/employee")
@Log
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeService service;

    public EmployeeController(EmployeeRepository repository, EmployeeService service){
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/list")
public String listEmployee(Model model) {
    var employeeList = service.getEmployee();
    employeeList.forEach(e -> log.info("ID do funcionário: " + e.getUuid()));
    model.addAttribute("employee", employeeList);
    return "employeelist";
}

@GetMapping("/test")
@ResponseBody
public String test() {
    return "Conectado com sucesso";
}

@GetMapping("/new")
public String newEmployee(Model model) {
    model.addAttribute("employee", new EmployeeDTO());
    return "cadastro";
}

@PostMapping("/salvar")
public String saveEmployee(
        @Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
        BindingResult bindingResult,
        Model model
) {
    if (bindingResult.hasErrors()) {
        log.warning("Erros de validação ao salvar funcionário:");
        bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
        model.addAttribute("employee", employeeDTO);
        return "cadastro";
    }

    log.info("Salvando funcionário: " + employeeDTO);
    service.save(employeeDTO);
    return "redirect:/?cadastro=sucesso";
}

@GetMapping("/edit/{uuid}")
public String editEmployee(@PathVariable UUID uuid, Model model) {
    EmployeeDTO employee = service.findById(uuid);
    model.addAttribute("employee", employee);
    return "employeeformulario";
}

@GetMapping("/delete/{uuid}")
public String deleteEmployee(@PathVariable UUID uuid) {
    service.deleteById(uuid);
    return "redirect:employeelist";
    }

}
