USE restaurantmanagementsystem;

LOAD DATA INFILE '/usr/local/mysql-8.0.32-macos13-arm64/tmp/records/Employees.csv' INTO TABLE Employees
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (@Emp_ID, @First_Name, @Last_Name, @SSN, @DOB, @Email, @Phone, @Street1, @Street2, @City, @State, @Zip, @Status, @Role, @Wage)
  SET
	EmployeeId = @Emp_ID, 
    FirstName = @First_Name, 
    LastName = @Last_Name, 
    SSN = @SSN, 
    DOB = @DOB, 
    Email = @Email, 
    Phone = @Phone, 
    Street1 = @Street1, 
    Street2 = @Street2, 
    City = @City, 
    State = @State, 
    Zip = @Zip, 
    Status = @Status,
    Role = @Role,
    Wage = @Wage;
  
LOAD DATA INFILE '/usr/local/mysql-8.0.32-macos13-arm64/tmp/time_clocks_2023_01_01_2023_03_31.csv' INTO TABLE TimeClocks
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (@Date, @Store_Name, @Employee_Number, @First_Name, @Last_Name, @Emp_Job_Code, @Clock_In_Time, @Clock_Out_Time, @Unpaid_Brk_Minutes)
  SET 
    EmployeeId = @Employee_Number,
    Date = STR_TO_DATE(@Date,'%m-%d-%Y'),
    ClockInTime = @Clock_In_Time,
    ClockOutTIme = @Clock_Out_Time,
    UnpaidBreakMin = @Unpaid_Brk_Minutes;
  
LOAD DATA INFILE '/usr/local/mysql-8.0.32-macos13-arm64/tmp/checks_2023_01_01_2023_03_31.csv' INTO TABLE Checks
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (@Str_Date, @Store_ID, @Store_Name, @Region, @Area, @Day_Part_ID, @Day_Part, @Rev_Center_ID, @Rev_Center, @Emp_ID, @Last_Name, @First_Name, @Employee_Export_ID,
  @Check_Num, @Table_Desc, @Guest_Count, @Item_Count, @Item_Sales, @Net_Sales, @Payment, @Comps, @Promos, @Tax, @GST_Tax, @Tax_Exempt_Amt, @Surcharge, 
  @Order_Mode_Charges, @Additional_Charges, @Open_Time, @First_Order_Time, @Last_Order_Time, @Close_Time, @Actual_Close_Time, @Check_Close_Time, @Check_Time, 
  @Last_Payment_Time, @Seat_Time, @Last_Bump_Time, @FK_Occasion_ID, @Tippable_Sales, @Tippable_Charge_Sales, @Check_Counter)
  SET 
	CheckId = @Check_Num,
    Date = STR_TO_DATE(@Str_Date,'%m/%d/%Y'),
    RevCenterId = @Rev_Center_ID,
    EmployeeId = @EMP_ID,
    TableDesc = @Table_Desc,
    GuestCount = @Guest_Count,
    ItemCount = @Item_Count,
    NetSales = @Net_Sales,
    Comps = @Comps,
    Promo = @Promos,
    Tax = @Tax,
    TimeOpen = SEC_TO_TIME(@Open_Time * 60),
    TimeClose = SEC_TO_TIME(@Actual_Close_Time * 60);

LOAD DATA INFILE '/usr/local/mysql-8.0.32-macos13-arm64/tmp/payments_2023_01_01_2023_03_31.csv' INTO TABLE Payments
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (@Date, @Store_ID, @Store_Name, @Region, @Area, @Hour, @Minute, @Check_Number, @CC_Number, @Auth_Number, @CC_Expiration, @Type, @Description, 
  @Amount, @Autograt, @Tips, @Employee_ID, @Employee_Name, @Manager_ID, @Manager_Name)
  SET 
    CheckId = @Check_Number,
    Date = STR_TO_DATE(@Date,'%m/%d/%Y'),
    Time = SEC_TO_TIME((@Hour * 60 + @Minute) * 60),
    PayMethod = @Description,
    CCNumber =@CC_Number,
    AuthNumber = @Auth_Number,
    PayAmount = @Amount,
    Tips = @Tips,
    EmployeeId = @Employee_ID;
    
LOAD DATA INFILE '/usr/local/mysql-8.0.32-macos13-arm64/tmp/records/categories.csv' INTO TABLE Categories
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;  
    
LOAD DATA INFILE '/usr/local/mysql-8.0.32-macos13-arm64/tmp/records/menu_items.csv' INTO TABLE MenuItems
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;

LOAD DATA INFILE '/usr/local/mysql-8.0.32-macos13-arm64/tmp/check_Items_2023_01_01_2023_03_31.csv' INTO TABLE CheckItems
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (@Str_Date, @Store_ID, @Store_Name, @Region, @Area, @Check_Number, @Hour, @Minute, @Day_Part_ID, @Day_Part, @Order_Mode_ID, @Order_Mode, @Rev_Center_ID, 
  @Rev_Center, @Category_ID, @Category, @Item_ID, @Long_Name, @Chit_Name, @Short_Name, @Qty, @Original_Price, @Price_Sold, @Refund_Qty, @Refund_Amount, 
  @Employee_ID, @Employee_Name, @Tax_ID, @Tax_Name, @Tax_Rate, @Secondary_Tax_ID, @Secondary_Tax_Name, @Secondary_Tax_Rate, @Mod_Code, @Parent_Modifier_ID, 
  @QS_Quick_Combo_ID, @Terminal_ID, @Terminal_Name)
  SET 
	CheckId = @Check_Number,
	Date = STR_TO_DATE(@Str_Date,'%m/%d/%Y'),
    TimeCreated = SEC_TO_TIME((@Hour * 60 + @Minute) * 60),
    OrderModeId = @Order_Mode_ID,
    ItemId = @Item_ID,
    Qty = @Qty,
    RefundQty = @Refund_Qty,
    EmployeeId = @Employee_ID,
    ParentModifierId = IF(@Parent_Modifier_ID = 0, NULL, @Parent_Modifier_ID);
  