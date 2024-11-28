package io.ka66.controller;

import io.ka66.model.Employee;
import io.ka66.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ka-66")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger("Ka-66");
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public Optional<Object> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping("/employee")
    public ResponseEntity<Integer> updateEmployeeById(@RequestBody Employee employee) {
        if (employee == null) {
            LOGGER.info("No employee provided for update");
            return ResponseEntity.badRequest().build();
        }

        Integer updatedRows = employeeService.updateEmployee(employee);
        LOGGER.info("Rows affected: {}", updatedRows);
        return ResponseEntity.ok(updatedRows);
    }

    @PutMapping("/employees/batch")
    public ResponseEntity<Integer> updateEmployees(@RequestBody List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            LOGGER.info("No employees provided for update");
            return ResponseEntity.badRequest().build();
        }

        int totalUpdatedRows = 0;

        for (Employee employee : employees) {
            totalUpdatedRows += employeeService.updateEmployee(employee);
        }

        LOGGER.info("Total rows affected: {}", totalUpdatedRows);
        return ResponseEntity.ok(totalUpdatedRows);
    }

    @GetMapping("/employee/filter")
    public List<Employee> getEmployeesBySalary(@RequestParam double minSalary, @RequestParam double maxSalary) {
        return employeeService.getEmployeesBySalary(minSalary, maxSalary);
    }

    @GetMapping("/employee/{id}/tax")
    public ResponseEntity<Double> calculateTax(@PathVariable int id) {
        Optional<Object> employeeOptional = employeeService.getEmployeeById(id);

        if (employeeOptional.isEmpty()) {
            LOGGER.info("Employee not found for ID: {} ", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Employee employee = (Employee) employeeOptional.get();
        double tax = employeeService.calculateTax(employee.getSalary());
        return ResponseEntity.ok(tax);
    }

}