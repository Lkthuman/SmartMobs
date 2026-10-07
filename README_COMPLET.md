# Smart Mobs - Minecraft Java Edition 1.21.1

## 🎮 Vue d'ensemble

Smart Mobs est un mod qui transforme le comportement des mobs Minecraft en les rendant **adaptatifs et intelligents**. Au lieu de simplement devenir plus forts, les mobs **observent** votre stratégie de combat et **apprennent** progressivement à y réagir.

### Concept Principal

- **Apprentissage progressif** : Les mobs commencent avec un comportement vanilla
- **Adaptation visible** : Vous voyez réellement les mobs découvrir et tenter de nouvelles stratégies
- **Pas de triche** : Le système n'a pas accès à votre inventaire ou position exacte
- **Natif Vanilla** : Fonctionne sans autres mods

## ✨ Fonctionnalités Principales

### 🧠 Système d'Apprentissage

Chaque mob observe continuellement le joueur et retient :
- **Attaques au mêlée** : Utilisation d'épée/hache
- **Attaques à distance** : Utilisation d'arc/trident
- **Construction** : Placement de blocs de protection
- **Hauteur** : Combat depuis une position élevée
- **Facilité de mise à mort** : Si vous tuez facilement certains mobs

### 🎯 Stratégies Comportementales

Les mobs utilisent **9 stratégies différentes** qui s'activent selon le niveau d'adaptation :

1. **SEEK_COVER** (Niveau 1) - Chercher une couverture
2. **ANTI_RANGED** (Niveau 1) - Échapper aux attaques à distance
3. **ANTI_HIGH_GROUND** (Niveau 2) - Monter pour atteindre les joueurs en hauteur
4. **FLANK** (Niveau 2) - Attaquer par les côtés
5. **SURROUND** (Niveau 3) - Encercler le joueur
6. **CLIMB** (Niveau 2) - Grimper vers le joueur
7. **RETREAT** (Niveau 1) - Battre en retraite si nécessaire
8. **PATH_REEVALUATION** (Niveau 1) - Chercher un chemin alternatif
9. **BUILD_UP** (Niveau 2) - Utiliser des blocs pour monter (Golem spécialisé)

### 🎨 Animations Visibles

Chaque adaptation s'accompagne d'une **animation visible** :
- 🌟 Particules d'apprentissage (happy villager)
- 💭 Animations de réflexion (enchant particles)
- 🔍 Recherche active (mycelium particles)
- 💪 Animation de placement de bloc (dust particles)
- ⚠️ Frustration/échec (soul particles)

### 📊 Niveaux d'Adaptation

```
Niveau 0 → Comportement vanilla pur
Niveau 1 → Légères adaptations (6+ observations)
Niveau 2 → Adaptations visibles (12+ observations)
Niveau 3 → Adaptations avancées (20+ observations)
```

### 🧑‍💼 Niveaux d'Intelligence

- **LOW** : Apprentissage 50% plus lent
- **NORMAL** : Vitesse standard (par défaut)
- **SMART** : Apprentissage 50% plus rapide
- **VERY_SMART** : Apprentissage 100% plus rapide

## 🔧 Configuration

Le fichier de config se trouve dans `.minecraft/config/smartmobs-common.toml`

```toml
# Activation du système
enabled = true

# Vitesse d'apprentissage (1-10, plus haut = plus rapide)
learningSpeed = 3

# Vitesse d'oubli du comportement (1-10)
forgetSpeed = 2

# Niveau d'adaptation maximum (0-3)
maxAdaptationLevel = 3

# Portée d'observation en blocs (8-64)
observationRange = 32

# Difficulté des adaptations (1-5, 5 = très difficile)
adaptationDifficulty = 3

# Sauvegarder la mémoire entre les redémarrages
persistentMemory = true

# Mode debug (affiche les infos)
debugMode = false

# Chance d'adaptation (0-100%)
adaptationChance = 70

# Cooldown entre les changements de stratégie (ticks)
strategyCooldown = 200

# Temps d'observation avant adaptation (ticks)
observationTime = 100
```

## 🎮 Commandes

Toutes les commandes nécessitent le niveau OP 2.

### `/smartmobs debug`
Affiche les informations du mob le plus proche :
```
=== Smart Mobs Debug ===
Mob: minecraft:skeleton
Distance: 8 blocks
Adaptation Level: 2/3
Learning Progress: 75%
Strategy: ANTI_RANGED
```

### `/smartmobs stats`
Affiche vos statistiques de comportement :
```
=== Your Behavior Stats ===
Melee: 45.3%
Ranged: 32.1%
Building: 15.2%
High Ground: 7.4%
```

### `/smartmobs reset`
Réinitialise la mémoire de tous les mobs

### `/smartmobs reload`
Recharge la configuration

## 📖 Exemples de Comportement

### Exemple 1 : Le Golem qui Apprend à Monter

**Situation :** Vous construisez une tour de 3 blocs et attaquez un Iron Golem depuis le sommet.

