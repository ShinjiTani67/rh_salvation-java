package com.br.rhsalvation.fiap.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.rhsalvation.fiap.demo.entity.Employee;

import java.util.Optional;
import java.util.UUID;



public interface EmployeeRepository extends JpaRepository<Employee,UUID>{

    Optional<Employee> findByUuid(UUID uuid);
    Optional<Employee> findByEmail(String email);

}
