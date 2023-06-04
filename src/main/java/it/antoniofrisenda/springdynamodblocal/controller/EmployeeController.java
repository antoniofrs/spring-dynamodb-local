package it.antoniofrisenda.springdynamodblocal.controller;

import it.antoniofrisenda.springdynamodblocal.model.Employee;
import it.antoniofrisenda.springdynamodblocal.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    private Employee getEmployee(@PathVariable String id){
        return employeeService.findById(id);
    }

    @PostMapping
    private Employee getEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    private Employee deleteEmployee(@PathVariable String id){
        return employeeService.delete(id);
    }

}
