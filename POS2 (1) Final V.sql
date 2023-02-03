--Create DataBase POS2
Use POS2

create table Manager
(
 ManagerID varchar(10) primary key,
Password varchar(20),
	--foreign key (ManagerID) references loggedin(LoginID)
)
insert into Manager(ManagerID,Password)
		values('asad12','321')
insert into Manager(ManagerID,Password)
		values('hamza123','321')
--drop table Manager
select * from Manager
create table Cashier
(
	
	FirstName varchar(20),
	LastName varchar(20),
	CashierID varchar(10) primary key,
	Password varchar(20) not null,
	Age varchar(2),
	CNIC varchar(13),
	PhoneNumber char(11),
	Address varchar(40),
	
	
	
	--foreign key(CashierID) references loggedin(LoginID)
)
--drop table Cashier
insert into Cashier(FirstName,LastName,CNIC,PhoneNumber,Age,CashierID,Password,[Address])
			values ('ali','Musa','1243232','03214567123','12','ali123','123','PGECHS PHASE 2,15-E')
select* from Cashier
Delete from Cashier
create table Customer
(
	
	FirstName varchar(20),
	LastName varchar(20),
	CNIC char(13),
	Phone char(11),
	Age varchar(30), 
	CustomerID int primary key,
	Address varchar(40),	
)
insert into Customer(FirstName,LastName,CNIC,Phone,Age,CustomerID,[Address])
		    values('Ayyan','Ali','12435322','03421323532','20',123,'14E')
			select *from Customer
--drop table Customer
--drop table Item
Create table Item
(
	Barcode_NO varchar(13) primary key,
	productID int not null,
	ProductName varchar(20),
	category varchar(20) not null,
	Quantity int,
	foreign key(productID) references ProductSpecification(productID),
	foreign key(category) references Category(categoryName),
	Price float
)

create table Category
(
categoryName varchar(20) primary key,
)
--drop table Category
--select * from Item
insert into Item(Barcode_NO,productID,ProductName,category,Quantity,Price)
		values('12323',123,'iphone-12','mobile',100,3000),
		      ('12324',124,'apple','fruits',10,3),
			  ('12325',125,'watch','electronics',25,680.0)
			  select*from Item
Create table ProductSpecification  ----INVENTORY TABLE CHUSSER
(	
	productID int primary key,
	ProductName varchar(20),
	Category varchar(20),
	Cost_Price float,
	Sales_Price float,
	Description varchar(100)
)
--drop table ProductSpecification
	insert into ProductSpecification(productID,ProductName,Cost_Price,Sales_Price,Description)
			values(123,'iphone-12',1000,3000,'Its an iphone with a camera'),
				  (124,'apple',1,3,'its an apple with no camera')
Create table Attendence
(
	CashierID varchar(10),
	[Year] varchar(4),
	[Date] varchar(2),
	[Month] varchar(20),
	CheckIn int,
	In_mode varchar(3),
	CheckOut int,
	Out_Mode varchar(3)
)

--drop table Attendence
create table voucher
(
	V_code varchar(10) primary key,
	Discount float,
)
insert into voucher(V_code,Discount)
			values('250D',1902.4)
--drop table voucher	
Create table RetailDetails
(
	BatchNo int primary key,
	Quantity int,
	Expiration_date varchar(20)
)
Create table TransactionDetails
(
	transactionID int primary key,
	transactionDate varchar(20),
	transactionCostPrice int,
	transactionAmount int

)
select * from TransactionDetails
drop table TransactionDetails

create table temp
(
	transactionID int primary key,
	transactionDate varchar(20),
)
-----------LOGGED IN----------------------------------------------
create table loggedin
(
	LoginID varchar(10) primary key,
	Password varchar(20)
)
----drop table loggedin
insert into loggedin (LoginID,[Password])
	values ('ali123','123'),
	       ('asad12','321')
		select *from loggedin   
--drop table loggedin
-----------------------------------------------------

