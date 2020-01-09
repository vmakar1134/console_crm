drop database if exists ConsoleCRM;
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



INSERT INTO Employee (id, first_name, last_name, salary, department_id, degree_id)
VALUES (1, 'name_one', 'surname_one', 4434, null, null),
       (2, 'name_two', 'surname_two', 4334, null, null),
       (3, 'name_three', 'surname_three', 4366, null, null),
       (4, 'name_four', 'surname_four', 1211, null, null),
       (5, 'name_five', 'surname_five', 6677, null, null);

INSERT INTO Department (id, name, head_employee_id)
VALUES (1, 'dep_one', 3),
       (2, 'dep_two', 2),
       (3, 'dep_three', 1);

INSERT INTO Degree (id, name)
VALUES (1, 'assistant'),
       (2, 'assosiate'),
       (3, 'assosiate professor'),
       (4, 'professor');

UPDATE ConsoleCRM.Employee t
SET t.department_id = 2,
    t.degree_id     = 3
WHERE t.id = 5;
UPDATE ConsoleCRM.Employee t
SET t.department_id = 1,
    t.degree_id     = 1
WHERE t.id = 1;
UPDATE ConsoleCRM.Employee t
SET t.department_id = 3,
    t.degree_id     = 2
WHERE t.id = 3;
UPDATE ConsoleCRM.Employee t
SET t.department_id = 1,
    t.degree_id     = 2
WHERE t.id = 4;
UPDATE ConsoleCRM.Employee t
SET t.department_id = 2,
    t.degree_id     = 1
WHERE t.id = 2;

