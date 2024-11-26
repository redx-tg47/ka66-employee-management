package io.ka66.service;

import io.ka66.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    /**
     * Get Gll Employee Details*/
    public List<Employee> getAllEmployees() {
        LOGGER.info("getAllEmployees");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Prajwal");
        employee.setDepartment("Aeronautical");
        employee.setSalary(30000);

        Employee employee1 = new Employee();
        employee1.setId(2);
        employee1.setName("Ranjith");
        employee1.setDepartment("CS");
        employee1.setSalary(40000);

        Employee employee2 = new Employee();
        employee2.setId(3);
        employee2.setName("Chethan");
        employee2.setDepartment("Architecture");
        employee2.setSalary(50000);

        return List.of(employee, employee1, employee2);
    }


    public Employee getEmployeeById(int id) {
        LOGGER.info("getEmployeeById");

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Prajwal");
        employee.setDepartment("Aeronautical");
        employee.setSalary(30000);

        return employee;
    }

    public void updateEmployee(Employee employee) {
        LOGGER.info("updateEmployee");

    }

    public void deleteEmployee(int id) {
        LOGGER.info("deleteEmployee");
    }

    public List<Employee> getEmployeesBySalary(double minSalary, double maxSalary) {
        LOGGER.info("getEmployeesBySalary");
        return null;
    }

    public double calculateTax(double salary) {
        LOGGER.info("calculateTax");
        return 0;
    }
}
