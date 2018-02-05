create database carservice;
use carservice;
create table garage(
id bigint primary key not null auto_increment,
is_free tinyint(1) not null default 1
);
create table master (
id bigint primary key not null auto_increment,
is_free tinyint(1) not null default 1
);
create table carservice_order (
id bigint primary key not null auto_increment,
order_state varchar(45) not null,
submission_date date not null,
execution_date date not null,
planned_start_date date not null,
price double not null,
garage_id bigint not null,
master_id bigint,
foreign key(garage_id) references garage(id),
foreign key(master_id) references master(id)
);
insert garage(is_free) values
(0),
(0),
(1);
insert master(is_free) values
(1),
(1),
(0);
insert carservice_order(order_state,submission_date,execution_date,planned_start_date,price,garage_id,master_id) values
("EXECUTABLE",'2017-11-21','2017-11-22','2017-11-21',177,1,null),
("EXECUTABLE",'2018-02-06','2018-02-10','2018-06-08',125,2,3);