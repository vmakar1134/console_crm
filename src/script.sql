# drop database if exists ConsoleCRM;
create database if not exists ConsoleCRM;
use ConsoleCRM;

create table if not exists Department
(
    id   bigint not null auto_increment,
    primary key (id),
    name varchar(255)
);

create table if not exists Degree
(
    id   bigint not null auto_increment,
    primary key (id),
    name varchar(255) unique
);

create table if not exists Employee
(
    id            bigint not null auto_increment,
    primary key (id),
    first_name    varchar(255),
    last_name     varchar(255),
    salary        bigint,
    department_id bigint,
    degree_id     bigint,
    constraint employee_department_fk foreign key (department_id) references Department (id),
    constraint employee_degree_fk foreign key (degree_id) references Degree (id)

);

alter table Department
    add column head_employee_id bigint unique,
    add constraint department_employee_head_fk foreign key (head_employee_id) references Employee (id);



