package models;

import java.util.Objects;

public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private Long salary;

    private Degree degree;

    private Department department;

    public class Builder {

        private Builder() {
        }

        public Builder setFirstName(String firstName) {
            Employee.this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            Employee.this.lastName = lastName;
            return this;
        }

        public Builder setSalary(Long salary) {
            Employee.this.salary = salary;
            return this;
        }

        public Builder setDegree(Degree degree) {
            Employee.this.degree = degree;
            return this;
        }

        public Builder setDepartment(Department department) {
            Employee.this.department = department;
            return this;
        }

        public Employee build() {
            return Employee.this;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public static Builder newBuilder() {
        return new Employee().new Builder();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", degree=" + degree +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(degree, employee.degree) &&
                Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, degree, department);
    }

}
