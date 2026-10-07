#!/bin/bash
# Script de compilation du JAR SmartMobs v1.0.0

echo "====================================="
echo "SmartMobs v1.0.0 - Compilation"
echo "====================================="

# Vérifie que Gradle est disponible
if ! command -v gradle &> /dev/null && [ ! -f "./gradlew" ]; then
    echo "❌ Erreur: Gradle ou gradlew non trouvé"
    exit 1
fi

echo "📦 Nettoyage des builds précédents..."
./gradlew clean

echo "🔨 Compilation du projet..."
./gradlew build

if [ $? -eq 0 ]; then
    echo "✅ Compilation réussie!"
    echo "📁 JAR généré: build/libs/smartmobs-1.0.0.jar"
    
    # Copie vers le dossier dist
    mkdir -p dist
    cp build/libs/smartmobs-1.0.0.jar dist/
    cp build/libs/smartmobs-1.0.0-sources.jar dist/smartmobs-1.0.0-sources.jar 2>/dev/null || true
    
    echo "📦 JAR copié vers dist/"
    echo ""
    echo "Installation:"
    echo "1. Copie dist/smartmobs-1.0.0.jar dans .minecraft/mods/"
    echo "2. Lance Minecraft avec NeoForge"
    echo "3. Test: /smartmobs debug"
else
    echo "❌ Compilation échouée"
    exit 1
fi