--drop table LoginTable
----------------------------------------------------------------------------------------------------------------------
----=================================Attendence Record Procedure==============================================================================
--drop proc AttendenceRecord
Go
create proc AttendenceRecord
@Cashierid varchar(10),
@Year varchar(4),
@Date varchar(2),
@Month varchar(20),
@Checkin int,
@INMODE varchar(3),
@Checkout int,
@OUTMODE varchar(3),
@status int output
as
begin
	  if exists(select * from Cashier where @Cashierid=CashierID)
	   begin
			if @Checkin<10 and @INMODE='AM'
			 begin
			 	insert into Attendence values(@Cashierid,@Year,@Date,@Month,@Checkin,@INMODE,@Checkout,@OUTMODE)
			    set @status=0
			 end
			 else
			 begin
				insert into Attendence values(@Cashierid,@Year,@Date,@Month,@Checkin,@INMODE,@Checkout,@OUTMODE)
			    set @status=1
			 end
	   end
	  else
	   begin
			
			set @status=2
	   end
end
--drop proc AttendenceRecord
----=========================================Product=Management=Procedure========================================================================
--drop proc ProductManagement
Go
create procedure ProductManagement
@OldproductID int,
@NewProductId int,
@OldProductName varchar(20),
@NewProductName varchar(20),
@O_CostPrice float,
@N_CostPrice float,
@O_SalesPrice float,
@N_SalesPrice float,
@N_category varchar(10),-----ADD this category in product management
@O_category varchar(10),
@O_Description varchar(100),
@N_Description varchar(100),
@status int output
as
begin
		if exists(select * from ProductSpecification where @OldproductID=productID)
		begin
			 insert into ProductSpecification values(@NewProductId,@NewProductName,@N_CostPrice,@N_SalesPrice,@N_category,@N_Description)
			 set @status=1
		end
		else
		begin
			 set @status=0
		end
end
---======================================================ADD=CASHIER=============================================================
GO
create procedure AddCashier

@firstname varchar(20),
@lastname varchar(20),
@CNIC varchar(13),
@Phone char(11),
@Age varchar(2),
@password varchar(20),
@ID varchar(10),
@Address varchar(40),
@status int output



as
begin
		if not exists(select * from Cashier where @ID=CashierID)
		begin
			insert into Cashier values(@firstName,@lastname,@ID,@password,@Age,@CNIC,@Phone,@Address)		
			--insert into loggedin values(@ID,@password)
			set @status=1
		end

		else
		begin 
			set @status=0
		end
end	

--drop proc AddCashier
---======================================================Remove=CASHIER=============================================================
--drop proc RemoveCashier
GO
create procedure RemoveCashier

@ID varchar(10),
@status int output 
as
begin
		if exists(select * from Cashier where @ID=CashierID)
		begin
		  
		  Delete from Cashier
		  where @ID=CashierID
		--  Delete from loggedin
		 -- where @ID=LoginID
		  set @status=1
		  end
		else 
		begin
			set @status=0
		end
				
end

GO
create procedure ViewUsers
 
as
begin
		select * from Cashier				
end
--drop proc ViewUsers	
------=============================================================================================================================
--------------------------------------------------------ADD CUSTOMER-------------------------------------------------------
----=============================================================================================================================
--drop proc AddCustomer
Go
create procedure AddCustomer

@firstname varchar(20),
@lastname varchar(20),
@CNIC varchar(13),
@Phone char(11),
@Age varchar(30),
@CID varchar(10),
@Address varchar(40),
@status int output

as
begin 
		if not exists (select* from Customer where @CNIC=CNIC and @CID=CustomerID )
		begin
			insert into Customer values (@firstname,@lastname,@CNIC,@Phone,@Age,@CID,@Address)
			set @status=1
		end

		else
			begin
			set @status=0
		end
				
end
----------------------------------------------------------------------------------------------------------------------------------
----===============================================Additem===================================================================================

GO
create procedure AddItem
@Barcode_NO varchar(13),
@Quantity int,
@status int output
as
begin
	if exists(select * from Item where @Barcode_NO=Barcode_NO and Quantity>=@Quantity )
	begin
	update Item set Quantity=Quantity-@Quantity where @Barcode_No=Barcode_NO
	set @status=0
	end
	else
	begin
	set @status=1
	end
