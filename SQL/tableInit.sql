create table Customer
(
	username varchar(25) primary key,
	password varchar(25) not null
)

create table BankAccount
(
	accountNumber int primary key,
	custUsername varchar(25) not null,
	accountName varchar(50),
	balance float not null,
	constraint fk_cust foreign key(custUsername) references Customer(username)
)

create table LogEntry
(
	logId int primary key identity(1, 1),
	accountNumber int not null,
	counterparty varchar(25),
	amount float,
	logTime datetime,
	constraint fk_acc foreign key(accountNumber) references BankAccount(accountNumber) on delete cascade
)

create procedure user_createBankAccount
@accNbr int,
@accName varchar(25),
@owner varchar(25),
@balance float
as
begin
set nocount on
insert into BankAccount
values (@accNbr,
@accName,
@owner,
@balance)
end

create procedure user_createCustomer
@accName varchar(25),
@password varchar(25)
as
begin
set nocount on
insert into Customer values (
@accName,
@password)
end 

create proc user_deleteBankAccount
@accNbr int
as
begin
set nocount on
delete from BankAccount
where accountNumber = @accNbr;
end

create proc user_getBankAccount
@accNbr int
as
begin
select *
from BankAccount
where accountNumber = @accNbr
end

create proc user_getAllBankAccounts
@custName varchar(25)
as
begin
select *
from BankAccount
where custUsername = @custName
end