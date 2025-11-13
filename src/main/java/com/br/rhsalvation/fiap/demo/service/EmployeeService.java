package com.br.rhsalvation.fiap.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setUuid(employee.getUuid());
        dto.setCpf(employee.getCpf());
        dto.setNome(employee.getNome());
        dto.setEmail(employee.getEmail());
        dto.setTelefone(employee.getTelefone());
        return dto;
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setUuid(dto.getUuid());
        employee.setCpf(dto.getCpf());
        employee.setEmail(dto.getEmail());
        employee.setNome(dto.getNome());
        employee.setTelefone(dto.getTelefone());
        return employee;
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);

        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }
        employee = repository.save(employee);
        return convertToDTO(employee);
    }

    public List<EmployeeDTO> getEmployee() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

    public EmployeeDTO findById(UUID uuid) {
        Optional<Employee> byUuid = repository.findByUuid(uuid);
        if (byUuid.isPresent())
            return convertToDTO(byUuid.get());

        throw new RuntimeException("Funcionário com id " + uuid + " não encontrado");
    }

    public EmployeeDTO findByEmail(String email) {
        Optional<Employee> employee = repository.findByEmail(email);
        if (employee.isPresent())
            return convertToDTO(employee.get());
        throw new RuntimeException("Funcionário com email " + email + " não encontrado");
    }
}
