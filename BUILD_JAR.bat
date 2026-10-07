@echo off
REM Script de compilation du JAR SmartMobs v1.0.0 (Windows)

echo =====================================
echo SmartMobs v1.0.0 - Compilation
echo =====================================

REM Vérifie que gradlew existe
if not exist "gradlew.bat" (
    echo Erreur: gradlew.bat non trouvé
    exit /b 1
)

echo Nettoyage des builds precedents...
call gradlew.bat clean

echo Compilation du projet...
call gradlew.bat build

if errorlevel 1 (
    echo Compilation echouee
    exit /b 1
)

echo Compilation reussie!
echo JAR genere: build\libs\smartmobs-1.0.0.jar

REM Copie vers le dossier dist
if not exist "dist" mkdir dist
copy build\libs\smartmobs-1.0.0.jar dist\

echo JAR copie vers dist\
echo.
echo Installation:
echo 1. Copie dist\smartmobs-1.0.0.jar dans .minecraft\mods\
echo 2. Lance Minecraft avec NeoForge
echo 3. Test: /smartmobs debug
