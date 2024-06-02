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

create table if not exists recordatorios (
    id binary(16) default uuid() not null primary key,
    user_id binary(16) not null,
    tipo enum('entrevista', 'nomina', 'liquidacion', 'prueba', 'examen', 'induccion', 'otro') not null,
    titulo varchar(255) not null,
    descripcion varchar(255),
    fecha datetime(6) not null,
    foreign key (user_id) references users(id) on delete cascade
);

create table if not exists ofertas (
    id binary(16) default uuid() not null primary key,
    titulo varchar(255),
    cargo varchar(255),
    experiencia varchar(255),
    lugar varchar(255),
    salario int(11),
    responsabilidades varchar(255),
    prestaciones varchar(255),
    tipo varchar(255),
    descripcion varchar(255),
    requisitos varchar(255),
    fecha_publicacion datetime(6),
    visible boolean
);