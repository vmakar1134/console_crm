package models;

import java.util.List;

public class Degree extends IdNameHolder {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Degree{" +
                "id=" + getId() +
                " name=" + getName() +
                '}';
    }

}
