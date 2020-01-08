package service;

import models.Employee;

public interface DepartmentDaoService {

    Employee getHeadOfDepartment(String departmentName);

    Long countEmployeeByDepartmentAndDegree(String departmentName, String degreeName);

    Long getAverageSalary(String departmentName);

    Long countEmployee(String departmentName);

}
