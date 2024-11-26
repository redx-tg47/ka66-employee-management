package io.ka66.controller;

import io.ka66.model.Employee;
import io.ka66.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @GetMapping("/filter")
    public List<Employee> getEmployeesBySalary(@RequestParam double minSalary, @RequestParam double maxSalary) {
        return employeeService.getEmployeesBySalary(minSalary, maxSalary);
    }

    @GetMapping("/{id}/tax")
    public double calculateTax(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employeeService.calculateTax(employee.getSalary());
    }

}