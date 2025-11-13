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

@GetMapping
public String home(Model model, Authentication authentication) {
    if (authentication != null) {
        String email = authentication.getName();
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            model.addAttribute("employee", employee);

            Address address = employee.getAddress();
            if (address != null) {
                model.addAttribute("address", address);

                List<FloodZone> floodZones = floodZoneRepository.findByAddress_Uuid(address.getUuid());
                model.addAttribute("floodZones", floodZones);
            } else {
                model.addAttribute("floodZones", List.of());
            }
        }
    }
    return "employee";
}

}
