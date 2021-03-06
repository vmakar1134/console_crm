package models;

import java.util.List;

public class Department extends IdNameHolder {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + getId() +
                " name=" + getName() +
                '}';
    }

}
