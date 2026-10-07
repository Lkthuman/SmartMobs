# 📥 Guide d'Installation - Smart Mobs v1.0.0

## 🎯 Prérequis

### Avant de commencer
- ✅ Minecraft Java Edition 1.21.1 **officiellement installé**
- ✅ NeoForge **21.1.172 ou supérieur**
- ✅ Java **21 ou supérieur** (check: `java -version`)
- ✅ ~50 MB d'espace libre

---

## 🚀 Installation Rapide (2 minutes)

### Étape 1 : Télécharger le JAR
1. Allez sur : https://github.com/Lkthuman/SmartMobs/releases
2. Téléchargez `smartmobs-1.0.0.jar`
3. Notez le chemin du fichier téléchargé

### Étape 2 : Placer dans le bon dossier

**Windows:**
```
C:\Users\[VotreNom]\AppData\Roaming\.minecraft\mods\
```

**Mac:**
```
~/Library/Application Support/minecraft/mods/
```

**Linux:**
```
~/.minecraft/mods/
```

**Trouvez facilement:** Dans le launcher Minecraft, cliquez sur "Installations", puis sur l'icône dossier à côté de votre version.

### Étape 3 : Vérifier l'installation
1. **Lancez Minecraft** avec le profil NeoForge 21.1.172+
2. Attendez le chargement complet
3. Créez un nouveau monde (Survival)
4. Une fois ingame, tapez : `/smartmobs debug`
5. Si aucune erreur rouge → **Installation réussie!** ✅

---

## 🔧 Installation NeoForge (si pas encore installé)

### Méthode 1 : Installer via Minecraft Launcher
1. **Téléchargez NeoForge** : https://neoforged.net/
2. **Installer la version 21.1.172** pour Minecraft 1.21.1
3. **Lancez l'installeur**
4. Cochez "Install client"
5. Validez
6. NeoForge s'installe automatiquement

### Méthode 2 : Vérifier si NeoForge est déjà installé
1. Lancez Minecraft Launcher
2. Allez dans "Installations"
3. Cherchez un profil avec "NeoForge 21.1.172"
4. Si absent → Installez via la Méthode 1

---

## ✅ Vérifications Post-Installation

### Vérification 1 : JAR au bon endroit
```
.minecraft/mods/smartmobs-1.0.0.jar
```

### Vérification 2 : Pas d'erreur au démarrage
- Lancez le jeu
- Console : Pas de message d'erreur Smart Mobs

### Vérification 3 : Configuration générée
```
.minecraft/config/smartmobs-common.toml
```
Ce fichier doit exister après le premier lancement.

### Vérification 4 : Commande de test
Ingame : `/smartmobs debug`

Résultat attendu :
```
[Smart Mobs Debug]
Target: [Mob Name]
Distance: X.X blocs
...
```

---

## ⚙️ Configuration de Base

### Fichier : `.minecraft/config/smartmobs-common.toml`

```toml
# ===== SMART MOBS CONFIGURATION =====

# Activer/désactiver le mod globalement
enabled = true

# Vitesse d'apprentissage (1-10)
# 1 = très lent, 10 = très rapide
learningSpeed = 3

# Vitesse d'oubli (1-10)
# Les mobs oublient progressivement ce qu'ils ont appris
forgetSpeed = 2

# Niveau d'adaptation maximum (0-3)
# 0 = pas d'adaptation, 3 = adaptation complète
maxAdaptationLevel = 3

# Distance d'observation du joueur (blocs)
# Les mobs observent le joueur si distance < observationRange
observationRange = 32

# Difficulté d'adaptation (1-5)
# 1 = facile, 5 = très difficile
adaptationDifficulty = 3

# Sauvegarder la mémoire entre sessions
persistentMemory = true

# Mode debug (affiche les logs)
debugMode = false

# Chance d'adaptation (0-100%)
# Chaque tick, 70% de chance que le mob adapte sa stratégie
adaptationChance = 70

# Cooldown entre stratégies (ticks)
# 200 ticks = 10 secondes
strategyCooldown = 200

# Temps d'observation avant adaptation (ticks)
observationTime = 100
```

### Présets Recommandés

**Facile (apprentissage lent):**
```toml
learningSpeed = 1
adaptationDifficulty = 1
adaptationChance = 30
```

**Normal (défaut):**
```toml
learningSpeed = 3
adaptationDifficulty = 3
adaptationChance = 70
```

**Difficile (apprentissage rapide):**
```toml
learningSpeed = 6
adaptationDifficulty = 4
adaptationChance = 85
```

**Très Difficile (apprentissage ultra-rapide):**
```toml
learningSpeed = 10
adaptationDifficulty = 5
adaptationChance = 100
maxAdaptationLevel = 3
```

---

## 🎮 Premier Test

### Scénario 1 : Observer l'apprentissage du Golem

