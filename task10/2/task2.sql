/*Задание 1*/
select model,speed,hd 
from pc 
where price<500;
/*Задание 2*/
select product.maker 
from printer 
inner join product using (model);
/*Задание 3*/
select model,ram,screen 
from laptop 
where price>1000;
/*Задание 4*/
select * 
from printer 
where color='y';
/*Задание 5*/
select model,speed,hd 
from pc 
where (cd='12x' or cd='24x') and price<600;
/*Задание 6*/
select product.maker,speed 
from laptop 
inner join product using (model) 
where hd>=10;
/*Задание 7*/
select p.model, coalesce(pc.price,l.price,pr.price) as price
from product p
left join pc on p.model=pc.model
left join laptop l on p.model=l.model
left join printer pr on p.model=pr.model  
where coalesce(pc.price,l.price,pr.price) is not null and p.maker='B';
/*Задание 8*/
select distinct(maker)
from product p 
where type='PC' 
and p.maker not in (
	select distinct(maker)
	from product p 
	where type='Laptop');
/*Задание 9*/
select pr.maker 
from pc 
inner join product pr using (model) 
where speed>=450;
/*Задание 10*/
select model,price 
from printer 
where price = (
	select max(price) 
    from printer);
/*Задание 11*/
select avg(speed) as average_speed
from pc;
/*Задание 12*/
select avg(speed) as average_speed 
from laptop 
where price>1000;
/*Задание 13*/
select avg(speed) as average_speed 
from pc 
join product p using (model) 
where p.maker='A';
/*Задание 14*/
select speed,avg(price) as average_price 
from pc 
group by speed;
/*Задание 15*/
select hd 
from pc 
group by hd 
having count(*)>1;
/*Задание 16*/
select p1.code as big_code,p2.code as little_code,p1.speed,p1.ram
from pc p1 
join pc p2 on p1.code > p2.code
where p1.speed=p2.speed and p1.ram=p2.ram;
/*Задание 17*/
select p.type,l.model,l.speed 
from laptop l
join product p on l.model=p.model
where l.speed<(
	select min(speed)
    from pc);
/*Задание 18*/
select p.maker 
from printer 
join product p using(model) 
where color='y' and price = (
	select min(price) 
    from printer 
    where color='y');
/*Задание 19*/
select p.maker,avg(l.screen) 
from laptop l
join product p using(model) 
group by maker;
/*Задание 20*/
select maker,count(*) as count 
from product 
where type='PC' 
group by maker 
having count(*)>2;
/*Задание 21*/
select p.maker,max(pc.price) 
from pc 
join product p using(model) 
group by maker;
/*Задание 22*/
select speed,avg(price) as average_price 
from pc 
group by speed 
having speed>600;
/*Задание 23*/
select distinct(p.maker)
from pc
join product p on pc.model=p.model
where pc.speed>=750
and p.maker in (
	select distinct(p2.maker)
	from laptop l
    join product p2 on l.model=p2.model
	where l.speed>=750);
/*Задание 24*/
select p.model
from product p
left join pc on p.model=pc.model
left join laptop l on p.model=l.model
left join printer pr on p.model=pr.model  
where coalesce(pc.price,l.price,pr.price) is not null and coalesce(pc.price,l.price,pr.price)=(
	select max(coalesce(pc1.price,l1.price,pr1.price))
	from product p1
	left join pc pc1 on p1.model=pc1.model
	left join laptop l1 on p1.model=l1.model
	left join printer pr1 on p1.model=pr1.model  
	where coalesce(pc1.price,l1.price,pr1.price) is not null);
/*Задание 25*/
select p.maker
from pc
join product p on pc.model=p.model
where pc.ram=(
	select min(pc2.ram)
    from pc pc2)
and pc.speed=(
	select max(pc3.speed)
    from pc pc3
    where pc3.ram=(
		select min(pc2.ram)
		from pc pc2));