create table if not exists developers(
id serial primary key,
name varchar(50) not null,
age integer not null,
gender varchar(10) not null);

create table if not exists skills(
id serial primary key,
name varchar(50) not null,
level varchar(50) not null);

create table if not exists projects(
id serial primary key,
name varchar(50) not null,
description varchar (300));

create table if not exists companies(
id serial primary key,
name varchar(50) not null,
description varchar (300) not null);

create table if not exists customers(
id serial primary key,
name varchar(50) not null,
description varchar(300) not null);

create table if not exists developers_projects(
id serial primary key,
developer_id integer not null,
project_id integer not null,
foreign key (developer_id) references developers (id),
foreign key (project_id) references projects (id));

create table if not exists developers_skills(
id serial primary key,
developer_id integer not null,
skill_id integer not null,
foreign key (developer_id) references developers (id),
foreign key (skill_id) references skills (id));

create table if not exists customers_projects(
id serial primary key,
customer_id integer not null,
project_id integer not null,
foreign key (customer_id) references customers (id),
foreign key (project_id) references projects (id));

create table if not exists companies_projects(
id serial primary key,
company_id integer not null,
project_id integer not null,
foreign key (company_id) references companies (id),
foreign key (project_id) references projects (id));