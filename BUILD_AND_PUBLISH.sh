#!/bin/bash

# Smart Mobs - Build and Publish Script
# Compile le mod et prépare la publication CurseForge

echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "Smart Mobs v1.0.0 - Build & Publish Script"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Step 1: Clean previous builds
echo "[1/5] Cleaning previous builds..."
./gradlew clean

if [ $? -ne 0 ]; then
    echo "❌ Clean failed"
    exit 1
fi

echo "✅ Clean successful"
echo ""

# Step 2: Build with Gradle
echo "[2/5] Building mod with Gradle..."
./gradlew build -x test

if [ $? -ne 0 ]; then
    echo "❌ Build failed"
    exit 1
fi

echo "✅ Build successful"
echo ""

# Step 3: Verify JAR
echo "[3/5] Verifying JAR file..."
if [ -f "build/libs/smartmobs-1.0.0.jar" ]; then
    echo "✅ JAR found"
    ls -lh build/libs/smartmobs-1.0.0.jar
else
    echo "❌ JAR not found"
    exit 1
fi

echo ""

# Step 4: Create release directory
echo "[4/5] Preparing release package..."
mkdir -p releases/v1.0.0
cp build/libs/smartmobs-1.0.0.jar releases/v1.0.0/
cp README_COMPLET.md releases/v1.0.0/
cp CHANGELOG.md releases/v1.0.0/
cp RELEASE_NOTES.md releases/v1.0.0/

echo "✅ Release package created"
echo ""

# Step 5: Summary
echo "[5/5] Build Summary"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "JAR Location:     build/libs/smartmobs-1.0.0.jar"
echo "Size:             $(du -h build/libs/smartmobs-1.0.0.jar | cut -f1)"
echo "Release Package:  releases/v1.0.0/"
echo "MD5:              $(md5sum build/libs/smartmobs-1.0.0.jar | awk '{print $1}')"
echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "✅ BUILD SUCCESSFUL - Ready for CurseForge"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "Next steps:"
echo "1. Test JAR in Minecraft 1.21.1 + NeoForge 21.1.172"
echo "2. Upload JAR to CurseForge"
echo "3. Fill metadata and description"
echo "4. Submit for review"
echo ""
echo "Documentation: README_COMPLET.md"
echo "CurseForge Guide: CURSFORGE_DEPLOYMENT.md"
echo ""
