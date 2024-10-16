--TP3
--1
/*
CREATE PROFILE profil_tp3 LIMIT
connect_time 7200
idle_time 180
sessions_per_user 2
failed_login_attempts 1
password_lock_time 5/1440;
*/
--Pour vérifier :
--select * from dba_profiles where profile='PROFIL_TP3';

--2
/*
CREATE USER TP3 identified by TP3
default tablespace USERS
quota 10M on USERS 
temporary tablespace Temp
Profile profil_tp3;
*/
--Pour vérifier
--select username from dba_users;

--3
/*
select profile from dba_users 
where username='TP3';
*/
--4
--select username from dba_users;

--5
/*
CREATE OR REPLACE PROCEDURE ps_list_profil
IS
BEGIN
for i in (select distinct profile A 
from dba_profiles)
LOOP
dbms_output.put_line(i.A);
END LOOP;
END;
/
*/
--APPEL :
--execute ps_list_profil

--6
/*
CREATE OR REPLACE FUNCTION verif_password
(utilisateur varchar,
password varchar,
old_password varchar ) 
RETURN boolean
IS
c integer :=0;
BEGIN
if length(password)<6 then
raise_application_error(-20000,
'le mdp doit contenir plus que 
6 caracteres');
end if;
for i in 1..length(password) Loop
if substr(password,i,1) in ('?','!','@')
then c:=c+1;
end if;
end loop;
if c=0 then
raise_application_error(-20000,
'le mdp doit contenir au moins un caractere 
special');
end if;
if utilisateur=password then
raise_application_error(-20000,
'le mdp doit etre different du username');
end if;
RETURN true;
END;
/
*/

--7
/*
ALTER PROFILE profil_tp3 LIMIT 
password_verify_function verif_password;
*/
--8
--create user testtp31 identified by system profile profil_tp3;
create user testtp31 identified by testtp31 profile profil_tp3;
create user testtp31 identified by managers profile profil_tp3;

--9
DROP PROFILE profil_tp3 cascade;
select profile from dba_users
where username='TP3';












