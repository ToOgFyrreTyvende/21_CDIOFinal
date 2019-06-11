DROP DATABASE IF EXISTS cdiofinal;
CREATE DATABASE cdiofinal;

USE cdiofinal;

DROP TABLE IF EXISTS Products;
CREATE TABLE Products (
productId int NOT NULL,
productName varchar(255),
rawMatId int,
nomNetto double,
tolerance double,
PRIMARY KEY(productId),
INDEX Products_productId_INDEX (productId));

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
userId int NOT NULL,
userName VARCHAR(20) NOT NULL COMMENT 'User navn' CHECK ( 2 <= CHAR_LENGTH(userName) <= 20 ),
ini      VARCHAR(4)  NOT NULL COMMENT 'User initialer' CHECK ( 2 <= CHAR_LENGTH(ini) <= 4 ),
cpr      VARCHAR(11) NOT NULL COMMENT 'User cpr nr' CHECK ( 10 <= CHAR_LENGTH(cpr) <= 11 ),
role     VARCHAR(16) NOT NULL COMMENT 'User role' CHECK ( 5 <= CHAR_LENGTH(role) <= 20 ),
PRIMARY KEY(userId),
INDEX Users_userId_INDEX (userId));

DROP TABLE IF EXISTS ProductBatches;
CREATE TABLE ProductBatches (
prodBatchId int AUTO_INCREMENT NOT NULL,
prodId int,
rawMatBatchId int,
status int,
PRIMARY KEY(prodBatchId),
FOREIGN KEY (prodId) REFERENCES Products (productId) ON DELETE CASCADE,
INDEX ProductBatches_prodBatchId_INDEX (prodBatchId));

DROP TABLE IF EXISTS RawMats;
CREATE TABLE RawMats (
rawMatID int NOT NULL,
rawMatName varchar(255) NOT NULL COMMENT 'Råvare navn' CHECK ( 2 <= CHAR_LENGTH(rawMatName) <= 20 ),
PRIMARY KEY(rawMatID),
INDEX RawMats_rawMatID_INDEX (rawMatID));


DROP TABLE IF EXISTS RawMatBatches;
CREATE TABLE RawMatBatches (
rmbId int NOT NULL,
rawMatId INT NOT NULL COMMENT 'Råvare id',
amount double,
supplier varchar(255),
PRIMARY KEY(rmbId),
FOREIGN KEY (rawMatId) REFERENCES RawMats (rawMatId) ON DELETE CASCADE,
INDEX RawMatBatches_rmbId_INDEX (rmbId));

DROP TABLE IF EXISTS WeighedBatches;
CREATE TABLE WeighedBatches (
weighedBatchId int NOT NULL,
rawMatBatchId int NOT NULL,
productBatchId int NOT NULL,
userId int,
tara double,
netto double,
PRIMARY KEY(weighedBatchId),
FOREIGN KEY (rawMatBatchId) REFERENCES RawMatBatches (rmbId) ON DELETE CASCADE,
FOREIGN KEY (productBatchId) REFERENCES ProductBatches (prodBatchId) ON DELETE CASCADE,
FOREIGN KEY (userId) REFERENCES Users (userId) ON DELETE CASCADE,
INDEX WeighedBatches_weighedBatchId_INDEX (weighedBatchId));


DROP TABLE IF EXISTS ProductIngredients;
CREATE TABLE ProductIngredients (
ingredientId int NOT NULL,
rawMatId int,
productId int,
PRIMARY KEY(ingredientId),
FOREIGN KEY (rawMatId) REFERENCES RawMats (rawMatId) ON DELETE CASCADE,
FOREIGN KEY (productId) REFERENCES Products (productId) ON DELETE CASCADE,
INDEX ProductIngredients_ingredientId_INDEX (ingredientId));
