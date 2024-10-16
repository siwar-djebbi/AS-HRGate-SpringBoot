
--TP5 
--Exercice1

--1
/*
show parameter audit_trail
Alter system set audit_trail=db,extended
scope=spfile;
--shutdown 
--startup
--pour qu'il la prend en consederation le changement 
*/

--2
/*
DROP USER TD4;
CREATE USER TD4 identified by td4 
default tablespace users
quota 2M on users;
GRANT create session,
create table, create procedure TO TD4;
*/

--3
-- LDD
--audit all by td4 by access;
--ou 
--audit table by td4 by access;
-- LMD
/*
audit insert table, update table, delete table
by td4 by access;
*/

--4 
--connect td4/td4
/*
create table test ( a number , b number);
insert into test values (1, 2);
insert into test values (3, 4);
update test set a = 7 where b=2;
select * from test;
delete from test;
*/

--5
/*
select username, timestamp,
 action_name, sql_text
from dba_audit_object
where obj_name='TEST';
*/

--6
/*
select count(*) from dba_audit_session
where username='TD4';
*/

--Exercice2
--1
/*
CREATE USER TD41 identified by TD41
default tablespace users
quota 1M on users;
GRANT DBA to TD41;
*/

--2 
--AUDIT create session by TD41;
/*
select * from dba_priv_audit_opts 
where user_name='TD41';
*/

--3
--connect td41/TD41
/*
create table table1(a number);
create table table2(b number);
*/
--4
/*
AUDIT insert, update, delete 
ON td41.table1 by session;

AUDIT SELECT on td41.table2 
by access;
*/

--5
/*
select object_name,
INS, UPD, DEL, SEL 
from dba_obj_audit_opts;
*/
--6
/*
select a from table1;
select bb from table1;
select b from table2;
insert into table1 values (1);
select b from table2;
select a from table1; 

select action_name , obj_name, sql_text
from dba_audit_object
where username='TD41';
--dba_audit_trail
-- Visualiser les requêtes faites par TD41 dans la vue dba_audit_trail
SELECT *
FROM dba_audit_trail
WHERE username = 'TD41';

*/

--7
/*
audit alter on default;
--conn td41/TD41
create table Table3(c number);
alter table table3 
add (d number);
--pour vérifier on exécute :
select action_name , obj_name, 
sql_text
from dba_audit_object
where username='TD41';
*/

--8
/*
NOAUDIT select, delete on
td41.table1;
select object_name,
INS, UPD, DEL, SEL 
from dba_obj_audit_opts;
*/
























