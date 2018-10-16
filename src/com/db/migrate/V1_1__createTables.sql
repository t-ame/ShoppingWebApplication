create table productDetailGroup
(
groupId int, 
groupName varchar
);

create table productDetail
(
detailId int,
productId int,
groupId int,
detailValue varchar,
priceIncrement float
);

create table product
(
productId int,
categoryId int,
imageUrl varchar,
quantity int,
productName varchar,
productRating int,
productFrequency float,
productDescription varchar,
basePrice float
);

create table productCategory
(
categoryId int,
categoryName varchar
);

create table userLogin
(
userId int,
userName varchar,
password varchar,
firstName varchar,
lastName varchar,
userRole varchar
);

create table addresses
(
AddressId int,
userId int,
addressLine1 varchar,
addressLine2 varchar,
city varchar,
state varchar,
country varchar,
zipcode int
);

create table cards
(
cardId int,
userId int,
cardType varchar,
cardName varchar,
cardNumber int,
expiryDate date,
cvv int
);

create table orders
(
orderId int,
userId int,
orderDate date,
productId int,
quantity int,
cardId int,
addressId int,
productDetailId int,
price float
);


create table orderDetails
(
orderId int,
productdetailId int
);

create table cartEntry
(
cartId int,
userId int,
productId int,
quantity int,
price float
);


create table cartEntryDetails
(
cartId int,
productdetailId int
);











