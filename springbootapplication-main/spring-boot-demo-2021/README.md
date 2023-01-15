Instructions for our WEB-APP 
---------------------

* Introduction
* URLS
* Operations
* Installation

The Home Menu  displays the entire  menu tree
(and most local tasks) in a drop-down menu, providing  one- or
two-click access to most pages.  Other modules may also add menu links to the
menu.

* Visit the project page(Home page/Login-Page):
  http://localhost:8080/api
* Login page:
  http://localhost:8080/login
* Register Page:
  http://localhost:8080/register
* Admin Page(fixed data:username:root,password:pass123):
  http://localhost:8080/admin
* Seller Page(fixed data:username:lefteris,password:pass123):
  http://localhost:8080/seller
* Buyer Page(fixed data:username:marianthi,password:pass123):
  http://localhost:8080/buyer

Admin:
* Add User (Buyer | Seller | Admin)
* Delete User (Buyer | Seller | Admin)
* Alter User (Buyer | Seller | Admin)

Seller user:
* Create a car transaction application form.
* Show the user's applications and their status.
* Delete user's account.
* Logout

Buyer user:
* Check user's available applications (access | deny)
* Logout

Installation git clone: 

---
Database Instructions:
* Create a database and select it.
* Create the tables and then insert records in them.


    CREATE DATABASE spring_boot_appl;
    USE spring_boot_appl;

    CREATE TABLE IF NOT EXISTS `user` (
    id int not null auto_increment,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    username varchar(255),
    password varchar(500),
    authority varchar(255),
    afm float,
    enabled int,
    primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    CREATE TABLE IF NOT EXISTS `application` (
    aid int not null auto_increment,
    applicationstatus int,
    buyer_afm_number float,
    trans_dept_address varchar(255),
    vehicle_id int,
    license_plt_numb varchar(255),
    car_maker varchar(255),
    car_mk_yr int,
    user_id int,
    primary key (aid),
    foreign key (user_id) references user (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    insert into user (first_name, last_name, email, username, password, authority, afm, enabled)
    values( 'Nick', 'Papafilipou', 'nickpap@gmail.com',
    'admin', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC',
    'ROLE_ADMIN', 173017299, 1);
    
    insert into user (first_name, last_name, email, username, password, authority, afm, enabled)
    values( 'Lefteris', 'Dimitriou', 'leftdim@gmail.com',
    'lefteris', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC',
    'ROLE_SELLER', 810428693, 1);
    
    
    insert into user (first_name, last_name, email, username, password, authority, afm, enabled)
    values( 'Marianthi', 'Ioanidi', 'iodmar@gmail.com',
    'marianthi', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC',
    'ROLE_BUYER', 920275970, 1);
    
    insert into application(applicationstatus, buyer_afm_number, trans_dept_address, vehicle_id, license_plt_numb, car_maker, car_mk_yr, user_id)
    values (0, 920275970, 'Korinthoy', 2510, 'KOR-1234', 'volvo', 2016, 2);

    



---------------------------------------
