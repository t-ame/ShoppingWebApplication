create table productDetailGroup
(
groupId int not null auto_increment, 
groupName varchar,
PRIMARY KEY (groupId)
);

create table productDetail
(
detailId int not null auto_increment,
productId int,
groupId int,
detailValue varchar,
priceIncrement float,
PRIMARY KEY (detailId),
FOREIGN KEY (productId) REFERENCES product(productId) 
ON DELETE CASCADE ON UPDATE NO ACTION,
FOREIGN KEY (groupId) REFERENCES productDetailGroup(groupId) 
ON DELETE CASCADE ON UPDATE NO ACTION
);

create table product
(
productId int not null auto_increment,
categoryId int,
imageUrl varchar,
quantity int,
productName varchar,
productRating int,
productFrequency float,
productDescription varchar,
basePrice float,
PRIMARY KEY (productId),
FOREIGN KEY (categoryId) REFERENCES productCategory(categoryId) 
ON DELETE CASCADE ON UPDATE NO ACTION
);

create table productCategory
(
categoryId int not null auto_increment,
categoryName varchar,
PRIMARY KEY (categoryId)
);

create table userLogin
(
userId int not null auto_increment,
userName varchar,
password varchar,
firstName varchar,
lastName varchar,
userRole varchar,
PRIMARY KEY (userId)
);

create table addresses
(
addressId int not null auto_increment,
userId int,
addressLine1 varchar,
addressLine2 varchar,
city varchar,
state varchar,
country varchar,
zipcode int,
PRIMARY KEY (AddressId),
FOREIGN KEY (userId) REFERENCES userLogin(userId) 
ON DELETE CASCADE ON UPDATE NO ACTION
);

create table cards
(
cardId int not null auto_increment,
userId int,
cardType varchar,
cardName varchar,
cardNumber int,
expiryDate date,
cvv int,
PRIMARY KEY (cardId),
FOREIGN KEY (userId) REFERENCES userLogin(userId) 
ON DELETE CASCADE ON UPDATE NO ACTION
);

create table orders
(
orderId int not null auto_increment,
userId int,
orderDate date,
productId int,
quantity int,
cardId int,
addressId int,
price float,
PRIMARY KEY (orderId),
FOREIGN KEY (userId) REFERENCES userLogin(userId) 
ON DELETE CASCADE ON UPDATE NO ACTION,
FOREIGN KEY (productId) REFERENCES product(productId) 
ON DELETE CASCADE ON UPDATE NO ACTION,
FOREIGN KEY (cardId) REFERENCES cards(cardId) 
ON DELETE CASCADE ON UPDATE NO ACTION,
FOREIGN KEY (addressId) REFERENCES addresses(addressId) 
ON DELETE CASCADE ON UPDATE NO ACTION
);


create table orderDetails
(
orderId int,
productdetailId int,
FOREIGN KEY (orderId) REFERENCES orders(orderId) 
ON DELETE CASCADE ON UPDATE NO ACTION,
FOREIGN KEY (productdetailId) REFERENCES productDetail(detailId) 
ON DELETE CASCADE ON UPDATE NO ACTION
);

create table cartEntry
(
cartId int not null auto_increment,
userId int,
productId int,
quantity int,
price float,
PRIMARY KEY (cartId),
FOREIGN KEY (userId) REFERENCES userLogin(userId) 
ON DELETE CASCADE ON UPDATE NO ACTION,
FOREIGN KEY (productId) REFERENCES product(productId) 
ON DELETE CASCADE ON UPDATE NO ACTION
);


create table cartEntryDetails
(
cartId int,
productdetailId int,
FOREIGN KEY (cartId) REFERENCES cartEntry(cartId) 
ON DELETE CASCADE ON UPDATE NO ACTION,
FOREIGN KEY (productdetailId) REFERENCES productDetail(detailId) 
ON DELETE CASCADE ON UPDATE NO ACTION
);











