-- ------------------------- --
-- CDIO Final database stuff --
-- ------------------------- --

DROP DATABASE IF EXISTS cdiofinal;
CREATE DATABASE cdiofinal;

USE cdiofinal;

/* Table: "Users" ("Laborant i opg. besk.)*/
DROP TABLE IF EXISTS Users;
CREATE TABLE Users
(
    userId   INT AUTO_INCREMENT COMMENT 'User id',
    userName VARCHAR(20) NOT NULL COMMENT 'User navn' CHECK ( 2 <= CHAR_LENGTH(userName) <= 20 ),
    ini      VARCHAR(4)  NOT NULL COMMENT 'User initialer' CHECK ( 2 <= CHAR_LENGTH(ini) <= 4 ),
    cpr      VARCHAR(11) NOT NULL COMMENT 'User cpr nr' CHECK ( 10 <= CHAR_LENGTH(cpr) <= 11 ),
    role     INT         NOT NULL COMMENT 'User role, 0=admin, 1=laborant, 2=produktionsleder, 3=farmaceut' CHECK ( 0 <= role <= 3 ),
    PRIMARY KEY (userId)
);

/* Table: "RawMat"*/
DROP TABLE IF EXISTS RawMats;
CREATE TABLE RawMats
(
    rawMatId   INT AUTO_INCREMENT COMMENT 'Råvare id',
    rawMatName VARCHAR(255) NOT NULL COMMENT 'Råvare navn' CHECK ( 2 <= CHAR_LENGTH(rawMatName) <= 255 ),
    PRIMARY KEY (rawMatId)
);

/* Table: "RawMatBatch"*/
DROP TABLE IF EXISTS RawMatBatches;
CREATE TABLE RawMatBatches
(
    rmbId    INT AUTO_INCREMENT COMMENT 'Råvare batch id',
    rawMatId INT          NOT NULL COMMENT 'Råvare id',
    amount   DOUBLE COMMENT 'Lagerbeholdning i kilogram, med 4 decimaler',
    supplier VARCHAR(255) NOT NULL COMMENT 'Leverandør af råvare' CHECK ( 2 <= CHAR_LENGTH(supplier) <= 255 ),
    PRIMARY KEY (rmbId),
    FOREIGN KEY (rawMatId) REFERENCES RawMats (rawMatId) ON DELETE CASCADE
);

/* Table: "Products" ("Recept" i opg beskrivelsen)*/
DROP TABLE IF EXISTS Products;
CREATE TABLE Products
(
    productId   INT AUTO_INCREMENT COMMENT 'Product Id',
    productName VARCHAR(20) NOT NULL COMMENT 'Product navn' CHECK ( 2 <= CHAR_LENGTH(productName) <= 20 ),
    PRIMARY KEY (productId)
);

/* Table: "ProductIngredients"*/
DROP TABLE IF EXISTS ProductIngredients;
CREATE TABLE ProductIngredients
(
    productIngredientId INT AUTO_INCREMENT COMMENT 'Ingredientens ID',
    rawMatId            INT    NOT NULL COMMENT 'Råvare batch id',
    productId           INT    NOT NULL COMMENT 'Product Id',
    amount              DOUBLE NOT NULL COMMENT 'mængde',
    tolerance           DOUBLE NOT NULL COMMENT 'tolerance',
    PRIMARY KEY (productIngredientId),
    FOREIGN KEY (rawMatId) REFERENCES RawMats (rawMatId) ON DELETE CASCADE,
    FOREIGN KEY (productId) REFERENCES Products (productId) ON DELETE CASCADE
);

/* Table: "ProductBatch" */
DROP TABLE IF EXISTS ProductBatches;
CREATE TABLE ProductBatches
(
    prodBatchId INT AUTO_INCREMENT COMMENT 'Produkt Batch id',
    productId   INT NOT NULL COMMENT 'Product id',
    status      INT NOT NULL Default 0 COMMENT 'Ikke påbegyndt = 0 / Under produktion = 1 / Afsluttet = 2' CHECK ( 0 <= status <= 2 ),
    PRIMARY KEY (prodBatchId),
    FOREIGN KEY (productId) REFERENCES Products (productId) ON DELETE CASCADE
);

/* Table: "WeighedIngredientBatches"*/
DROP TABLE IF EXISTS
;
CREATE TABLE WeighedIngredientsBatches
(
    weighedIngredientId INT AUTO_INCREMENT COMMENT 'Ingredientens ID',
    rawMatBatchId       INT    NOT NULL COMMENT 'Råvare batch id',
    prodBatchId         INT    NOT NULL COMMENT 'Product batch Id',
    userId              INT    NOT NULL COMMENT 'User id',
    tara                DOUBLE NOT NULL COMMENT 'Tara i kg med 4 decimaler',
    netto               DOUBLE NOT NULL COMMENT 'Netto i kg med 4 decimaler',
    PRIMARY KEY (weighedIngredientId),
    FOREIGN KEY (rawMatBatchId) REFERENCES RawMatBatches (rmbId) ON DELETE CASCADE,
    FOREIGN KEY (prodBatchId) REFERENCES ProductBatches (prodBatchId) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES Users (userId) ON DELETE CASCADE
);
