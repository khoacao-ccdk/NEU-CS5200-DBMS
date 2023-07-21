CREATE SCHEMA IF NOT EXISTS RestaurantManagementSystem;
USE RestaurantManagementSystem;

DROP TABLE IF EXISTS PaymentsAndTips;
DROP TABLE IF EXISTS CheckItems;
DROP TABLE IF EXISTS Checks;
DROP TABLE IF EXISTS MenuItems;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS PaymentsAndTips;
DROP TABLE IF EXISTS ClockEdits;
DROP TABLE IF EXISTS TimeClocks;
DROP TABLE IF EXISTS Employees;

CREATE TABLE Employees (
	EmployeeId INT NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    SSN VARCHAR(255),
    DOB DATE,
    Email VARCHAR(255),
    Phone VARCHAR(255),
    Street1 VARCHAR(255),
    Street2 VARCHAR(255),
    City VARCHAR(255),
    State VARCHAR(255),
    Zip VARCHAR(255),
    Status BOOLEAN,
    Role VARCHAR(255),
    Wage INT,
    CONSTRAINT pk_Employee_EmployeeId PRIMARY KEY (EmployeeId)
);

CREATE TABLE TimeClocks (
	TimeClockId INT AUTO_INCREMENT,
    EmployeeId INT,
    Date DATE,
    ClockInTime TIME,
    ClockOutTIme TIME,
    UnpaidBreakMin INT,
    CONSTRAINT pk_TimeClocks_TimeClockId PRIMARY KEY (TimeClockId),
    CONSTRAINT fk_TimeClocks_EmployeeId FOREIGN KEY (EmployeeId)
		REFERENCES Employees (EmployeeId)
        ON UPDATE CASCADE
);

CREATE TABLE ClockEdits (
	ClockEditId INT AUTO_INCREMENT,
    TimeClockId INT,
    Date DATE,
    EditedClockInTime TIME,
    EditedClockOutTIme TIME,
    EditedUnpaidBreakMin INT,
    CONSTRAINT pk_ClockEdits_ClockEditsId PRIMARY KEY (ClockEditId),
    CONSTRAINT fk_ClockEdits_TimeClockId FOREIGN KEY (TimeClockId)
		REFERENCES TimeClocks (TimeClockId)
        ON UPDATE CASCADE
);

CREATE TABLE Checks (
	CheckId INT NOT NULL,
    Date DATE NOT NULL,
    RevCenterId INT,
    EmployeeId INT,
    TableDesc VARCHAR(255),
    GuestCount INT,
    ItemCount INT,
    NetSales DECIMAL(20,2),
    Comps DECIMAL(20,2),
    Promo DECIMAL(20,2),
    Tax DECIMAL(20,2),
    TimeOpen TIME,
    TimeClose TIME,
    CONSTRAINT pk_Checks_CheckId_Date PRIMARY KEY(CheckId, Date),
	CONSTRAINT fk_Checks_EmployeeId FOREIGN KEY (EmployeeId)
		REFERENCES Employees (EmployeeId)
        ON UPDATE CASCADE
);

CREATE TABLE PaymentsAndTips (
	PaymentId INT AUTO_INCREMENT,
    CheckId INT,
    Date DATE,
    Time TIME,
    PayMethod VARCHAR(255),
    CCNumber VARCHAR(255),
    AuthNumber VARCHAR(255),
    PayAmount DECIMAL(6,2),
    Tips DECIMAL(6,2),
    EmployeeId INT,
    CONSTRAINT pk_PaymentsAndTips_PaymentId PRIMARY KEY (PaymentId),
    CONSTRAINT fk_PaymentsAndTips_CheckId_Date FOREIGN KEY (CheckId, Date)
		REFERENCES Checks (CheckId, Date)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Categories (
	CategoryId INT,
    CategoryName VARCHAR(100),
    CONSTRAINT pk_Categories_CategoryId PRIMARY KEY(CategoryId)
);

CREATE TABLE MenuItems (
	ItemId INT,
    ItemName VARCHAR(255) NOT NULL,
    ItemPrice DECIMAL(6,2) NOT NULL DEFAULT(0),
    CategoryId INT,
    CONSTRAINT pk_Items_ItemId PRIMARY KEY(ItemId),
    CONSTRAINT fk_Items_CategoryId FOREIGN KEY(CategoryId)
		REFERENCES Categories(CategoryId)
        ON UPDATE CASCADE,
	CONSTRAINT uq_Items_ItemId UNIQUE (ItemId)
);

CREATE TABLE CheckItems (
	CheckItemId INT AUTO_INCREMENT,       
    CheckId INT NOT NULL,
    Date DATE NOT NULL,
	ItemId INT,
    TimeCreated TIME,
    OrderModeId INT,
    Qty INT,
    RefundQty INT,
    EmployeeId INT,
    ParentModifierId INT,
    CONSTRAINT pk_CheckItems_CheckItemId PRIMARY KEY(CheckItemId),
    CONSTRAINT fk_CheckItems_ItemId FOREIGN KEY(ItemId)
		REFERENCES MenuItems(ItemId)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_CheckItems_CheckId_Date FOREIGN KEY(CheckId, Date)
		REFERENCES Checks(CheckId, Date)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_CheckItems_EmployeeId FOREIGN KEY (EmployeeId)
		REFERENCES Employees (EmployeeId)
        ON UPDATE CASCADE
);



