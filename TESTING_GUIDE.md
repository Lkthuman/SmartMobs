# Guide de Test du Mod SmartMobs

## 🎯 Objectif

Vérifier que les mobs apprennent progressivement du comportement du joueur et adaptent leur IA.

## 📋 Prérequis

- Minecraft 1.21.1
- NeoForge 1.21.1 installé
- Le JAR du mod dans `mods/`
- Monde en mode Créatif ou Survie (Difficile recommandé)

## ✅ Tests Fondamentaux

### Test 1 : Le mod se charge

**Étapes :**
1. Lance Minecraft
2. Ouvre le journal (`.minecraft/logs/latest.log`)
3. Cherche "SmartMobs" dans le journal

**Résultat attendu :**
```
[INITIALIZE] SmartMobs has loaded successfully
```

Si tu vois une erreur, le mod n'a pas les bonnes dépendances.

---

### Test 2 : Détection du comportement du joueur

**Étapes :**
1. Lance un monde en mode Créatif
2. Prépare-toi avec un arc
3. Invoque un Skeleton : `/summon minecraft:skeleton`
4. Tire des flèches sur le Skeleton (minimum 10 tirs)
5. Exécute `/smartmobs debug`

**Résultat attendu :**
```
Smart Mobs Debug
Target: Skeleton
Player behavior:
Ranged: HIGH (80%+)
Melee: LOW
Building: NONE
```

---

### Test 3 : Adaptation au comportement de mêlée

**Étapes :**
1. Lance un monde en Survie (Difficile)
2. Crée une petite arène avec un Zombie
3. Attaque le Zombie avec une épée (20+ coups)
4. Observe le comportement du Zombie

**Résultat attendu :**
- Au début : Le Zombie vous attaque normalement
- Après plusieurs hits : Le Zombie peut reculer, contourner, ou changer sa distance
- C'est du pur apprentissage ! 🧠

---

### Test 4 : Combat en hauteur

**Étapes :**
1. Lance un monde en Survie
2. Construis une tour de 3 blocs
3. Invoque un Iron Golem : `/summon minecraft:iron_golem`
4. Attaque-le depuis le sommet (arc ou épée)
5. Observe pendant 2-3 minutes

**Résultat attendu :**
- Après plusieurs tentatives, le Golem cherche une solution
- Le Golem peut tenter de monter (selon l'implémentation)
- Le comportement change progressivement

---

### Test 5 : Commandes

**Étapes :**
1. Ouvre le chat (`T`)
2. Exécute : `/smartmobs stats`
3. Puis : `/smartmobs reload`
4. Puis : `/smartmobs debug`

**Résultat attendu :**
- Les commandes ne génèrent pas d'erreur
- Le debug affiche les infos correctement

---

### Test 6 : Multijoueur

**Étapes :**
1. Lance un serveur avec le mod
2. Connecte 2 joueurs
3. Joueur 1 utilise un arc contre un Skeleton
4. Joueur 2 utilise une épée contre un Zombie
5. Observe que les mobs adaptent leurs comportements DIFFÉREMMENT pour chaque joueur

**Résultat attendu :**
- Le Skeleton du joueur 1 s'adapte aux attaques à distance
- Le Zombie du joueur 2 s'adapte au combat rapproché
- Aucune confusion entre les mémoires

---

## 🐛 Diagnostic des Problèmes

### Le mod ne se charge pas

**Vérifications :**
1. NeoForge 1.21.1 est bien installé
2. Le JAR est dans le dossier `mods/`
3. Aucune erreur de dépendances

**Solution :**
- Supprime le dossier `versions` de `.minecraft`
- Relance Minecraft avec le NeoForge installer

---

### Les mobs ne changent pas de comportement

**Vérifications :**
1. Vérifie avec `/smartmobs debug` que le comportement du joueur est détecté
2. Vérifie que `enabled: true` dans la config
3. Attends au moins 30 secondes (cooldown d'adaptation)

**Solution :**
- Réinitialise avec `/smartmobs reset`
- Recharge avec `/smartmobs reload`

---

### Crash au démarrage

**Vérifications :**
1. Ouvre `.minecraft/logs/latest.log`
2. Cherche les erreurs `SmartMobs`
3. Vérifie la version de NeoForge

**Solution :**
- Mets à jour vers la dernière version du mod
- Utilise NeoForge 1.21.1 officiel

---

## 🎬 Scénario de Test Complet

### Durée : 10-15 minutes

**Joueur 1 : Archer**
1. Lance un monde Survie
2. Invoque 3 Skeletons
3. Tire sur eux pendant 5 minutes avec un arc
4. Les Skeletons devraient chercher davantage d'abri
5. Exécute `/smartmobs debug` pour voir l'adaptation

**Joueur 2 : Guerrier**
1. Dans le même monde
2. Invoque 3 Zombies
3. Combat au mêlée pendant 5 minutes
4. Les Zombies devraient adapter leur distance
5. Observe les changements de comportement

**Résultat :**
- Les deux types de mobs s'adaptent différemment
- L'IA reste équilibrée (pas invincible)
- Le jeu reste fun 🎮

---

## 📊 Métriques de Succès

✅ Le mod se charge sans erreur
✅ Les commandes fonctionnent
✅ Le debug affiche les bonnes infos
✅ Les mobs changent de comportement après observation
✅ Le multijoueur fonctionne correctement
✅ Pas de crash
✅ Performance acceptable (FPS stable)

---

## 📝 Rapport de Test

Si tu trouves un bug, crée une issue sur GitHub avec :
- Ton OS (Windows/Linux/Mac)
- Ta version Minecraft
- Tes logs (`.minecraft/logs/latest.log`)
- Les étapes pour reproduire le bug

💪 Merci de tester SmartMobs !
