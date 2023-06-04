package it.antoniofrisenda.springdynamodblocal.service;

import it.antoniofrisenda.springdynamodblocal.model.Employee;
import it.antoniofrisenda.springdynamodblocal.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findById(String id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test"));
    }

    public Employee delete(String id) {
        return employeeRepository.delete(id);
    }
}
