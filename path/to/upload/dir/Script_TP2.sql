--Correction TP2 DBA
--1
/*
--Pour supprimer le tablespace dans le cas où il existe déjà
DROP tablespace TBL01 including contents and datafiles;

CREATE TABLESPACE TBL01
DATAFILE 'fd01tbl01.dbf' SIZE 6M,
'fd02tbl01.dbf' SIZE 4M;
*/

--2
/*
AlTER DATABASE default tablespace TBL01;
*/

--3
/*
--Pour supprimer le tablespace dans le cas où il existe déjà :
DROP tablespace TBL02 including contents and datafiles;

CREATE TABLESPACE TBL02
DATAFILE 'fd01tbl02.dbf' SIZE 10M,
'fd02tbl02.dbf' SIZE 10M,
'fd0xtbl02.dbf' SIZE 5M;
*/

--4
/*
ALTER TABLESPACE TBL01 ADD DATAFILE 'fd03tbl01.dbf' SIZE 20M;
*/

--5
--pour savoir l emplacement physique des fichiers 

SELECT FILE_NAME
FROM DBA_DATA_FILES
WHERE TABLESPACE_NAME = 'TBL02';

--Pour renommer un fichier il faut passer par 4 étapes :
---1 Rendre le tablespace OFFLINE :
--ALTER TABLESPACE TBL02 OFFLINE;
---2 Renommer le fichier physiquement.
--acceder au repertoire 
cd C:\ORACLEXE\APP\ORACLE\PRODUCT\11.2.0\SERVER\DATABASE\

ren "C:\ORACLEXE\APP\ORACLE\PRODUCT\11.2.0\SERVER\DATABASE\FD0XTBL02.DBF" "FD03TBL02.DBF"
---3 Renommer le fichier logiquement :
--ALTER TABLESPACE TBL02 rename datafile 'fd0Xtbl02.dbf' TO 'fd03tbl02.dbf';
---4 Rendre le tablespace ONLINE :
--ALTER TABLESPACE TBL02 ONLINE; 

--6 
/*
Select tablespace_name from dba_tablespaces;
*/

--7
/*
BEGIN
for i in (select tablespace_name A,
count(*) C from dba_data_files
group by tablespace_name) LOOP
dbms_output.put_line(i.A||' '||i.C);
END LOOP;
END;
/
*/

--8
/*
ALTER TABLESPACE TBL01 ADD DATAFILE
'fd04tbl01.dbf' SIZE 2M AUTOEXTEND ON
NEXT 1M MAXSIZE 4M;
*/
--Pour le supprimer :
--ALTER TABLESPACE TBL01 DROP DATAFILE 'fd04tbl01.dbf';

--9
/*
CREATE TEMPORARY TABLESPACE MonTemp
TEMPFILE 'MonTemp01.dbf' SIZE 5M;

ALTER DATABASE DEFAULT TEMPORARY TABLESPACE MonTemp;
*/

--10
--creation de la fonction stockée :
/*
CREATE OR REPLACE FUNCTION FN_NBR_TAB_TEMP 
RETURN INTEGER
IS
nb integer;
BEGIN
select count(*) into nb
from dba_tablespaces where contents='TEMPORARY';
return nb;
END;
/
*/
--APPEL de la fonction 
--Select fn_nbr_tab_temp from dual;

--11
/*
CREATE USER TD3 identified by TD3 DEFAULT TABLESPACE TBL01;

SELECT username, temporary_tablespace
FROM dba_users
WHERE username = 'TD3';
*/

--12
/*
DROP tablespace TBL01 including contents and datafiles;

select temporary_tablespace from dba_users 
where username ='TD3';
*/

--13
/*
CREATE OR REPLACE PROCEDURE PS_DETAILS_TAB 
IS
tot1 number;
tot01 number;
tot2 number;
BEGIN
for rec in (select tablespace_name A from dba_tablespaces) loop
select nvl(SUM(bytes),0) into tot1 from dba_data_files where tablespace_name = rec.A;
select SUM(bytes) into tot01 from dba_temp_files where tablespace_name = rec.A;
select Nvl(SUM(bytes),0) into tot2 from dba_segments where tablespace_name = rec.A;
if tot1<>0 then
dbms_output.put_line(rec.A ||' '|| tot1 ||' '|| tot2);
else
dbms_output.put_line(rec.A ||' '|| tot01 ||' '|| tot2);
end if;
end loop;
END;
/
*/
--Pour se connecter avec l'utilisateur TD3 il faut lui donne le privilège de connexion :
--conn sys as sysdba
--GRANT connect TO TD3;

--14
--Pour créer des objets il faut donner à l'utilisateur un QUOTA :
--conn sys as sysdba 
-- ALTER USER TD3 quota 2M on TBL01;

--connect TD3/TD3
--privilége : GRANT CREATE TABLE TO TD3;

--creer une table et ajouter des enregistrements :
/*
create table Etudiant
(num_etud   number(10) primary key,
nom_etud  varchar2(30),
moyenne_etud number(4,2)
) ;

begin
for i in 1 .. 10000 loop
insert into Etudiant(num_etud,nom_etud,moyenne_etud)
values(i , 'Etudiant'||i ,10) ; 
end loop ;
end ;
/
*/

-- réexecuter la procédure Proc_taille_tbl et vérifier la taille occupée ????

--15
--connect sys as sysdba
--execute PS_details_tab






























