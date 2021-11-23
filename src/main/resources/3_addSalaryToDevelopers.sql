alter table developers add column salary integer default 0;
update developers set salary = '5000' where (id = '1');
update developers set salary = '1000' where (id = '2');
update developers set salary = '3400' where (id = '3');
update developers set salary = '3600' where (id = '4');
update developers set salary = '4000' where (id = '5');
update developers set salary = '3800' where (id = '6');
update developers set salary = '5000' where (id = '7');