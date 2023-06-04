package it.antoniofrisenda.springdynamodblocal.repository;

import it.antoniofrisenda.springdynamodblocal.model.Employee;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeRepository extends DynamoDbCrudRepository<Employee, String> {

}
