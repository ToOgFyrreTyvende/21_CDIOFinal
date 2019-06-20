use cdiofinal;

DELETE FROM Users;
insert into Users (userId, userName, ini, cpr, role)
VALUES (1, 'Lars Larsen', 'LL', '140553-1477', 1),
       (2, 'Michael Jackson', 'MJ', '290858-1235', 3),
       (3, 'Angelina Jolie', 'AJ', '040674-4352', 2);

DELETE FROM Products;
insert into Products (productId, productName)
VALUES (1, 'Panodil'),
       (2, 'Øjendråber'),
       (3, 'Oxadiaziridine');

DELETE FROM RawMats;
insert into RawMats (rawMatID, rawMatName)
VALUES (1, 'Titaniumoxid'),
       (2, 'Hydroxid'),
       (3, 'Nitrogen'),
       (4, 'NaCl'),
       (5, 'H2O');

insert into ProductIngredients (productIngredientId, rawMatId, productId, amount, tolerance)
values (1, 1, 1, 25, 1),
       (2, 2, 1, 42, 2),
       (3, 3, 1, 3, 5),
       (4, 4, 2, 2, 4),
       (5, 5, 2, 100, 10),
       (6, 3, 3, 15, 5),
       (7, 5, 3, 15, 2);

insert into RawMatBatches (rmbId, rawMatId, amount, supplier)
values (1, 1, 250, 'Novo Nordisk'),
       (2, 2, 150, 'Zealand Pharma'),
       (3, 3, 200, 'H. Lundbeck'),
       (4, 4, 1000, 'Borup Kemi'),
       (5, 5, 1500, 'DTU Biosustain');

insert into ProductBatches (prodBatchId, productId, status)
values (1, 1, 2),
       (2, 1, 1),
       (3, 1, 0),
       (4, 2, 1),
       (5, 2, 0);

select RM.rawMatId, RM.rawMatName, amount
from ProductIngredients
         inner join RawMats RM on ProductIngredients.rawMatId = RM.rawMatID
         inner join Products pr on ProductIngredients.productId = pr.productId
where ProductIngredients.productId = 1;


select rawMatBatchId, rmb.rawMatId, wib.userId, rawMatName, tara, netto, supplier from ProductBatches as pb
inner join WeighedIngredientsBatches as wib on wib.prodBatchId = pb.prodBatchId
inner join RawMatBatches as rmb on wib.rawMatBatchId = rmb.rmbId
inner join RawMats Mat on rmb.rawMatId = Mat.rawMatId
where pb.prodBatchId = 1;


SELECT *
FROM Users;
SELECT *
FROM Products;
SELECT *
FROM RawMats;
SELECT *
FROM ProductIngredients;
SELECT *
FROM RawMatBatches;

select * from WeighedIngredientsBatches;