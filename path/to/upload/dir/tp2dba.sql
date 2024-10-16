----1
/*
create tablespace TBL_INFRA1
datafile 'INFR01
.dbf' size 10M autoextend on next  5M maxsize 30M,
'INFR02.dbf' size 20M; 
*/
---select file_name from dba_data_files where tablespace_name='TBL_INFRA1';

----alter database default tablespace TBL_INFRA1; 
---select default_tablespace from dba_users where username='HR';


----2
/*
ALTER DATABASE DATAFILE 'INFR02.dbf'
resize 25M;
*/
/*
select sum(bytes) taille from dba_data_files 
where tablespace_name='TBL_INFRA1';
*/
---3
---alter tablespace TBL_INFRA1 add datafile 'INFRA03.dbf' size 5 M; 

---select file_name from dba_data_files where tablespace_name='TBL_INFRA1';

----4

-----tablespace offline 
----alter tablespace TBL_INFRA1 offline;
---renommer physiquement  
-----renommer logiquement sur la base 
----alter database rename file 'INFRA03.dbf' to 'INFRA0S.dbf'; 
-----tablespace online

--- select file_name from dba_data_files where tablespace_name='TBL_INFRA1';


---5
/*
create tablespace TBL_INFRA2
datafile 'INFR1.dbf' size 10M ,
'INFR2.dbf' size 10M, 'INFR3.dbf' size 5M autoextend on next 1M;
*/

---6
/*
set serveroutput on 
declare
cursor c is select tablespace_name nom, count(*) nbr from dba_data_files group by tablespace_name; 
begin
for i in c 
loop
dbms_output.put_line (' le nom est '|| i.nom || ' a  fichier '|| ''|| i.nbr ); 
end loop;
end;
/
*/

---7
/*
create or replace procedure PROC_AVAILABLE
IS 
v_dispo number;
BEGIN
for i in (select tablespace_name nom from dba_free_space)
loop
select (nvl (sum(bytes),0)) into v_dispo from dba_free_space where tablespace_name=i.nom; 
dbms_output.put_line (' le nom est '|| i.nom || 'diponible est ' || v_dispo); 
end loop;
END;
/
*/


---set serveroutput on 
---execute PROC_AVAILABLE


----8

create temporary  tablespace INFRA_TEMP
tempfile 'T1.tmp' size 5M ,
'T2.tmp' size 5M; 

/* vérification :
select FILE_NAME, FILE_ID from dba_temp_files where TABLESPACE_NAME='INFRA_TEMP';
*/

---rendre temporary
alter database default temporary tablespace infra_temp;
select  TEMPORARY_TABLESPACE from dba_users;

----9
create or replace function FN_NBR_TEMP
return integer
is
v_total integer;
begin 
select count(*) into v_total from dba_tablespaces where contents ='TEMPORARY';
return v_total;
end;
/
-----show errors
-----conn sys/esprit as sysdba
select FN_NBR_TEMP from dual;
----10
create or replace procedure PROC_TABLES
is
begin
for i in (select tablespace_name nom from dba_tablespaces)
loop
dbms_output.put_line('le nom du tablespace est:' || i.nom);
for j in (select table_name tab, owner o from dba_tables where TABLESPACE_NAME=i.nom)
loop
dbms_output.put_line('le nom du tablespace est:' || i.nom|| 'la table' || j.tab|| 'pour'|| j.o);
end loop;
end loop;
end;
/

drop tablespace_name from dba_tablespaces;
/*on a 2 types de suppression : logique : infra_temp,
-------------------------------- physique infra_temp icluding datafiles; ( ymigriw lel tablespace par defaut + on ne peut pas supprimer un tablespace par defaut)
-------------------------------- l+p+Objects physique infra_temp icluding datafiles and contents; 
*/
alter database default temporary tablespace temp;
drop tablespace 'INFRA_TEMP' including datafiles and contents;

----13
create or replace procedure PROC_details
is
v_occupe number;
v_total_per number; --permanant
v_total_temp number; --temporaire
begin
for i in (select tablespace_name nom, contents type from dba_tablespaces)
loop
select nvl(sum(bytes),0) into v_occupe from dba_segments where tablespace_name=i.nom;  
select nvl(sum(bytes),0) into v_total_per from dba_data_files where tablespace_name=i.nom; 
select nvl(sum(bytes),0) into v_total_temp from dba_temp_files where tablespace_name=i.nom;  
if i.type='TEMPORARY' THEN
dbms_output.put_line( i.nom|| 'total' || v_total_temp|| 'occupé dans :'|| v_occupe);
else
dbms_output.put_line( i.nom|| 'total' || v_total_per|| 'occupé dans :'|| v_occupe);
end if;
end loop;
end;
/

















 










 


