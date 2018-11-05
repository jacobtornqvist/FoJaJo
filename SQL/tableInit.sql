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

-------------//

create procedure user_createCustomer 
@username nvarchar(25), 
@password nvarchar(25)
as
begin
set nocount on
insert into Customer values (@username, @password)
end

create procedure user_changePassword 
@username nvarchar(25), 
@newPassword nvarchar(25)
as
begin 
set nocount on
update Customer 
set password = @newPassword 
where username = @username
end

create procedure user_deleteCustomer 
@username nvarchar(25)
as
begin
set nocount on
delete from Customer 
where username = @username
end

create procedure user_getCustomer 
@username nvarchar(25)
as
begin 
set nocount on
select * 
from Customer 
where username = @username;
end


create procedure user_getAllEntries
@accountNbr int
as
begin
set nocount on
select * 
from LogEntry 
where accountNumber = @accountNbr
end

--// inte klart �n
 
create procedure user_transfer 
@fromAccount int, 
@toAccount int, 
@amount float
as
begin try
begin transaction
exec user_withdraw @fromAccount, @amount
exec user_deposit @toAccount, @amount
commit
end try
begin catch
end catch
end 

create procedure user_withdraw 
@fromAccount int,
@amount float
as
begin
begin try
begin transaction
update BankAccount set balance -= @amount where accountNumber = @fromAccount
commit
end try
begin catch
end catch
end

create procedure user_deposit 
@toAccount int, 
@amount float
as
begin try
begin transaction
update BankAccount set balance += @amount where accountNumber = @toAccount
commit
end try
begin catch
end catch

create trigger user_checkAmount
on [dbo].[BankAccount]
after update 
as
begin
select * from inserted 
end



--JONATHANS TRIGGER TEST

--skapar custom error 50001
exec sp_addmessage 50001, 15, 'Insufficient funds on account';
--kollar s� att erroret �r skapat
select * from sys.messages where message_id > 50000
--tar bort det skapade errort
drop sp_dropmessage 50001;

create procedure user_withdraw 
@fromAccount int,
@amount float
as
begin try
update BankAccount set balance -= @amount where accountNumber = @fromAccount
end try
begin catch
throw
end catch


create trigger user_checkAmount
on BankAccount
after update 
as
if(select balance from inserted) < 0
begin
raiserror (50001, 15, 1);
rollback
return
end


