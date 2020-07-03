call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runchrome
echo runcrud return errors

:runchrome
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Error while starting chrome

:end
echo Work is finished