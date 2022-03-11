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

CREATE TABLE USERS_ROLES
(
    users_id bigint,
    roles_id bigint,
    primary key (users_id, roles_id)
);

alter table if exists USERS_ROLES
    add constraint FKRoles foreign key (roles_id) references ROLES;

alter table if exists USERS_ROLES
    add constraint FKUsers foreign key (users_id) references USERS;