1. **Créez un monde Survival**
2. **Mode créatif** : `/gamemode creative`
3. **Trouvez un Iron Golem** (ou spawner un avec `/summon iron_golem`)
4. **Construisez une tour** de 3-4 blocs de haut
5. **Montez au sommet**
6. **Attaquez le Golem** depuis le haut
7. **Regardez** : Dans 30-60 secondes, le Golem devrait :
   - Observer (particules mystérieuses)
   - Réfléchir (pause)
   - Chercher des blocs
   - MONTER progressivement

### Scénario 2 : Vérifier le debug

1. **Spawner un Skeleton** : `/summon skeleton`
2. **Tapez** : `/smartmobs debug`
3. **Résultat attendu** :
   ```
   [Smart Mobs Debug]
   Target: Skeleton
   Distance: X.X blocs
   Adaptation Level: X
   Strategy: [Stratégie]
   ...
   ```

---

## 🚨 Dépannage

### ❌ Erreur : "Mod not found" ou "Missing dependencies"

**Cause** : NeoForge n'est pas installé ou mauvaise version.

**Solution**:
1. Installez NeoForge 21.1.172 pour Minecraft 1.21.1
2. Assurez-vous que le profil NeoForge est actif
3. Relancez le jeu

### ❌ Le JAR est ignoré

**Cause** : Le JAR est au mauvais endroit ou mauvais nom.

**Solution**:
1. Vérifiez : `.minecraft/mods/smartmobs-1.0.0.jar`
2. Le nom doit être **exact**
3. Supprimez les autres versions
4. Relancez le launcher

### ❌ Console : Erreur de chargement du mod

**Cause** : Java 21+ pas utilisé ou conflit.

**Solution**:
1. Vérifiez : `java -version` (doit être 21+)
2. Dans Minecraft Launcher → Options Java
3. Vérifiez le chemin Java
4. Redémarrez

### ❌ Les mobs ne s'adaptent pas

**Cause** : Mod désactivé ou config incorrecte.

**Solution**:
1. Vérifiez : `enabled = true` dans `smartmobs-common.toml`
2. Vérifiez : `adaptationChance = 70` (minimum 50)
3. Vérifiez : `maxAdaptationLevel = 3`
4. Redémarrez le monde

### ❌ Lag / Ralentissement

**Cause** : Configuration trop agressive.

**Solution**:
1. Augmentez `strategyCooldown` à 300-400
2. Baissez `adaptationChance` à 50
3. Baissez `learningSpeed` à 2
4. Relancez

### ❌ Crash au démarrage

**Cause** : Conflit avec un autre mod.

**Solution**:
1. Supprimez temporairement Smart Mobs
2. Testez que Minecraft se lance
3. Réajoutez Smart Mobs seul
4. Si ça recrash, c'est une incompatibilité (rare)

---

## 🔧 Commandes Essentielles

### `/smartmobs debug`
Affiche les infos du mob le plus proche.
```
Usage: /smartmobs debug
Résultat: Statistiques complètes du mob
```

### `/smartmobs stats`
Affiche vos statistiques comportementales.
```
Usage: /smartmobs stats
Résultat: Melee %, Ranged %, Building %, etc.
```

### `/smartmobs reset`
Réinitialise la mémoire des mobs (oubli complet).
```
Usage: /smartmobs reset
Effet: Tous les mobs recommencent à 0
```

### `/smartmobs reload`
Recharge la configuration sans redémarrer.
```
Usage: /smartmobs reload
Effet: Applique les nouveaux paramètres
```

---

## 📊 Monitoring

### Vérifier que le mod fonctionne

**Console (F3 + M) :**
- Cherchez : `[Smart Mobs]` messages
- Pas d'erreurs en rouge

**Logs (.minecraft/logs/latest.log) :**
```bash
grep "Smart Mobs" ~/.minecraft/logs/latest.log
```

**Performance :**
- FPS normal (60+)
- Pas de ralentissement au spawn de mobs
- Pas de memory leak (RAM stable)

---

## 🌐 Multijoueur / Serveur

### Pour Serveur Vanilla
1. Téléchargez NeoForge server
2. Placez Smart Mobs dans `mods/`
3. Lancez le serveur
4. Clients n'ont **pas besoin** du mod (mod-side server)

### Pour Serveur Paper/Purpur
1. Smart Mobs doit être sur le serveur
2. Les clients n'ont pas besoin du mod
3. Config centralisée sur le serveur

---

## ✅ Checklist Finale

- [ ] Minecraft 1.21.1 installé
- [ ] NeoForge 21.1.172+ installé
- [ ] JAR téléchargé
- [ ] JAR placé dans `.minecraft/mods/`
- [ ] Jeu relancé
- [ ] `/smartmobs debug` fonctionne
- [ ] Config générée
- [ ] Aucune erreur console
- [ ] Premier test réussi

---

## 🎉 Installation Terminée!

Vous pouvez maintenant profiter de Smart Mobs v1.0.0! 🎮✨

Pour toute question : https://github.com/Lkthuman/SmartMobs/issues
