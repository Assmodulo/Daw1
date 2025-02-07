@echo off
msg * Vamos a programar una tarea. Introduce hora y minuto.
echo.
set /p hora= Introduce la hora deseada de ejecucion:?
echo.
set /p minuto= Introduce el minuto:?
echo.
SCHTASKS /delete /TN recordatorio /F
SCHTASKS /Create /SC DAILY /TN recordatorio /TR C:\Users\MV\Documents\
mensaje.vbs /ST %hora%:%minuto%
::pause
exit