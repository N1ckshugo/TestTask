CREATE TABLE USERS
(
    id       bigint,
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (id)

);

CREATE TABLE ROLES
(
    id   bigint,
    name varchar(255) not null,
    primary key (id)
);

CREATE TABLE ORDERS
(
    id      bigint auto_increment,
    name    varchar(255) not null,
    email   varchar(255) not null,
    address varchar(255) not null,
    phone   bigint       not null,
    primary key (id)
);

create table USERS_ROLES
(
    users_id bigint,
    roles_id bigint,
    primary key (users_id, roles_id)
);

alter table if exists USERS_ROLES
    add constraint FKML90KEF4W2JY7OXYQV742TSFC foreign key (roles_id) references ROLES;

alter table if exists USERS_ROLES
    add constraint FKA62J07K5MHGIFPP955H37PONJ foreign key (users_id) references USERS;

