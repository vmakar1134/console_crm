package dao;

import connection.Database;
import models.Degree;
import models.Department;
import models.Employee;
import service.DaoService;
import service.DepartmentDaoService;
import utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoServiceImpl implements DaoService<Department>, DepartmentDaoService {

    private Connection connection;

    public DepartmentDaoServiceImpl() {
        this.connection = Database.getConnection();
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Department");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getLong("id"));
                department.setName(resultSet.getString("name"));
                departments.add(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Long insert(Department department) {
        Long id = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Department(name) value (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, department.getName());
            preparedStatement.execute();
            id = DatabaseUtil.getGeneratedKey(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Employee getHeadOfDepartment(String departmentName) {
        Employee employee = new Employee();
        Degree degree = new Degree();
        Department department = new Department();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from Employee " +
                            "left join Department d on Employee.id = d.head_employee_id " +
                            "where d.name = ?");
            preparedStatement.setString(1, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            setEmployee(employee, degree, department, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Long countEmployeeByDepartmentAndDegree(String departmentName, String degreeName) {
        Long count = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(e.id) from Employee e " +
                    "left join Department dep on e.department_id = dep.id " +
                    "left join Degree deg on e.degree_id = deg.id " +
                    "where dep.name = ? and deg.name = ?");
            preparedStatement.setString(1, departmentName);
            preparedStatement.setString(2, degreeName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            count = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Long getAverageSalary(String departmentName) {
        Long sum = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select sum(e.salary) from Employee e " +
                    "left join Department d on e.department_id = d.id " +
                    "where d.name = ?");
            preparedStatement.setString(1, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            sum = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    @Override
    public Long countEmployee(String departmentName) {
        Long count = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(e.id) from Employee e " +
                    "left join Department dep on e.department_id = dep.id " +
                    "where dep.name = ?");
            preparedStatement.setString(1, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            count = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private void setEmployee(Employee employee, Degree degree, Department department, ResultSet resultSet) throws SQLException {
        employee.setId(resultSet.getLong("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        degree.setId(resultSet.getLong("degree_id"));
        department.setId(resultSet.getLong("department_id"));
        employee.setDegree(degree);
        employee.setDepartment(department);
    }

}
