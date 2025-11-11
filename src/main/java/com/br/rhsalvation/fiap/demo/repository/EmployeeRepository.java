package com.br.rhsalvation.fiap.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.rhsalvation.fiap.demo.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