end

--drop proc AddItem

GO
create procedure AddCategory
@categoryName varchar(20),
@status int output
as
begin
	if not exists(select * from Category where @categoryName=categoryName)
	begin
	insert into Category values (@categoryName)
	set @status=1
	end
	else
	begin
	set @status=0
	end
end
-----===========================================================================================================
------------------------------------------ADD VOUCHER-----------------------------------------------------------------

----===============================================================================================================
Go
create proc Addvoucher
@VoucherCode varchar(10),
@Discount float,
@status int output
as
begin
	if not exists(select * from voucher where @VoucherCode=V_code)
	begin
			insert into voucher values(@VoucherCode,@Discount)
			set @status=1
	end
	else 
	begin
		set @status=0
	end
end
--drop proc Addvoucher
select * from voucher

GO
create procedure removeCategory

@name varchar(20),
@status int output 
as
begin
		if exists(select * from Category where @name=categoryName)
		begin
		  
		  Delete from Category
		  where @name=categoryName
		  set @status=1
		  end
		else 
		begin
			set @status=0
		end
				
end



GO
create procedure removeVoucher

@id varchar(20),
@status int output 
as
begin
		if exists(select * from voucher where @id=V_code)
		begin
		  
		  Delete from voucher
		  where @id=V_code
		  set @status=1
		  end
		else 
		begin
			set @status=0
		end
				
end

----------------------------------------------------------------------------------------------------------------------
---================================Inventory management Procedure-==============================================================
   
----------------------------------------------------------------------------------------------------------------------
--drop proc InventoryManagement
go
create proc InventoryManagement
@Barcode_No varchar(13),
@productID int,
@ProductName varchar(20),
@category varchar(20),
@Quantity int,
@Cost_Price float,
@Sales_Price float,
@Description varchar(100),
@status int output
as
begin
	 if not exists(select * from Item where @Barcode_No=Barcode_NO)
	  begin
	  			insert into ProductSpecification values(@ProductID,@ProductName,@category,@Cost_Price,@Sales_Price,@Description)
			insert into Item values(@Barcode_No,@ProductID,@ProductName,@category,@Quantity,@Sales_Price)

			set @status=0
	  end
	 else
	  begin
	        update Item set productID=@productID where @Barcode_No=Barcode_NO
		    update Item set ProductName=@ProductName where @Barcode_No=Barcode_NO
		    update Item set category=@category where @Barcode_No=Barcode_NO
		    update Item set Quantity=@Quantity where @Barcode_No=Barcode_NO
		    update Item set Price=@Sales_Price where @Barcode_No=Barcode_NO
		
		  set @status=1
	  end
end


create procedure Addtemp
@tDate varchar(20),
@tID int,
@status int output
as
begin
	if not exists(select * from temp where @tID=transactionID)
	begin
	insert into temp values (@tID,@tDate)
	set @status=1
	end
	else
	begin
	set @status=0
	end
end


GO
create procedure AddTransactionDetails
@tID int,
@tDate varchar(20),
@tCostPrice int,
@tAmount int,

@status int output
as
begin
	if not exists(select * from TransactionDetails where @tID=transactionID)
	begin
	insert into TransactionDetails values (@tID,@tDate,@tCostPrice,@tAmount)
	set @status=1
	end
	else
	begin
	set @status=0
	end
end

--drop proc AddTransactionDetails
-------------------=========================================================--------------------------------------
-------------------------------------REMOVE INVENTORY-------------------------------------------------------------
go
create proc RemoveInventoryManagement
@Barcode_No varchar(13),
@productID int,

@status int output
as
begin
	 if exists(select * from Item where @Barcode_No=Barcode_NO)
	  begin
			delete from Item where @Barcode_No=Barcode_NO
			delete from ProductSpecification where @ProductID=productID
			set @status=0
	  end
	 else
	  begin 
		    set @status=1
	  end
end

drop proc RemoveInventoryManagement

