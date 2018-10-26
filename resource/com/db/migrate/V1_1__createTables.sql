alter table CARD_TABLE drop foreign key FKhdvnvo6aafm98thaomvqif4ut
alter table CART_ENTRIES drop foreign key FKl993agta822hwg0osdq6w3m3u
alter table CART_ENTRIES drop foreign key FKlg871hpgfav02p13whut095oh
alter table CartEntry_cartEntryDetails drop foreign key FK7cs5aqnfxeo8wjv16gcvpiyy9
alter table LOGIN_TABLE drop foreign key FKnj6q8r18i9muvigta16i5dgq0
alter table Order_productDetails drop foreign key FK4v4gexwf2jt9oa4bntpthsdlo
alter table PRODUCT_DETAIL_GROUPING drop foreign key FKkkn0euq8vskq3tl26decuggmj
alter table PRODUCT_DETAIL_GROUPING drop foreign key FKh1trnl26v7tbx2unywlwd0yjr
alter table PRODUCT_DETAILS drop foreign key FKkg3v93s4qdoxtb7hwm29m3oeh
alter table USER_ADDRESSES drop foreign key FK1mni5e0q8jgfih5qmwocylwxx
alter table USER_ADDRESSES drop foreign key FKbqxhv50jb9mscvm47pt1tx41p
alter table USER_CARDS drop foreign key FK8booi1nyc44b4w787uipty608
alter table USER_CARDS drop foreign key FKjy1k58qb4be7y5x8xw2ee6ih1
alter table USER_ORDERS drop foreign key FKaksmvowoab1md8k0dfkata4q2
alter table USER_ORDERS drop foreign key FK7o9tr3dsioi93a41tfmli6hfk
alter table USER_TABLE drop foreign key FKbtvva4p245x3s5rkidew8de0
drop table if exists ADDRESS_TABLE
drop table if exists BOOKS_TABLE
drop table if exists CARD_TABLE
drop table if exists CART_ENTRIES
drop table if exists CART_ENTRY_TABLE
drop table if exists CART_TABLE
drop table if exists CartEntry_cartEntryDetails
drop table if exists CLOTHING_TABLE
drop table if exists ELECTRONICS_TABLE
drop table if exists hibernate_sequence
drop table if exists HOME_TABLE
drop table if exists LOGIN_TABLE
drop table if exists Order_productDetails
drop table if exists ORDER_TABLE
drop table if exists OUTDOORS_TABLE
drop table if exists PRODUCT_DETAIL_GROUP_TABLE
drop table if exists PRODUCT_DETAIL_GROUPING
drop table if exists PRODUCT_DETAIL_TABLE
drop table if exists PRODUCT_DETAILS
drop table if exists PRODUCT_TABLE
drop table if exists PRODUCT_TO_STRING
drop table if exists USER_ADDRESSES
drop table if exists USER_CARDS
drop table if exists USER_ORDERS
drop table if exists USER_TABLE
create table ADDRESS_TABLE (ADDRESS_ID bigint not null, LINE_ONE varchar(150) not null, LINE_TWO varchar(150), ADDRESS_CITY varchar(50) not null, ADDRESS_COUNTRY varchar(50) not null, ADDRESS_STATE varchar(50) not null, ZIP_CODE integer not null, primary key (ADDRESS_ID)) engine=InnoDB
create table BOOKS_TABLE (PRODUCT_ID bigint not null, CATEGORY_NAME varchar(50) not null, PRODUCT_IMG_URL varchar(250) not null, PRODUCT_DESCRIPTION varchar(500), ORDER_FREQUENCY float not null, PRODUCT_NAME varchar(100) not null, PRODUCT_RATING integer not null, STOCK_QUANTITY integer not null, UNIT_PRICE float not null, primary key (PRODUCT_ID)) engine=InnoDB
create table CARD_TABLE (CARD_ID bigint not null, CARD_NAME varchar(100) not null, CARD_NO bigint not null, CARD_TYPE varchar(25) not null, CARD_CVV integer not null, EXP_DATE varchar(10) not null, billingAddress_ADDRESS_ID bigint, primary key (CARD_ID)) engine=InnoDB
create table CART_ENTRIES (CART_ID bigint not null, CART_ENTRY_ID bigint not null) engine=InnoDB
create table CART_ENTRY_TABLE (CART_ENTRY_ID bigint not null, ENTRY_QUANTITY integer not null, product_PRODUCT_ID bigint, primary key (CART_ENTRY_ID)) engine=InnoDB
create table CART_TABLE (CART_ID bigint not null, primary key (CART_ID)) engine=InnoDB
create table CartEntry_cartEntryDetails (CartEntry_CART_ENTRY_ID bigint not null, cartEntryDetails varchar(255)) engine=InnoDB
create table CLOTHING_TABLE (PRODUCT_ID bigint not null, CATEGORY_NAME varchar(50) not null, PRODUCT_IMG_URL varchar(250) not null, PRODUCT_DESCRIPTION varchar(500), ORDER_FREQUENCY float not null, PRODUCT_NAME varchar(100) not null, PRODUCT_RATING integer not null, STOCK_QUANTITY integer not null, UNIT_PRICE float not null, primary key (PRODUCT_ID)) engine=InnoDB
create table ELECTRONICS_TABLE (PRODUCT_ID bigint not null, CATEGORY_NAME varchar(50) not null, PRODUCT_IMG_URL varchar(250) not null, PRODUCT_DESCRIPTION varchar(500), ORDER_FREQUENCY float not null, PRODUCT_NAME varchar(100) not null, PRODUCT_RATING integer not null, STOCK_QUANTITY integer not null, UNIT_PRICE float not null, primary key (PRODUCT_ID)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table HOME_TABLE (PRODUCT_ID bigint not null, CATEGORY_NAME varchar(50) not null, PRODUCT_IMG_URL varchar(250) not null, PRODUCT_DESCRIPTION varchar(500), ORDER_FREQUENCY float not null, PRODUCT_NAME varchar(100) not null, PRODUCT_RATING integer not null, STOCK_QUANTITY integer not null, UNIT_PRICE float not null, primary key (PRODUCT_ID)) engine=InnoDB
create table LOGIN_TABLE (USER_EMAIL varchar(50) not null, USER_ROLE varchar(20), PASSWORD varchar(25) not null, USER_DETAILS bigint, primary key (USER_EMAIL)) engine=InnoDB
create table Order_productDetails (Order_ORDER_ID bigint not null, productDetails varchar(255)) engine=InnoDB
create table ORDER_TABLE (ORDER_ID bigint not null, IS_COMPLETE bit not null, ORDER_DATE date not null, ORDER_QUANTITY integer not null, stringDetails varchar(255), product_PRODUCT_ID bigint, primary key (ORDER_ID)) engine=InnoDB
create table OUTDOORS_TABLE (PRODUCT_ID bigint not null, CATEGORY_NAME varchar(50) not null, PRODUCT_IMG_URL varchar(250) not null, PRODUCT_DESCRIPTION varchar(500), ORDER_FREQUENCY float not null, PRODUCT_NAME varchar(100) not null, PRODUCT_RATING integer not null, STOCK_QUANTITY integer not null, UNIT_PRICE float not null, primary key (PRODUCT_ID)) engine=InnoDB
create table PRODUCT_DETAIL_GROUP_TABLE (PRODUCT_DETAIL_GROUP_ID integer not null, DETAIL_VALUE varchar(20) not null, primary key (PRODUCT_DETAIL_GROUP_ID)) engine=InnoDB
create table PRODUCT_DETAIL_GROUPING (PRODUCT_DETAIL_GROUP_ID integer not null, PRODUCT_DETAIL_ID bigint not null, primary key (PRODUCT_DETAIL_GROUP_ID, PRODUCT_DETAIL_ID)) engine=InnoDB
create table PRODUCT_DETAIL_TABLE (PRODUCT_DETAIL_ID bigint not null, DETAIL_VALUE varchar(50) not null, primary key (PRODUCT_DETAIL_ID)) engine=InnoDB
create table PRODUCT_DETAILS (PRODUCT_ID bigint not null, PRODUCT_DETAIL_GROUP_ID integer not null, primary key (PRODUCT_ID, PRODUCT_DETAIL_GROUP_ID)) engine=InnoDB
create table PRODUCT_TABLE (PRODUCT_ID bigint not null, CATEGORY_NAME varchar(50) not null, PRODUCT_IMG_URL varchar(250) not null, PRODUCT_DESCRIPTION varchar(500), ORDER_FREQUENCY float not null, PRODUCT_NAME varchar(100) not null, PRODUCT_RATING integer not null, STOCK_QUANTITY integer not null, UNIT_PRICE float not null, primary key (PRODUCT_ID)) engine=InnoDB
create table PRODUCT_TO_STRING (descriptionId bigint not null, catName varchar(255), description varchar(2000), product_PRODUCT_ID bigint, primary key (descriptionId)) engine=InnoDB
create table USER_ADDRESSES (USER_ID bigint not null, ADDRESS_ID bigint not null, primary key (USER_ID, ADDRESS_ID)) engine=InnoDB
create table USER_CARDS (USER_ID bigint not null, CARD_ID bigint not null, primary key (USER_ID, CARD_ID)) engine=InnoDB
create table USER_ORDERS (USER_ID bigint not null, ORDER_ID bigint not null, primary key (USER_ID, ORDER_ID)) engine=InnoDB
create table USER_TABLE (USER_ID bigint not null, FIRST_NAME varchar(50) not null, USER_GENDER varchar(20), LAST_NAME varchar(50) not null, MOBILE_NO bigint, cart_CART_ID bigint, primary key (USER_ID)) engine=InnoDB
alter table CART_ENTRIES add constraint UK_nu8v0clq61b22qa9mo7nt7ej5 unique (CART_ENTRY_ID)
alter table PRODUCT_DETAIL_GROUPING add constraint UK_6kq23rm2mgk8ek5scj385y45f unique (PRODUCT_DETAIL_ID)
alter table PRODUCT_DETAILS add constraint UK_66bkwaqmgfnqcappt4jppjum2 unique (PRODUCT_DETAIL_GROUP_ID)
alter table USER_ADDRESSES add constraint UK_fay1p3m0xkpql4rllvik8sytb unique (ADDRESS_ID)
alter table USER_CARDS add constraint UK_2b5wk5p2xutvjl174fjdfvojl unique (CARD_ID)
alter table USER_ORDERS add constraint UK_2f59a5aos75rw7718n2medble unique (ORDER_ID)
alter table CARD_TABLE add constraint FKhdvnvo6aafm98thaomvqif4ut foreign key (billingAddress_ADDRESS_ID) references ADDRESS_TABLE (ADDRESS_ID)
alter table CART_ENTRIES add constraint FKl993agta822hwg0osdq6w3m3u foreign key (CART_ENTRY_ID) references CART_ENTRY_TABLE (CART_ENTRY_ID)
alter table CART_ENTRIES add constraint FKlg871hpgfav02p13whut095oh foreign key (CART_ID) references CART_TABLE (CART_ID)
alter table CartEntry_cartEntryDetails add constraint FK7cs5aqnfxeo8wjv16gcvpiyy9 foreign key (CartEntry_CART_ENTRY_ID) references CART_ENTRY_TABLE (CART_ENTRY_ID)
alter table LOGIN_TABLE add constraint FKnj6q8r18i9muvigta16i5dgq0 foreign key (USER_DETAILS) references USER_TABLE (USER_ID)
alter table Order_productDetails add constraint FK4v4gexwf2jt9oa4bntpthsdlo foreign key (Order_ORDER_ID) references ORDER_TABLE (ORDER_ID)
alter table PRODUCT_DETAIL_GROUPING add constraint FKkkn0euq8vskq3tl26decuggmj foreign key (PRODUCT_DETAIL_ID) references PRODUCT_DETAIL_TABLE (PRODUCT_DETAIL_ID)
alter table PRODUCT_DETAIL_GROUPING add constraint FKh1trnl26v7tbx2unywlwd0yjr foreign key (PRODUCT_DETAIL_GROUP_ID) references PRODUCT_DETAIL_GROUP_TABLE (PRODUCT_DETAIL_GROUP_ID)
alter table PRODUCT_DETAILS add constraint FKkg3v93s4qdoxtb7hwm29m3oeh foreign key (PRODUCT_DETAIL_GROUP_ID) references PRODUCT_DETAIL_GROUP_TABLE (PRODUCT_DETAIL_GROUP_ID)
alter table USER_ADDRESSES add constraint FK1mni5e0q8jgfih5qmwocylwxx foreign key (ADDRESS_ID) references ADDRESS_TABLE (ADDRESS_ID)
alter table USER_ADDRESSES add constraint FKbqxhv50jb9mscvm47pt1tx41p foreign key (USER_ID) references USER_TABLE (USER_ID)
alter table USER_CARDS add constraint FK8booi1nyc44b4w787uipty608 foreign key (CARD_ID) references CARD_TABLE (CARD_ID)
alter table USER_CARDS add constraint FKjy1k58qb4be7y5x8xw2ee6ih1 foreign key (USER_ID) references USER_TABLE (USER_ID)
alter table USER_ORDERS add constraint FKaksmvowoab1md8k0dfkata4q2 foreign key (ORDER_ID) references ORDER_TABLE (ORDER_ID)
alter table USER_ORDERS add constraint FK7o9tr3dsioi93a41tfmli6hfk foreign key (USER_ID) references USER_TABLE (USER_ID)
alter table USER_TABLE add constraint FKbtvva4p245x3s5rkidew8de0 foreign key (cart_CART_ID) references CART_TABLE (CART_ID)