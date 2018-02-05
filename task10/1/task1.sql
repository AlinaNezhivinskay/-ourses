create database db1;
use db1;
create table product(
maker varchar(10) not null,
model varchar(50) primary key not null,
type varchar(50) not null
);
create table pc (
code int primary key not null,
model varchar(50) not null,
speed smallint not null,
ram smallint not null,
hd real not null,
cd varchar(10) not null,
price int,
foreign key(model) references product(model)
);
create table laptop (
code int primary key not null,
model varchar(50) not null,
speed smallint not null,
ram smallint not null,
hd real not null,
price int,
screen tinyint not null,
foreign key(model) references product(model)
);
create table printer (
code int primary key not null,
model varchar(50) not null,
color char(1) not null,
type varchar(10) not null,
price int,
foreign key(model) references product(model)
);
insert product(maker,model,type) values
('Jet','Jet MultiGame FX430D16H05S12G105GM5K','PC'),
('ASUS','Asus X540','Laptop'),
('HAFF','Haff Maxima AREA G4','PC'),
('EPSON','Epson Expression Home XP-342','Printer'),
('SAMSUNG','Samsung SCX-3400','Printer'),
('LENOVO','Lenovo IdeaPad 320-15','Laptop'),
('A','A 123','PC'),
('A','A 124','Laptop'),
('B','B 785','PC');
insert pc(code,model,speed,ram,hd,cd,price) values
(1,'Haff Maxima AREA G4',4000,16000,2000,'4x',500),
(2,'Jet MultiGame FX430D16H05S12G105GM5K',380,16000,500,'12x',700),
(3,'B 785',4000,16000,2000,'24x',350),
(4,'A 123',3800,16000,500,'12x',450);
insert laptop(code,model,speed,ram,hd,screen,price) values
(1,'Asus X540',1600,2000,10,17,1670),
(2,'Lenovo IdeaPad 320-15',3000,8000,100,15,495),
(3,'A 124',300,4000,100,22,1200);
insert printer(code,model,color,type,price) values
(1,'Epson Expression Home XP-342','y','Jet',232),
(2,'Samsung SCX-3400','n','Laser',300),
(3,'Epson Expression Home XP-342','y','Laser',500);