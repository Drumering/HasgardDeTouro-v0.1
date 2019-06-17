create table categoria(
    idCate number(5) not null,
    nomeCate varchar2(50) not null,
    slugCate varchar2(50),
    constraint cate_pk primary key(idCate)
);

create table produto(
    proID number(5) not null,
    idCate number(5) not null,
    proNome varchar2(50) not null,
    proAltura number(6,2),
    proLargura number(6,2),
    proCompr number(6,2),
    proPreco number(15,2),
    proQntd number(10),
    constraint pro_pk primary key(proID),
    constraint pro_cate_fk foreign key(idCate) references categoria(idCate)
);

create table carrinho (
    idCarrinho number(5) not null,
    constraint carrinho_pk primary key (idCarrinho)
);

create table carrinho_itens (
    idCarrinho number(5) not null,
    proID number(5) not null,
    constraint carrinho_itens_fk foreign key (idCarrinho) references carrinho(idCarrinho),
    constraint carrinho_proitem_fk foreign key (proID) references produto(proID)
);

create table usuario(
    idUser number(10) not null,
    usrEmail varchar2(50) not null,
    usrSenha varchar2(20) not null,
    constraint usr_pk primary key(idUser)
);

create table cliente(
    cliCPF varchar2(20) not null,
    idUser number(10) not null,
    cliNome varchar2(100) not null,
    cliEndereco varchar2(100) not null,
    cliNumEndereco number(10) not null,
    cliCompleEnd varchar(100),
    cliCep number(8) not null,
    cliEmail varchar2(50),
    cliCel number(20),
    cliTelFixo number(20),
    constraint cliente_pk primary key(cliCPF),
    constraint usr_cliente_fk foreign key(idUser) references usuario(idUser)
);

create table pedido (
    idPedido number(5) not null,
    idCarrinho number(5) not null,
    cliCPF varchar2(20) not null,
    pedDataHora date,
    pedStatus varchar2(20),
    pedFormPgto varchar2(20),
    pedValorTotal number(8,2) not null,
    constraint pedido_pk primary key (idPedido),
    constraint pedido_carrinho_fk foreign key(idCarrinho) references carrinho(idCarrinho),
    constraint pedido_cliente_fk foreign key(cliCPF) references cliente(cliCPF)
);

create sequence carrinhoSEQ
increment by 1
start with 1;

create sequence pedidoSEQ
increment by 1
start with 1;

create sequence usuarioSEQ
increment by 1
start with 1;

create sequence CateSEQ
increment by 1
start with 1;

create sequence proSEQ
increment by 1
start with 1;

insert into categoria(idCate,nomeCate,slugCate)
values (CateSEQ.nextval,'casal','cholchao-de-casal');

insert into categoria(idCate,nomeCate,slugCate)
values (CateSEQ.nextval,'solteiro','cholchao-de-solteiro');

insert into categoria(idCate,nomeCate,slugCate)
values (CateSEQ.nextval,'premium-casal','colchao-premium-casal');

insert into categoria(idCate,nomeCate,slugCate)
values (CateSEQ.nextval,'premium-solteiro','colchao-premium-solteiro');

insert into produto(proID,idCate,proNome,proAltura,proLargura,proCompr,proPreco,proQntd)
values (proSEQ.nextval,1,'Cholchao Dona Joana',1.1,2.2,3.3,1250.99,150);

insert into produto(proID,idCate,proNome,proAltura,proLargura,proCompr,proPreco,proQntd)
values (proSEQ.nextval,2,'Cholchao Juninho',1111.11,22.2,33.3,700.98,10);

insert into produto(proID,idCate,proNome,proAltura,proLargura,proCompr,proPreco,proQntd)
values (proSEQ.nextval,1,'Cholchao KingFuckingSize',1.1,3.3,4.4,5488.99,300);

insert into produto(proID,idCate,proNome,proAltura,proLargura,proCompr,proPreco,proQntd)
values (proSEQ.nextval,1,'Cholchao LordFuckingSize',1.2,2.2,3.3,3498.55,400);

insert into carrinho(idCarrinho)
values(carrinhoSEQ.nextval);

insert into usuario(idUser,usrEmail,usrSenha)
values(usuarioSEQ.nextval,'email@email.com','senha1234');

insert into cliente(cliCPF,idUser,cliNome,cliEndereco,cliNumEndereco,cliCompleEnd,cliCep,cliEmail,cliCel,cliTelFixo)
values('000.000.000-00',1,'joao','rua curitiba',000,'sobrado',00000000,'email@email.com','41999999999','4133333333');

insert into pedido(idPedido,idCarrinho,cliCPF,pedDataHora,pedStatus,pedFormPgto,pedValorTotal)
values(pedidoSEQ.nextval,1,'000.000.000-00',sysdate,null,'boleto',1000);

select * from pedido;

select * from categoria;
select * from produto;

select proID,proNome,nomeCate from produto
inner join categoria on produto.idCate = categoria.idCate;

commit;
