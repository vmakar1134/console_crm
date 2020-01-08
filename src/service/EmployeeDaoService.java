package service;

import models.Employee;

import java.util.List;

public interface EmployeeDaoService {

    List<Employee> findAll(String search);

}
