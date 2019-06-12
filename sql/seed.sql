use cdiofinal;

DELETE FROM Users;
insert into Users (userId, userName, ini, cpr, role)
VALUES (11, 'carl', 'ee', '123123', 1),
       (12, 'bob', 'bb', '123123', 3),
       (13, 'Karsten', 'kk', '699696', 2);

DELETE FROM Products;
insert into Products (productId, productName, nomNetto, tolerance)
VALUES (1, 'Kebab', 5, 5);

DELETE FROM RawMats;
insert into RawMats (rawMatID, rawMatName)
VALUES (1, 'Rottekød'),
       (2, 'Eddike'),
       (3, 'Salt');

insert into ProductIngredients (productIngredientId, rawMatId, productId, amount)
values (1, 1, 1, 25),
       (2, 2, 1, 42),
       (3, 3, 1, 3);


select RM.rawMatId, RM.rawMatName, amount
from ProductIngredients
         inner join RawMats RM on ProductIngredients.rawMatId = RM.rawMatID
         inner join Products pr on ProductIngredients.productId = pr.productId
where ProductIngredients.productId = 1;


select rawMatBatchId, rmb.rawMatId, wib.userId, rawMatName, tara, netto, supplier from ProductBatches as pb
inner join WeighedIngredientsBatches as wib on wib.prodBatchId = pb.prodBatchId
inner join RawMatBatches as rmb on wib.rawMatBatchId = rmb.rmbId
inner join RawMats Mat on rmb.rawMatId = Mat.rawMatId
where pb.prodBatchId = 1


SELECT *
FROM Users;
SELECT *
FROM Products;
SELECT *
FROM RawMats;
SELECT *
FROM ProductIngredients;