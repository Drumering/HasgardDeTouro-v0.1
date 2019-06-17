create tablespace ts_integrador
    datafile 'C:/temp/db_integrador/ts_integrador.dbf'
    size 32m autoextend on next 32m maxsize 2048m
    logging
    extent management local;

create temporary tablespace temp_integrador
    tempfile 'C:/temp/db_integrador/temp_integrador.dbf'
    size 32m autoextend on next 32m maxsize 2048m
    extent management local;

create user admin identified by admin
default tablespace ts_integrador
temporary tablespace temp_integrador;

grant connect,resource to admin with admin option;

select username,default_tablespace,temporary_tablespace from dba_users;