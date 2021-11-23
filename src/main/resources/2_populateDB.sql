insert into developers (name, age, gender) values ('Bohdan Holovchenko','24', 'Male');
insert into developers (name, age, gender) values ('Maria Mytenko','17', 'Female');
insert into developers (name, age, gender) values ('William Dog','30', 'Male');
insert into developers (name, age, gender) values ('Bashyr Hamster','33', 'Male');
insert into developers (name, age, gender) values ('John Smith','34', 'Male');
insert into developers (name, age, gender) values ('Smith Johnson','44', 'Male');
insert into developers (name, age, gender) values ('Hluhivska Glafira','78', 'Female');

insert into skills (name, level) values ('Java','Junior');
insert into skills (name, level) values ('Java','Middle');
insert into skills (name, level) values ('Java','Senior');
insert into skills (name, level) values ('Python','Junior');
insert into skills (name, level) values ('Python','Middle');
insert into skills (name, level) values ('Python','Senior');
insert into skills (name, level) values ('JavaScript','Junior');
insert into skills (name, level) values ('JavaScript','Middle');
insert into skills (name, level) values ('JavaScript','Senior');

insert into projects (name, description) values ('Shop123','Shopping app');
insert into projects (name, description) values ('FastPost','Mail app');
insert into projects (name, description) values ('BestFuel','Gas station app');
insert into projects (name, description) values ('ManyMoney','Bank app');
insert into projects (name, description) values ('CleverMind','Education app');
insert into projects (name, description) values ('DB-Pedia','Education app');

insert into companies (name, description) values ('IT-Factory','App development for general purposes');
insert into companies (name, description) values ('AppDev','App development for general purposes');
insert into companies (name, description) values ('EducDev','App development for educational purposes');

insert into customers (name, description) values ('24/7-Banking','Bank');
insert into customers (name, description) values ('Best Gas Station','Chain of gas stations');
insert into customers (name, description) values ('European university','Educational institution');

insert into developers_projects (developer_id, project_id) values ('1','1');
insert into developers_projects (developer_id, project_id) values ('1','2');
insert into developers_projects (developer_id, project_id) values ('2','1');
insert into developers_projects (developer_id, project_id) values ('2','2');
insert into developers_projects (developer_id, project_id) values ('3','3');
insert into developers_projects (developer_id, project_id) values ('3','4');
insert into developers_projects (developer_id, project_id) values ('4','3');
insert into developers_projects (developer_id, project_id) values ('4','4');
insert into developers_projects (developer_id, project_id) values ('5','5');
insert into developers_projects (developer_id, project_id) values ('5','6');
insert into developers_projects (developer_id, project_id) values ('6','5');
insert into developers_projects (developer_id, project_id) values ('6','6');
insert into developers_projects (developer_id, project_id) values ('7','1');
insert into developers_projects (developer_id, project_id) values ('7','2');

insert into developers_skills (developer_id, skill_id) values ('1','3');
insert into developers_skills (developer_id, skill_id) values ('1','9');
insert into developers_skills (developer_id, skill_id) values ('2','7');
insert into developers_skills (developer_id, skill_id) values ('3','2');
insert into developers_skills (developer_id, skill_id) values ('3','5');
insert into developers_skills (developer_id, skill_id) values ('4','9');
insert into developers_skills (developer_id, skill_id) values ('4','2');
insert into developers_skills (developer_id, skill_id) values ('5','3');
insert into developers_skills (developer_id, skill_id) values ('5','8');
insert into developers_skills (developer_id, skill_id) values ('6','5');
insert into developers_skills (developer_id, skill_id) values ('6','8');
insert into developers_skills (developer_id, skill_id) values ('7','9');
insert into developers_skills (developer_id, skill_id) values ('7','6');

insert into customers_projects (customer_id, project_id) values ('1','2');
insert into customers_projects (customer_id, project_id) values ('1','4');
insert into customers_projects (customer_id, project_id) values ('2','1');
insert into customers_projects (customer_id, project_id) values ('2','3');
insert into customers_projects (customer_id, project_id) values ('3','5');
insert into customers_projects (customer_id, project_id) values ('3','6');

insert into companies_projects (company_id, project_id) values ('1','1');
insert into companies_projects (company_id, project_id) values ('1','3');
insert into companies_projects (company_id, project_id) values ('2','2');
insert into companies_projects (company_id, project_id) values ('2','4');
insert into companies_projects (company_id, project_id) values ('3','5');
insert into companies_projects (company_id, project_id) values ('3','6');