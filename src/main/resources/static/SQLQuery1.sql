create database PharmExpress

use  PharmExpress


create table cliente(
	cod_cliente int IDENTITY(1,1) primary key,
	nome varchar(50) not null,
	usuario varchar(30) not null,
	cpf varchar(14) unique not null,
	email varchar(30) not null,
	telefone varchar(20) not null,
	senha varchar(30) not null,
	data_nasc varchar(30) not null
);

select * from cliente

create table cliente_telefone(
	cod_telefone int primary key not null,
	telefone int not null,
	ddd int not null
);

create table produto(
	cod_produto int IDENTITY(1,1) primary key,
	nome varchar(50) not null,
	tipo varchar(20) not null,
	descricao varchar(100) not null,
	preco float not null,
	cod_status_produto bit not null,
	quantidade integer not null,
	codigobarra varchar(30) not null
);

select * from produto


create table compra(
	cod_compra int IDENTITY(1,1) primary key,
);

create table formaDePagamento(
	cod_formaPagemento int IDENTITY(1,1) primary key,
	tipo varchar(20) not null,
);

create table pedido(
	cod_pedido int IDENTITY(1,1) primary key,
	quantidade int not null,
	status varchar(20) not null
);

create table carrinho(
	cod_carrinho int IDENTITY(1,1) primary key,
	quantidade int not null
);

create table compra_endereco(
	cod_endereco int IDENTITY(1,1) primary key,
	rua varchar(30) not null,
	numero int not null,
	bairro varchar(20) not null,
	cidade varchar(20) not null,
	cep int not null
);

create table pagamento(
	cod_pagamento int IDENTITY(1,1) primary key,
	fkcod_formaPagemento int not null,
	valor decimal(6,2) not null
);

create table perfil(
	cod_perfil int primary key not null,
	descricao varchar(30) not null
);



alter table cliente
	add fk_cod_perfil integer,
	constraint fk_cliente_perfil foreign key (fk_cod_perfil) references perfil (cod_perfil);
	

alter table cliente_telefone
	add fk_cod_cliente integer,
	constraint fk_cliente_telefone foreign key (fk_cod_cliente) references cliente (cod_cliente);

alter table compra
	add fk_cod_formaPagamento integer,
	fk_cod_carrinho integer,
	fk_cod_cliente integer,
	constraint fk_cliente_compra foreign key (fk_cod_cliente) references cliente (cod_cliente),
	constraint fk_formaPagemento_compra foreign key (fk_cod_formaPagamento) references formaDePagamento (cod_formaPagemento),
	constraint fk_carrinho_compra foreign key (fk_cod_carrinho) references carrinho (cod_carrinho)


alter table pedido
	add fk_cod_compra integer,
	constraint fk_compra_pedido foreign key (fk_cod_compra) references compra (cod_compra)

alter table carrinho
	add fk_cod_produto integer,
	constraint fk_produto_carrinho foreign key (fk_cod_produto) references produto (cod_produto);

alter table compra_endereco
	add fk_cod_compra integer,
	constraint fk_compra_endereco foreign key (fk_cod_compra) references compra (cod_compra);

alter table pagamento
	add fk_cod_formaPagemento integer,
	constraint fkcod_formaPagemento foreign key (fk_cod_formaPagemento) references formaDePagamento (cod_formaPagemento);


Insert into perfil values (1, 'Acesso de Administração')
Insert into perfil values (2, 'Acesso do cliente')						 

select * from perfil