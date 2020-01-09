import dao.DegreeDaoServiceImpl;
import dao.DepartmentDaoServiceImpl;
import dao.EmployeeDaoServiceImpl;
import models.Degree;
import models.Employee;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DepartmentDaoServiceImpl departmentDaoServiceImpl = new DepartmentDaoServiceImpl();
        DegreeDaoServiceImpl degreeDaoServiceImpl = new DegreeDaoServiceImpl();
        EmployeeDaoServiceImpl employeeDaoServiceImpl = new EmployeeDaoServiceImpl();

        System.out.println("deparments:");
        departmentDaoServiceImpl.findAll()
                .forEach(d -> System.out.println(d.getName()));


        System.out.println("1 - Who is head of department {department_name} \n" +
                "2 - Show {department_name} statistic \n" +
                "3 - Show the average salary for department {department_name} \n" +
                "4 - Show count of employee for {department_name} \n" +
                "5 - Global search by {template} \n" +
                "6 - exit");

        boolean exit = false;
        while (!exit) {
            System.out.println("enter number of option");
            int sc = new Scanner(System.in).nextInt();
            if (sc < 5) {
                System.out.println("enter department name:");
            } else if (sc == 5) {
                System.out.println("enter search template");
            }
            String scanner = new Scanner(System.in).nextLine();

            switch (sc) {
                case 1: {
                    Employee headOfDepartment = departmentDaoServiceImpl.getHeadOfDepartment(scanner);
                    System.out.println("head of " + scanner + " is " + headOfDepartment);
                    break;
                }
                case 2: {
                    List<Degree> degrees = degreeDaoServiceImpl.findAll();
                    degrees
                            .forEach(d -> {
                                Long count = departmentDaoServiceImpl.countEmployeeByDepartmentAndDegree(scanner, d.getName());
                                System.out.println(d.getName() + " - " + count.toString());
                            });
                    break;
                }
                case 3: {
                    Long averageSalary = departmentDaoServiceImpl.getAverageSalary(scanner);
                    System.out.println("The average salary of " + scanner + " is " + averageSalary.toString());
                    break;
                }
                case 4: {
                    Long countEmployee = departmentDaoServiceImpl.countEmployee(scanner);
                    System.out.println(countEmployee.toString());
                    break;
                }
                case 5: {
                    List<Employee> all = employeeDaoServiceImpl.findAll(scanner);
                    all.forEach(e -> System.out.println(e.getId() + " " + e.getFirstName() + " " + e.getLastName()));
                    break;
                }
                case 6: {
                    exit = true;
                }
                default:
                    break;
            }
        }
    }

}
