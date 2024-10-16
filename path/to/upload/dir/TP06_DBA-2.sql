--TP6

--Partie1
--1
/*
DROP user user1 cascade;
CREATE USER user1 identified by pwd1 
default tablespace users
quota 1M on users;

DROP user user2 cascade;
CREATE USER user2 identified by pwd2 
default tablespace users
quota 1M on users;
*/

--2
/*
GRANT create session, create table, 
exp_full_database, imp_full_database
TO user1, user2;
*/

--3
--conn user1/pwd1
--create table test1(a1 number, b1 number);
--create table test2(a2 number, b2 number);

--4
/*
insert into test1 values (1, 2);
insert into test1 values (3, 4);
insert into test1 values (5, 6);
insert into test2 values (11, 22);
insert into test2 values (33, 44);
insert into test2 values (55, 66);
commit;
*/

--5
/*
CREATE OR REPLACE DIRECTORY Oracle As 
'C:\oraclexe\app\oracle';
*/

--6
/*
GRANT read, write ON directory Oracle TO user1, user2;
*/

--7
--EXPDP user1/pwd1 directory=oracle dumpfile=user1.dump

--import vers user1 :
--IMPDP user1/pwd1 directory=oracle dumpfile=user1.dump tables=test1

--8
--import vers user2
/*
IMPDP user2/pwd2 content=metadata_only directory=oracle dumpfile=user1.dump tables=user1.test1 remap_schema=user1:user2
*/

--9
-- la table test1
-- aucune ligne

--10
/*
IMPDP user2/pwd2 directory=oracle dumpfile=user1.dump tables=user1.test2 remap_schema=user1:user2
*/

--11
--les tables test1 et test2
--3 lignes

--Partie2

--1
/*
drop table artiste;
CREATE TABLE ARTISTE (
identifiant number
primary key,
nom varchar(30),
prenom varchar(30),
pays varchar(30)
);
*/

--2
--contenu du fichier artiste.ctl :
/*
load data infile 'artiste.txt'
badfile 'artisteB.bad'
discardfile 'artisteD.dsc'
Insert into table artiste
when (1:1) ="0"
fields terminated by ','
(
identifiant,
nom,
prenom,
pays
) 
*/

--Lancement de la commande sqlldr :
--sqlldr HR/HR control=artiste.ctl
--mon propro chemin
C:\Users\User\Desktop\DBA\TP\TP6>sqlldr HR/HR control=artiste.ctl

--3
--creation de la table et la séquence :
/*
CREATE TABLE modified_data
(
rec_no integer primary key,
region integer,
load_date date,
data1 number,
data2 varchar(20),
data3 date
);
*/
/*
CREATE SEQUENCE seq_rec 
start with 1
increment by 1;
*/

--contenu du fichier emp.ctl :
/*
load data infile
'emp.dat'
badfile 'empB.bad'
discardfile 'EmpD.dsc'
APPEND
into table modified_data
(
rec_no "seq_rec.nextval",
region CONSTANT 31,
load_date "to_date(sysdate,'dd/mm/yyyy')",
data1 POSITION(1:5),
data2 POSITION(6:15) "UPPER(:data2)",
data3 POSITION(16:21) "to_date(:data3,'YYMMDD')"
)
*/

--Lancement de la commande sqlldr
--sqlldr HR/HR control=emp.ctl

--4
--creation de la table :
/*
create table emp_test
(
empid number primary key,
nom varchar(20),
date_E date
);
*/

--contenu du fichier emp1.ctl :
/*
load data infile 'emp1.dat'
discardfile 'empD.dsc'
Append 
into table emp_test
when (5) ='B' and (15:20)='990112'
(
empid POSITION(1:4),
nom POSITION(5:14),
date_E POSITION(15:20) "to_date(:date_E,'YYMMDD')"
)
*/

--Lancement de la commande sqlldr :
--sqlldr HR/HR control=emp1.ctl





























