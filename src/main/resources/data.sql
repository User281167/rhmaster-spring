create TABLE if not exists users (
    id binary(16) default uuid() not null primary key,
    username varchar(255) not null,
    nombres varchar(255) not null,
    apellidos varchar(255),
    email varchar(255) not null,
    password varchar(255) not null,
    habilitado boolean default true not null
);

create table if not exists roles (
    id binary(16) default uuid() not null primary key,
    nombre enum('ROLE_CANDIDATO', 'ROLE_EVALUADO', 'ROLE_CONTRATADO', 'ROLE_RETIRADO', 'ROLE_PSICO', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN') not null default 'ROLE_CANDIDATO'
);

create table if not exists user_roles (
    user_id binary(16) not null,
    role_id binary(16) not null,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (role_id) references roles(id) on delete cascade
);