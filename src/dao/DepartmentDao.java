package dao;

import connection.Database;
import models.Degree;
import models.Department;
import models.Employee;
import service.DaoService;
import service.DepartmentDaoService;
import utils.DatabaseUtil;

import java.sql.*;

public class DepartmentDao implements DaoService<Department>, DepartmentDaoService {

    private Connection connection;

    public DepartmentDao() {
        this.connection = Database.getConnection();
    }

    @Override
    public Department findById(Long id) {
        return null;
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
                    "left join Department d on e.department_id = d.name " +
                    "where d.id = ?");
            preparedStatement.setString(1, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();
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
