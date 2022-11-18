create database if not exists Assign_Work; 
use Assign_Work;
show tables;

create table Employee(
	employeeId int auto_increment,
	Name varchar(100) not null,
	BirthDate date not null,
	Address varchar(200) not null,
	Gender int null,
	Salary double(12,2) not null,
	constraint pk_Employee_employeeId primary key(employeeId)
);

insert into Employee 
	(Name, BirthDate, Address, Gender, Salary)
values
	("vishal", "2000-02-02", "house no 42", 3, 5555.11),
	("rama", "1999-02-02", "house no 55", 3, 5544.15),
	("sham", "2002-06-01", "house no 99", 1, 8885.44),
	("lucky", "2008-08-08", "house no 88", 3, 5577.99);


select * from Employee;
