package io.ka66.repository;

import static io.ka66.constants.QueryConstants.DEPARTMENT;
import static io.ka66.constants.QueryConstants.ID;
import static io.ka66.constants.QueryConstants.NAME;
import static io.ka66.constants.QueryConstants.SALARY;

import io.ka66.constants.QueryConstants;
import io.ka66.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger("ka-66");
  private final JdbcTemplate jdbcTemplate;

  public EmployeeRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Employee> getAllEmployees() {
    String sql = QueryConstants.SELECT_FROM_DB_ORDER_BY_NAME;
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      Employee employee = new Employee();
      employee.setId(rs.getInt(ID));
      employee.setName(rs.getString(NAME));
      employee.setSalary(rs.getDouble(SALARY));
      employee.setDepartment(rs.getString(DEPARTMENT));
      return employee;
    });
  }

  public Optional<Object> getEmployeeById(int id) {
    String sql = QueryConstants.SELECT_FROM_DB_WHERE_K_ID;
    List<Employee> employees = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
      Employee employee = new Employee();
      employee.setId(rs.getInt(ID));
      employee.setName(rs.getString(NAME));
      employee.setSalary(rs.getDouble(SALARY));
      employee.setDepartment(rs.getString(DEPARTMENT));
      return employee;
    });

    if (employees.isEmpty()) {
      LOGGER.info("Employee not found for ID {}", id);
      return Optional.empty();
    }
    return Optional.of(employees.get(0));
  }

  public int deleteEmployee(int id) {
    String sql = QueryConstants.DELETE_FROM_DB_WHERE_K_ID;
    return jdbcTemplate.update(sql, id);
  }

  public Integer updateEmployee(Employee employee) {
    StringBuilder sqlBuilder = new StringBuilder(QueryConstants.UPDATE_DB);
    List<Object> params = new ArrayList<>();

    if (employee.getName() != null) {
      sqlBuilder.append("name = ?, ");
      params.add(employee.getName());
    }

    if (employee.getDepartment() != null) {
      sqlBuilder.append("department = ?, ");
      params.add(employee.getDepartment());
    }

    if (employee.getSalary() != null) {
      sqlBuilder.append("salary = ?, ");
      params.add(employee.getSalary());
    }

    // Remove the trailing comma and space
    if (params.isEmpty()) {
      LOGGER.info("No fields to update for employee with ID: {} ", employee.getId());
      return 0;
    }

    sqlBuilder.setLength(sqlBuilder.length() - 2); // Remove the last ", "
    sqlBuilder.append(" WHERE id = ?");
    params.add(employee.getId());

    String sql = sqlBuilder.toString();
    return jdbcTemplate.update(sql, params.toArray());
  }

  public List<Employee> getEmployeesBySalary(double minSalary, double maxSalary) {
    String sql = QueryConstants.SALARY_BETWEEN_MAX_AND_MINI;
    return jdbcTemplate.query(sql, new Object[]{minSalary, maxSalary}, (rs, rowNum) -> {
      Employee employee = new Employee();
      employee.setId(rs.getInt(ID));
      employee.setName(rs.getString(NAME));
      employee.setSalary(rs.getDouble(SALARY));
      employee.setDepartment(rs.getString(DEPARTMENT));
      return employee;
    });
  }

}