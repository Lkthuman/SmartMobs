# 🔨 Comment Compiler et Obtenir le JAR

## Option 1: Utiliser les Scripts de Compilation (RECOMMANDÉ)

### Sur Linux/Mac:
```bash
chmod +x BUILD_JAR.sh
./BUILD_JAR.sh
```

### Sur Windows:
```batch
BUILD_JAR.bat
```

**Résultat**: Le JAR sera généré dans `dist/smartmobs-1.0.0.jar`

---

## Option 2: Compilation Manuelle avec Gradle

### Linux/Mac:
```bash
./gradlew clean
./gradlew build
```

### Windows:
```batch
gradlew.bat clean
gradlew.bat build
```

**JAR généré**: `build/libs/smartmobs-1.0.0.jar`

---

## Option 3: IDE (IntelliJ IDEA / Eclipse)

1. Ouvre le projet dans ton IDE
2. Clique sur **Gradle** → **Tasks** → **build**
3. Double-clique sur **build**
4. Attends la compilation
5. JAR dans `build/libs/smartmobs-1.0.0.jar`

---

## ✅ Vérifier la Compilation

```bash
# Vérifier le JAR généré
ls -lh build/libs/smartmobs-1.0.0.jar

# Vérifier le contenu du JAR
unzip -l build/libs/smartmobs-1.0.0.jar | head -20
```

---

## 📦 Installation du JAR

Une fois compilé:

1. Localise `.minecraft/mods/`
2. Copie le JAR: `smartmobs-1.0.0.jar`
3. Assure-toi que **NeoForge 21.1.172+** est installé
4. Lance Minecraft 1.21.1
5. Entre un monde Survival
6. Teste: `/smartmobs debug`

---

## 🔍 Dépannage

### ❌ "gradlew: command not found"
**Solution**: Utilise `./gradlew` ou `gradle` si Gradle est dans PATH

### ❌ "Invalid Gradle JDK"
**Solution**: Installe Java 21
```bash
java -version
```
Doit afficher **Java 21+**

### ❌ "Build failed"
**Solution**: Nettoie et recommence
```bash
./gradlew clean
./gradlew build --info
```

### ❌ JAR vide ou corrompu
**Solution**: Supprime les caches
```bash
rm -rf .gradle build dist
./gradlew build
```

---

## 📊 Taille du JAR

- **Avec sources**: ~3-4 MB
- **Sans sources**: ~2-2.5 MB
- **Taille décompressée**: ~5-6 MB

---

## 🎯 Résumé

```
1. Clone le dépôt ✅
2. Run BUILD_JAR.sh (ou .bat) ✅
3. Copie dist/smartmobs-1.0.0.jar dans .minecraft/mods/ ✅
4. Lance Minecraft ✅
5. Test: /smartmobs debug ✅
```

**C'est tout!** 🎉
