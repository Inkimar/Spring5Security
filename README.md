https://github.com/PacktPublishing/Hands-On-Spring-Security-5-for-Reactive-Applications/tree/master/Chapter02/jetty-db-basic-authentication/src/main/java/com/packtpub/book/ch02/springsecurity/config


database = spring5

***

create table users(
username varchar(75) not null primary key,
password varchar(150) not null,
enabled boolean not null
);

create table authorities (
username varchar(75) not null,
authority varchar(50) not null,
constraint fk_authorities_users foreign key(username) references users(username)
);

***
insert into users(username, password, enabled)
values('admin', '$2a$04$lcVPCpEk5DOCCAxOMleFcOJvIiYURH01P9rx1Y/pl.wJpkNTfWO6u',
true);

insert into authorities(username, authority)
values('admin','ROLE_ADMIN');

insert into users(username, password, enabled)
values('user', '$2a$04$nbz5hF5uzq3qsjzY8ZLpnueDAvwj4x0U9SVtLPDROk4vpmuHdvG3a',
true);

insert into authorities(username,authority)
values('user','ROLE_USER');

***

The password is one-way hashed using online tool http://www.devglan.com/online-tools/
bcrypt-hash-generator . To compare the password we will use PasswordEncoder ( Bcrypt ).

***
Credentials are as follows:
User = admin and password = admin@password
User = user and password = user@password
It's important to note that, even though the role is named ROLE_ADMIN , the actual name is ADMIN ,
and this is what our code will be using while passing.

### testing
1. https://www.devglan.com/online-tools/bcrypt-hash-generator (4 rounds) <p> 
2. admin@password = $2a$04$tbS4KGe2VuX5mzvSxKMn4O64dpcu2crGk1n4WOauTqJJdkm.qCWIu
3. insert into users(username, password, enabled) values('admin1', '$2a$04$tbS4KGe2VuX5mzvSxKMn4O64dpcu2crGk1n4WOauTqJJdkm.qCWIu', true);

***
#mvn commands

1. mvn clean install
2. mvn jetty:run 
3. open -> http://localhost:8080 

kan inte logga in -> skapar en issue 2020-07-07
https://github.com/PacktPublishing/Hands-On-Spring-Security-5-for-Reactive-Applications/issues 


***

# Spring Security

## Step 1—Spring Security configuration setup 

1. config-paketet: SpringSecurityConfig 

We then set up the authentication mechanism to retrieve the user's credentials.
Here we are using basic authentication as the mechanism to capture user
credentials. Please note that the role names being used to check doesn't have the
prefix ROLE_ .

## Step 2—Spring Security setup for a web application

- here avoiding setting through the xml-file

We know that we have to instruct the application to start using Spring Security.
One easy way is to declare the Spring Security filter in web.xml . If you want to
avoid using XML and perform the actions using Java instead, then create a class
that extends AbstractSecurityWebApplicationInitializer ; 