CREATE DATABASE CMS;

/*
CREATE TABLE `mydatabase`.`tbl_user` (
  `pk_userId` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pk_userId`));
*/


CREATE TABLE CMS.tbl_customer(
pk_customerId INT NOT NULL AUTO_INCREMENT,
customerName VARCHAR(45) NOT NULL,
mobileNumber VARCHAR(45) NOT NULL,
email VARCHAR(45) NOT NULL,
address VARCHAR(500) NOT NULL,
PRIMARY KEY (pk_customerId)
);
USE CMS;

SELECT * FROM tbl_customer;

INSERT INTO `cms`.`tbl_customer` (`customerName`, `mobileNumber`, `email`, `address`) VALUES ('rahul', '10002', 'rahul@test.com', 'trivandrum');
-- UPDATE `cms`.`tbl_customer` SET `customerName` = 'ram', `mobileNumber` = '10003', `email` = 'ram@test.com', `address` = 'kollam' WHERE (`pk_customerId` = '2');
UPDATE cms.tbl_customer SET customerName = 'laxman', mobileNumber = '1004', email = 'laxman@test.com', address ='palakkad' WHERE (pk_customerId = '2');
-- select * from persons where personID < 105;
UPDATE cms.tbl_customer SET customerName = 'roni' , mobileNumber = '1002' , email = 'roni@test.com' , address = 'ernakulam' WHERE  pk_customerId = 10;

SELECT * FROM tbl_customer WHERE pk_customerId = 2;

SELECT * FROM tbl_customer WHERE pk_customerId NOT IN (25,26,27);

SELECT pk_userId, userName, password FROM  mydatabase.tbl_user;

-- DELETE FROM `cms`.`tbl_customer` WHERE (`pk_customerId` = '3');
DELETE FROM cms.tbl_customer WHERE pk_customerId = 4;

DELETE FROM cms.tbl_customer WHERE pk_customerId IN (22,23,24);

SET SQL_SAFE_UPDATES = 0;



SET SQL_SAFE_UPDATES = 0;
UPDATE CMS.tbl_customer SET mobileNumber = '10023' WHERE customerName = 'hans'; 
SET SQL_SAFE_UPDATES = 1;

# your code SQL here

SET SQL_SAFE_UPDATES = 1;

SELECT * FROM cms.tbl_customer WHERE pk_customerId = 30;
DESC tbl_customer;