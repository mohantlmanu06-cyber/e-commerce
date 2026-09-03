# GO TICKET - Launch Script (PowerShell)
$ErrorActionPreference = "Stop"

Write-Host "================================================================" -ForegroundColor Green
Write-Host "   GO TICKET - Indian Railway Reservation Platform" -ForegroundColor Green
Write-Host "   Zero-Dependency Java REST Backend (Emerald Green Theme)" -ForegroundColor Green
Write-Host "================================================================" -ForegroundColor Green

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $scriptDir

$binDir = Join-Path $scriptDir "backend\bin"
if (-not (Test-Path $binDir)) {
    New-Item -ItemType Directory -Path $binDir | Out-Null
}

Write-Host "`n[1/2] Compiling Java Backend sources..." -ForegroundColor Cyan
$sources = Get-ChildItem -Path "backend/src/main/java" -Recurse -Filter "*.java" | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d "$binDir" $sources

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Compilation failed." -ForegroundColor Red
    exit 1
}
Write-Host "[✓] Compilation successful." -ForegroundColor Green

Write-Host "`n[2/2] Launching GoTicket Server on http://localhost:8080..." -ForegroundColor Cyan
Write-Host "Web UI available at: http://localhost:8080" -ForegroundColor Green
Write-Host "Press Ctrl+C to stop server.`n" -ForegroundColor Yellow

java -cp "$binDir" com.velocity.VelocityServer 8080
