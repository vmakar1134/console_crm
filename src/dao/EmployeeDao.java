package dao;

import connection.Database;
import models.Employee;
import service.DaoService;
import service.EmployeeDaoService;
import utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements DaoService<Employee>, EmployeeDaoService {

    private Connection connection;

    public EmployeeDao() {
        connection = Database.getConnection();
    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public Long insert(Employee employee) {
        Long id = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Employee(first_name, last_name, salary, department_id, degree_id) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            setEmployeeToStatement(employee, preparedStatement);
            preparedStatement.execute();
            DatabaseUtil.getGeneratedKey(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Employee> findAll(String search) {
        search = "%" + search + "%";
        List<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Employee e where e.first_name like ? or e.last_name like ?");
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employees.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        return employee;
    }

    private void setEmployeeToStatement(Employee employee, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setLong(3, employee.getSalary());
        preparedStatement.setObject(4, DatabaseUtil.getId(employee.getDepartment()), Types.BIGINT);
        preparedStatement.setObject(5, DatabaseUtil.getId(employee.getDegree()), Types.BIGINT);
    }

}
