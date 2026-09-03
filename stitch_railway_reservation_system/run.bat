@echo off
setlocal
cd /d "%~dp0"

echo ================================================================
echo    GO TICKET - Indian Railway Reservation Platform
echo    Zero-Dependency Java Backend (Emerald Green Theme)
echo ================================================================
echo.

set BIN_DIR=backend\bin
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

echo [1/2] Compiling Java Sources...
dir /s /b backend\src\main\java\*.java > sources.txt
javac -encoding UTF-8 -d "%BIN_DIR%" @sources.txt
del sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed!
    pause
    exit /b 1
)

echo [2/2] Launching GoTicket Server on port 8080...
echo.
echo Open http://localhost:8080 in your browser.
echo Press Ctrl+C to stop the server.
echo.
java -cp "%BIN_DIR%" com.velocity.VelocityServer 8080

pause
