import dao.DegreeDao;
import dao.DepartmentDao;
import dao.EmployeeDao;
import models.Degree;
import models.Employee;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("1 - Who is head of department {department_name} \n" +
                "2 - Show {department_name} statistic \n" +
                "3 - Show the average salary for department {department_name} \n" +
                "4 - Show count of employee for {department_name} \n" +
                "5 - Global search by {template}");

        int sc = new Scanner(System.in).nextInt();
        if (sc < 5) {
            System.out.println("enter department name:");
        } else {
            System.out.println("enter search template");
        }
        String scanner = new Scanner(System.in).nextLine();
        switch (sc) {
            case 1: {
                DepartmentDao departmentDao = new DepartmentDao();
                Employee headOfDepartment = departmentDao.getHeadOfDepartment(scanner);
                System.out.println("head of " + scanner + " is " + headOfDepartment);
                break;
            }
            case 2: {
                DepartmentDao departmentDao = new DepartmentDao();
                DegreeDao degreeDao = new DegreeDao();
                List<Degree> degrees = degreeDao.findAll();
                degrees
                        .forEach(d -> {
                            Long count = departmentDao.countEmployeeByDepartmentAndDegree(scanner, d.getName());
                            System.out.println(d.getName() + " - " + count.toString());
                        });
                break;
            }
            case 3: {
                DepartmentDao departmentDao = new DepartmentDao();
                Long averageSalary = departmentDao.getAverageSalary(scanner);
                System.out.println("The average salary of " + scanner + " is " + averageSalary.toString());
                break;
            }
            case 4: {
                DepartmentDao departmentDao = new DepartmentDao();
                Long countEmployee = departmentDao.countEmployee(scanner);
                System.out.println(countEmployee.toString());
                break;
            }
            case 5: {
                EmployeeDao employeeDao = new EmployeeDao();
                List<Employee> all = employeeDao.findAll(scanner);
                all.forEach(System.out::println);
            }

        }

    }

}
