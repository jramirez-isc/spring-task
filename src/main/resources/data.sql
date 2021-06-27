DROP TABLE IF EXISTS PERSON;
DROP TABLE IF EXISTS POSITION;
DROP TABLE IF EXISTS EMPLOYEE;

create table PERSON (
    per_id    int AUTO_INCREMENT primary key,
    name      varchar(255),
    last_name varchar(255),
    address   varchar(255),
    cellphone varchar(255),
    city_name varchar(255)
);

create table POSITION (
    pos_id int AUTO_INCREMENT primary key,
    name   varchar(255)
);

create table EMPLOYEE (
    emp_id int AUTO_INCREMENT primary key,
    per_id int,
    pos_id int,
    salary decimal(20, 4)
);

alter table EMPLOYEE
    add constraint emp_per_fk foreign key (per_id) references PERSON(per_id) on delete cascade;
alter table EMPLOYEE
    add constraint emp_pos_fk foreign key (pos_id) references POSITION(pos_id) on delete cascade;