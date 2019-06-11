CREATE TABLE Products (
      productId bigint NOT NULL,
      productName varchar(255),
      rawMatId bigint,
      nomNetto double,
      tolerance double,
PRIMARY KEY(productId),
INDEX Products_productId_INDEX (productId));

CREATE TABLE Users (
      userId bigint NOT NULL,
      userName varchar(255),
      ini varchar(255),
      cpr varchar(255),
      role varchar(255),
PRIMARY KEY(userId),
INDEX Users_userId_INDEX (userId));

CREATE TABLE ProductBatches (
      prodBatchId bigint AUTO_INCREMENT NOT NULL,
      prodId bigint,
      rawMatBatchId bigint,
      status bigint,
PRIMARY KEY(prodBatchId),
INDEX ProductBatches_prodBatchId_INDEX (prodBatchId));

CREATE TABLE RawMats (
    rawMatID bigint NOT NULL,
    rawMatName varchar(255),
PRIMARY KEY(rawMatID),
INDEX RawMats_rawMatID_INDEX (rawMatID));


