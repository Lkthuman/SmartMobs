# 📥 TELECHARGER Smart Mobs v1.0.0 - VERSION FINALE

## 🌟 STATUS FINAL

```
╔══════════════════════════════════════════════════╗
║  SMART MOBS v1.0.0 - PRODUCTION READY ✅              ║
║  Le mod Minecraft le plus complet avec IA adaptative     ║
║  Animations visibles + 9 stratégies comportementales     ║
╚══════════════════════════════════════════════════╝
```

---

## 🔗 LES 3 FAÇONS DE TÉLÉCHARGER

### 1️⃣ GITHUB RELEASES (Officiel) ⭐ RECOMMANDÉ

**Lien Direct:**
```
https://github.com/Lkthuman/SmartMobs/releases/tag/v1.0.0
```

**Étapes:**
1. Ouvrez le lien ci-dessus
2. Cherchez la section **"Releases"**
3. Téléchargez **`smartmobs-1.0.0.jar`**
4. Placez dans `.minecraft/mods/`
5. Lancez Minecraft avec NeoForge

**Avantages:**
✅ Mise à jour automatique  
✅ Historique complet  
✅ Changelog visible  
✅ Plus sécurisé  

---

### 2️⃣ TÉLÉCHARGEMENT DIRECT

**URL du JAR:**
```
https://github.com/Lkthuman/SmartMobs/releases/download/v1.0.0/smartmobs-1.0.0.jar
```

**Méthode:**
- Copier l'URL
- Coller dans le navigateur
- Clic droit → "Enregistrer sous"
- Placer dans `.minecraft/mods/`

---

### 3️⃣ COMPILATION MANUELLE (Pour développeurs)

**Cloner le dépôt:**
```bash
git clone https://github.com/Lkthuman/SmartMobs.git
cd SmartMobs
./gradlew build
```

**JAR généré:**
```
build/libs/smartmobs-1.0.0.jar
```

---

## 📻 INSTALLATION (5 MINUTES)

### Étape 1: Vérifier les Prérequis

```
✅ Minecraft Java Edition 1.21.1 (installé)
✅ NeoForge 21.1.172+ (installé)
✅ Java 21+ (vérifier: java -version)
```

### Étape 2: Localiser le Dossier mods

**Windows:**
```
C:\Users\[NomUtilisateur]\AppData\Roaming\.minecraft\mods\
```

**Mac:**
```
~/Library/Application Support/minecraft/mods/
```

**Linux:**
```
~/.minecraft/mods/
```

**Raccourci facile dans Minecraft Launcher:**
- Cliquez sur "Installations"
- Cherchez votre version NeoForge
- Clic droit → "Open folder"
- Allez dans le dossier `mods`

### Étape 3: Placer le JAR

1. **Téléchargez** `smartmobs-1.0.0.jar`
2. **Glissez-déposez** dans le dossier `mods`
3. **Ou** copiez-collez le fichier

### Étape 4: Lancer le Jeu

1. Lancez Minecraft Launcher
2. Sélectionnez le profil **NeoForge 21.1.172+**
3. Cliquez "Jouer"
4. Attendez le chargement complet

### Étape 5: Vérifier l'Installation

Une fois ingame:
```
/smartmobs debug
```

**Résultat attendu:**
```
[Smart Mobs Debug]
Target: [Mob Name]
Distance: X.X blocs
...
```

✅ Si pas d'erreur rouge → **Installation réussie!**

---

## 📊 VÉRIFICATIONS RAPIDES

### Le JAR s'est bien téléchargé?

```
✅ Nom: smartmobs-1.0.0.jar
✅ Taille: ~2.4 MB
✅ Format: JAR (pas .part ni .txt)
✅ Pas d'erreur de téléchargement
```

### Le JAR est au bon endroit?

```
.minecraft/mods/smartmobs-1.0.0.jar
                 ↑ Exact, pas d'autre dossier
```

### NeoForge est installé?

```
Minecraft Launcher → Installations
                   → Cherchez "NeoForge 21.1.172"
                   → Doit exister
```

### Java 21+ est installé?

```
Ouvrez terminal/CMD:
java -version

Doit afficher: 21.x.x ou supérieur
```

---

## ⚠️ PROBLÈMES COURANTS

### ❌ "Mod not found" ou erreur au démarrage

**Cause:** NeoForge n'est pas installé ou mauvaise version.

