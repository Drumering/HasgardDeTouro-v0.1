create table auditoria(
    logData varchar2(100),
    loggedUser VARCHAR2(30),
    updateDate DATE,
    ipAdress VARCHAR2(50)
);

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
    quantidade number(9) not null,
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

create table tipo_cliente(
    idTipo number(3) not null,
    nm_tipo VARCHAR2(30) not null,
    constraint tipo_pk primary key (idTipo)
);

alter table cliente add tipo_cliente number(3);

alter table cliente add constraint tipo_fk foreign key (tipo_cliente) references tipo_cliente(idTipo);

create table cliente_log(
    idLog number(5) not null,
    idCliente varchar2(20),
    idPedido number(5),
    constraint cliLog_pk primary key (idLog),
    constraint cliLog_fk foreign key (idCliente) references cliente(cliCPF),
    constraint pedidoLog_fk foreign key (idPedido) references pedido (idPedido)
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

insert into carrinho_itens values(1, 1, 1);

insert into usuario(idUser,usrEmail,usrSenha)
values(usuarioSEQ.nextval,'email@email.com','senha1234');

insert into cliente(cliCPF,idUser,cliNome,cliEndereco,cliNumEndereco,cliCompleEnd,cliCep,cliEmail,cliCel,cliTelFixo)
values('000.000.000-00',1,'joao','rua curitiba',000,'sobrado',00000000,'email@email.com','41999999999','4133333333');

insert into pedido(idPedido,idCarrinho,cliCPF,pedDataHora,pedStatus,pedFormPgto,pedValorTotal)
values(pedidoSEQ.nextval,1,'000.000.000-00',sysdate,null,'boleto',1000);

-- TRIGGER --
create or replace trigger t_auditoria
after update or delete on cliente
for each row
declare
    vDiff varchar2(100);
    vUser varchar2(100);
    vIP varchar2(100);
begin
    SELECT USER INTO vUser FROM DUAL;
    SELECT SYS_CONTEXT ('USERENV', 'IP_ADDRESS') INTO vIP FROM DUAL;
    IF :old.cliCPF != :new.cliCPF THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliCPF) || ' - To: ' || TO_CHAR(:new.cliCPF);
    ELSIF :old.idUser != :new.idUser THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.idUser) || ' - To: ' || TO_CHAR(:new.idUser);
    ELSIF :old.cliNome != :new.cliNome THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliNome) || ' - To: ' || TO_CHAR(:new.cliNome);
    ELSIF :old.cliEndereco != :new.cliEndereco THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliEndereco) || ' - To: ' || TO_CHAR(:new.cliEndereco);
    ELSIF :old.cliNumEndereco != :new.cliNumEndereco THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliNumEndereco) || ' - To: ' || TO_CHAR(:new.cliNumEndereco);
    ELSIF :old.cliCompleEnd != :new.cliCompleEnd THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliCompleEnd) || ' - To: ' || TO_CHAR(:new.cliCompleEnd);
    ELSIF :old.cliCep != :new.cliCep THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliCep) || ' - To: ' || TO_CHAR(:new.cliCep);
    ELSIF :old.cliEmail != :new.cliEmail THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliEmail) || ' - To: ' || TO_CHAR(:new.cliEmail);
    ELSIF :old.cliCel != :new.cliCel THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliCel) || ' - To: ' || TO_CHAR(:new.cliCel);
    ELSIF :old.cliTelFixo != :new.cliTelFixo THEN
        vDiff := 'Old Data: ' || TO_CHAR(:old.cliTelFixo) || ' - To: ' || TO_CHAR(:new.cliTelFixo);
    END IF;
    
    INSERT INTO auditoria (logData, loggedUser, updateDate, ipAdress)
    VALUES (vDiff, vUser, SYSDATE, vIP);
END;
/

UPDATE cliente set cliNome = 'Teste Trigger Auditoria' where cliCPF = '000.000.000-00';
select * from cliente;
select * from auditoria;

SELECT SYS_CONTEXT ('USERENV', 'IP_ADDRESS') FROM DUAL;
SELECT USER FROM DUAL;

grant update on cliente to teste;

-- PROCEDURE --
create table cliente(
    cliCPF varchar2(20) not null,
    idUser number(10) not null,
    cliNome varchar2(100) not null,
    cliEndereco varchar2(100) not null,
    cliNumEndereco number(10) not null,
    cliCompleEnd varchar2(100),
    cliCep number(8) not null,
    cliEmail varchar2(50),
    cliCel number(20),
    cliTelFixo number(20),
    constraint cliente_pk primary key(cliCPF),
    constraint usr_cliente_fk foreign key(idUser) references usuario(idUser)
);

CREATE OR REPLACE PROCEDURE cliInsert(cliCPF varchar2,idUser number,cliNome varchar2,cliEndereco varchar2,cliNumEndereco number,cliCompleEnd varchar2,cliCep varchar2,cliEmail varchar2,cliCel number,cliTelFixo number)
IS
BEGIN
    INSERT INTO cliente(cliCPF, idUser, cliNome, cliEndereco, cliNumEndereco, cliCompleEnd, cliCep, cliEmail, cliCel, cliTelFixo)
    VALUES (cliCPF, idUser, cliNome, cliEndereco, cliNumEndereco, cliCompleEnd, cliCep, cliEmail, cliCel, cliTelFixo);
END;
/

-- INSERT USER FOR USE IN PROCEDURE -- 
insert into usuario(idUser,usrEmail,usrSenha)
values(usuarioSEQ.nextval,'email@email.com','senha1234');

exec cliInsert('000.000.000-01',2,'Teste Procedure','rua curitiba',000,'sobrado',00000000,'email@email.com','41999999999','4133333333');

select * from usuario;
select * from cliente;

commit;