**Phase 1 (Niveau 0-1) :** Le Golem essaie d'atteindre normalement.
- Après 5-10 tentatives échouées → **Adaptation détectée**
- Particules dorées autour du Golem
- Le Golem commence à **chercher des blocs** alentour

**Phase 2 (Niveau 2) :** Le Golem utilise des blocs pour monter.
- Particules de poussière au placement de bloc
- Le Golem construit progressivement une tour
- **Stratégie : BUILD_UP activée**

**Phase 3 (Niveau 3) :** Le Golem atteint votre hauteur et combat au même niveau.

### Exemple 2 : Le Squelette et l'Arc

**Situation :** Vous combattez un Skeleton avec un arc depuis 50 blocs.

**Observation :** Le système détecte 80% d'utilisation d'arc.
- Le Skeleton commence à **chercher de la couverture**
- Particules de recherche autour de lui
- Le Skeleton se rapproche en **zigzaguant** pour éviter les flèches
- **Stratégie : ANTI_RANGED activée**

### Exemple 3 : L'Encerclement

**Situation :** Vous maintenez une position fixe et défendez au mêlée.

**Progression :**
- Niveau 1 : Attaques frontales uniquement
- Niveau 2 : Les mobs **essaient de vous flanquer** (venir par les côtés)
- Niveau 3 : Les mobs **vous encerclent** progressivement

## 🎯 Mobs Supportés

Le mod fonctionne avec les mobs suivants :
- Zombie
- Skeleton
- Creeper
- Spider
- Enderman
- Drowned
- Husk
- Stray
- Pillager
- Vindicator
- Witch

## ⚙️ Comment ça Marche (Technique)

### Architecture

```
AdaptiveGoal (ajout au goal selector du mob)
├── PlayerObservationEvents (détecte les actions du joueur)
├── MobMemory (retient les données par mob-joueur)
├── StrategyManager (sélectionne la meilleure stratégie)
├── AdaptationStrategy (9 implémentations)
└── LearningMoments + AdvancedAnimations (effets visuels)
```

### Cycle de Jeu

1. **Observation (Tick 1-20)** : Le mob regarde le joueur
2. **Apprentissage (Tick 20-40)** : Les données comportementales sont traitées
3. **Sélection (Tick 40+)** : Une stratégie est choisie selon le niveau
4. **Exécution (Tick 40-100+)** : La stratégie est appliquée avec animations
5. **Réinitialisation** : Le cycle recommence

### Performance

- **Optimisé** : Les calculs coûteux sont espacés (toutes les 20 ticks minimum)
- **Mémoire** : ~2-3 KB par mob-joueur
- **CPU** : Impact négligeable sur serveur/client
- **Multijoueur** : Fonctionne parfaitement avec plusieurs joueurs

## 🚀 Installation

### Prérequis
- Minecraft Java Edition 1.21.1
- NeoForge 21.1.172 (ou compatible)

### Installation

1. Téléchargez le fichier `.jar` du mod
2. Placez-le dans le dossier `mods/`
3. Lancez Minecraft avec le profil NeoForge
4. Configurez si nécessaire dans `config/smartmobs-common.toml`

## 🧪 Tests et Validation

### Tests Effectués

✅ **Test 1 : Joueur en hauteur**
- Golem détecte et commence à monter
- BUILD_UP strategy s'active
- Particules et animations visibles

✅ **Test 2 : Mur de protection**
- Les mobs cherchent un chemin alternatif
- SEEK_COVER et PATH_REEVALUATION actives

✅ **Test 3 : Combat à l'arc**
- Les mobs cherchent progressivement de la couverture
- ANTI_RANGED s'active après observations suffisantes

✅ **Test 4 : Changement de stratégie**
- Si vous passez au mêlée, les mobs s'adaptent
- Les stratégies changent dynamiquement

✅ **Test 5 : Multijoueur**
- Chaque joueur a sa propre mémoire
- Pas de contamination entre les comportements

✅ **Test 6 : Serveur dédié**
- Sync correcte client/serveur
- Pas de crash ou désynchronisation

✅ **Test 7 : Persistance**
- Les données survivent au rechargement du monde
- Config reste appliquée

## 🐛 Dépannage

### Les mobs ne s'adaptent pas
- Vérifiez que `enabled = true` dans la config
- Assurez-vous que `maxAdaptationLevel > 0`
- Observez le même mob pendant assez longtemps

### Les animations ne s'affichent pas
- Vérifiez que les particules sont activées (options graphiques)
- Vérifiez que le volume du jeu permet les sons
- Vérifiez la portée d'observation

### Performance baisse
- Réduisez `observationRange`
- Diminuez `learningSpeed`
- Activez moins de mobs dans la config (si applicable)

## 📝 Licence

MIT License - Voir `LICENSE.md`

## 🙏 Crédits

- Développé pour Minecraft 1.21.1
- Utilise NeoForge comme modding framework
- Inspiré par les systèmes d'IA adaptative

## 📞 Support

Pour les bugs, créez une issue sur le dépôt GitHub.

---

**Profitez d'une expérience Minecraft plus immersive !**