**Solution:**
1. Installez NeoForge 21.1.172 depuis https://neoforged.net/
2. Assurez-vous que le profil NeoForge est sélectionné
3. Relancez Minecraft

### ❌ Le JAR n'apparaît pas dans le dossier mods

**Cause:** Problème de permissions ou chemin incorrect.

**Solution:**
1. Fermez Minecraft complètement
2. Vérifiez le chemin du dossier mods
3. Copiez-collez le JAR manuellement
4. Relancez

### ❌ Console: "[Smart Mobs] Error loading"

**Cause:** Fichier corrompu ou mauvaise version Java.

**Solution:**
1. Supprimez le JAR
2. Téléchargez-le à nouveau
3. Vérifiez Java: `java -version` (doit être 21+)
4. Réinstallez

### ❌ La commande `/smartmobs debug` ne marche pas

**Cause:** Le mod n'est pas chargé correctement.

**Solution:**
1. Vérifiez les logs: `.minecraft/logs/latest.log`
2. Cherchez "Smart Mobs" dans les logs
3. S'il n'y a rien → le mod n'est pas chargé
4. Vérifiez que le JAR est dans le bon dossier

---

## 📚 DOCUMENTATION APRÈS INSTALLATION

Une fois installé, consultez:

```
📄 README_COMPLET.md
   └─ Vue d'ensemble complète du mod

📄 GUIDE_UTILISATION.md
   └─ Comment utiliser et configurer

📄 INSTALLATION_GUIDE.md
   └─ Guide détaillé d'installation

📄 RELEASE_NOTES.md
   └─ Fonctionnalités principales

📄 CHANGELOG.md
   └─ Historique des versions
```

Tous disponibles sur GitHub:
https://github.com/Lkthuman/SmartMobs

---

## 🎮 PREMIER TEST

### Tester que ça fonctionne:

1. **Créez un nouveau monde** (Survival)
2. **Spawner un mob**: `/summon iron_golem`
3. **Construire une tour**: 3 blocs de haut
4. **Attaquer le Golem** depuis le sommet
5. **Observer**: Après 30-60 secondes, le Golem doit chercher à monter vers vous

**Si ça marche** → Installation réussie! 🎉

---

## 💾 CONFIGURATION

Après le premier lancement, la config est générée:

```
.minecraft/config/smartmobs-common.toml
```

Vous pouvez ajuster:
- Vitesse d'apprentissage
- Niveau de difficulté
- Cooldowns
- Mode debug

Sans redémarrer le jeu, utilisez:
```
/smartmobs reload
```

---

## 🚀 VOUS ÊTES PRÊT!

```
✅ Téléchargé smartmobs-1.0.0.jar
✅ Placé dans .minecraft/mods/
✅ NeoForge 21.1.172+ installé
✅ Java 21+ vérifié
✅ Minecraft 1.21.1 prêt
✅ Premier test réussi

👉 Vous pouvez maintenant profiter du mod!
```

---

## 🔗 RESSOURCES RAPIDES

| Ressource | Lien |
|-----------|------|
| **Dépôt GitHub** | https://github.com/Lkthuman/SmartMobs |
| **Releases** | https://github.com/Lkthuman/SmartMobs/releases |
| **Issues/Bugs** | https://github.com/Lkthuman/SmartMobs/issues |
| **JAR Téléchargement** | https://github.com/Lkthuman/SmartMobs/releases/download/v1.0.0/smartmobs-1.0.0.jar |
| **NeoForge** | https://neoforged.net/ |
| **Minecraft Launcher** | https://www.minecraft.net/download |

---

## 📞 SUPPORT

**Besoin d'aide?**

- GitHub Issues: https://github.com/Lkthuman/SmartMobs/issues
- Discussions: https://github.com/Lkthuman/SmartMobs/discussions
- Email: support@smartmobs.dev

---

## ✨ APPRÉCIEZ!

Smart Mobs v1.0.0 vous offre:
- 🧠 Une véritable IA adaptative
- 🎬 16 animations visibles
- 🎯 9 stratégies comportementales
- 🗣️ 11 mobs supportés
- ⚙️ Configuration complète
- 🚀 Performance optimisée
- 👥 Multijoueur natif

**Profitez d'une expérience Minecraft unique!** 🎮✨

---

**Dernière mise à jour:** 7 Octobre 2026  
**Version:** 1.0.0  
**Minecraft:** 1.21.1  
**Licence:** MIT  
