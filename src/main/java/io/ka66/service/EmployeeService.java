package io.ka66.service;

import io.ka66.model.Employee;
import io.ka66.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger("ka-66");
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
      this.employeeRepository = employeeRepository;
    }

  /**
   * Get All Employee Details
   */
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Optional<Object> getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    public void deleteEmployee(int id) {
      int rowsAffected = employeeRepository.deleteEmployee(id);
      LOGGER.info("Rows deleted: {}", rowsAffected);
    }

    public Integer updateEmployee(Employee employee) {
      return employeeRepository.updateEmployee(employee);
    }

    public List<Employee> getEmployeesBySalary(double minSalary, double maxSalary) {
      LOGGER.info("Fetching employees with salary between {} and {}", minSalary, maxSalary);
      return employeeRepository.getEmployeesBySalary(minSalary, maxSalary);
    }

    public double calculateTax(double salary) {
      double tax = salary * 0.2; // Assuming 20% tax rate
      LOGGER.info("Calculated tax for salary {}: {}", salary, tax);
      return tax;
    }

}