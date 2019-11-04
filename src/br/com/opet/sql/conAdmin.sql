create tablespace ts_integrador
    datafile 'C:/temp/db_integrador/ts_integrador.dbf'
    size 32m autoextend on next 32m maxsize 2048m
    logging
    extent management local;

create temporary tablespace temp_integrador
    tempfile 'C:/temp/db_integrador/temp_integrador.dbf'
    size 32m autoextend on next 32m maxsize 2048m
    extent management local;
    
SELECT * FROM DBA_USERS;

DROP USER ADMIN cascade;
    
create user admin identified by admin
default tablespace ts_integrador
temporary tablespace temp_integrador;

create user teste identified by teste
default tablespace ts_integrador
temporary tablespace temp_integrador;

grant connect,resource to admin with admin option;
grant connect,resource to teste with admin option;

select username,default_tablespace,temporary_tablespace from dba_users;

set serveroutput on

DECLARE
USEREXISTS INTEGER;
BEGIN
  SELECT COUNT(*) INTO USEREXISTS FROM DBA_USERS WHERE USERNAME = 'ADMIN';
  IF (USEREXISTS = 0) THEN
    EXECUTE IMMEDIATE 'create user admin identified by admin default tablespace ts_integrador temporary tablespace temp_integrador';
    EXECUTE IMMEDIATE 'grant connect,resource to admin with admin option';
    DBMS_OUTPUT.put_line('USUARIO CRIADO E PERMISSÕES CONCEDIDAS');
  ELSE
    DBMS_OUTPUT.put_line('USUARIO JA EXISTE');
  END IF;
END;
/

commit;
