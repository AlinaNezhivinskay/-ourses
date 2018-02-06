create database carservice;
use carservice;
create table garage(
id bigint primary key not null auto_increment,
is_free tinyint(1) not null default 1
);
create table master (
id bigint primary key not null auto_increment,
name varchar(45),
is_free tinyint(1) not null default 1
);
create table carservice_order (
id bigint primary key not null auto_increment,
order_state varchar(45) not null,
submission_date date not null,
execution_date date,
planned_start_date date,
price double not null,
garage_id bigint not null, 
master_id bigint,
foreign key(garage_id) references garage(id),
foreign key(master_id) references master(id),
unique key(order_state,planned_start_date,garage_id)
);
insert garage(is_free) values
(0),
(0),
(1),
(0),
(1);
insert master(name,is_free) values
('Alsopp Make',1),
('Jeff Kate',1),
('Lewin Dima',0),
('Kirk Petr',0),
('Clapton Dima',1);
insert carservice_order(order_state,submission_date,execution_date,planned_start_date,price,garage_id,master_id) values
("EXECUTABLE",'2017-11-21','2018-02-20','2018-02-21',850,1,null),
("EXECUTABLE",'2018-02-06','2018-02-10','2018-02-08',125,2,3),
("EXECUTED",'2017-10-07','2017-10-10','2017-10-20',250,3,2),
("REMOTE",'2018-02-06','2018-02-10','2018-02-06',100,5,1),
("CANCELED",'2017-12-15','2017-12-28',null,800,4,null),
("EXECUTABLE",'2018-01-09','2018-02-03','2018-06-08',300,4,4);