@echo off
echo �������������ѡ�dev, prd, test�е�һ��
set /p env=>nul 
echo ���û�����%env%
cd %~dp0
call mvn clean package -Denv=%env%
pause