@echo off
echo.
SCHTASKS /delete /TN MiBackup /F
SCHTASKS /Create /SC WEEKLY /D SAT /ST 22:00:00 /TN MiBackup /TR C:\Users
\MV\Documents\BackupaRAID.bat
::pause
exit