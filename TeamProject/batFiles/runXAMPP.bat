cd C:\xampp\mysql\bin
start mysqld.exe
mysql -hlocalhost -ustudent -phello student_space < "%~dp0databaseTEST.sql"