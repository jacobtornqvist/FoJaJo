go
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
go
--BankAccount
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

go
create proc user_deleteBankAccount
@accNbr int
as
set nocount on
begin try
delete from BankAccount
where accountNumber = @accNbr;
if(@@rowcount < 1)
raiserror(50002, 15, 1)
end try
begin catch
throw;
end catch;

go
create proc user_getBankAccount
@accNbr int
as
set nocount on
begin
select *
from BankAccount
where accountNumber = @accNbr
end

go
create proc user_getAllBankAccounts
@custName varchar(25)
as
set nocount on
begin
select *
from BankAccount
where custUsername = @custName
end

--Customer

go
create procedure user_createCustomer 
@username nvarchar(25), 
@password nvarchar(25)
as
begin
set nocount on
insert into Customer values (@username, @password)
end

go
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

go
create procedure user_deleteCustomer 
@username nvarchar(25)
as
begin
set nocount on
delete from Customer 
where username = @username
end

go
create procedure user_getCustomer 
@username nvarchar(25)
as
begin 
set nocount on
select * 
from Customer 
where username = @username;
end

go
create procedure user_logIn
@username varchar(25),
@password varchar(25)
as
set nocount on
begin 
select * 
from Customer 
where username = @username
and password = @password;
if(@@rowcount < 1)
begin
raiserror(50004, 15,1);
end
end

--LogEntry

go
create procedure user_getAllEntries
@accountNbr int
as
begin
set nocount on
select * 
from LogEntry 
where accountNumber = @accountNbr
end

--Transaction

go
create procedure user_withdraw 
@fromAccount int,
@amount float
as
set nocount on
begin try
update BankAccount 
set balance -= @amount 
where accountNumber = @fromAccount

if(@@rowcount < 1)
raiserror(50002, 15, 1);
end try
begin catch
throw;
end catch

go
create procedure user_deposit 
@toAccount int, 
@amount float
as
set nocount on
begin try
update BankAccount 
set balance += @amount 
where accountNumber = @toAccount

if(@@rowcount < 1)
raiserror(50002, 15, 1)
end try
begin catch
throw;
end catch


go
create procedure user_insertIntoLogEntries 
@fromAccount int, 
@toAccount int, 
@amount float
as
set nocount on
declare @fromUsername as nvarchar(50)
declare @toUsername as nvarchar(50)

begin try
set @fromUsername = (select custUsername from BankAccount where accountNumber = @fromAccount)
set @toUsername = (select custUsername from BankAccount where accountNumber = @toAccount)
insert into LogEntry (accountNumber, counterparty, amount, logTime) values (@fromAccount,@toUsername,-@amount,CURRENT_TIMESTAMP)
insert into LogEntry (accountNumber, counterparty, amount, logTime) values (@toAccount,@fromUsername,@amount,CURRENT_TIMESTAMP)
end try
begin catch
throw
end catch

go
create procedure user_transfer 
@fromAccount int, 
@toAccount int, 
@amount float
as
set nocount on
begin
begin try
begin transaction
exec user_withdraw @fromAccount, @amount;
exec user_deposit @toAccount, @amount;
exec user_insertIntoLogEntries @fromAccount, @toAccount, @amount;
commit
end try
begin catch
rollback transaction;
throw
end catch
end

--Trigger

go
create trigger user_checkAmount
on BankAccount
after update
as
set nocount on
if update (balance)
begin
if ((select sum(balance) from inserted) < 0)
begin
raiserror (50001, 15, 1)
end
end


go
create trigger user_deleteAccountBalanceTrigger
on BankAccount
after delete
as
set nocount on
if(select sum(balance) from deleted) > 0
begin
raiserror(50003, 15, 1);
end

--JONATHANS TRIGGER TEST

--skapar custom error
exec sp_addmessage 50001, 15, 'Insufficient funds on account';
exec sp_addmessage 50002, 15, 'Account does not exist';
exec sp_addmessage 50003, 15, 'Balance is greater than 0';
exec sp_addmessage 50004, 15, 'Login failed: Username and Password did not match';

--kollar så att erroret är skapat
select * from sys.messages where message_id > 50000
--tar bort det skapade errort
drop sp_dropmessage 50001;
drop sp_dropmessage 50002;
drop sp_dropmessage 50003;


