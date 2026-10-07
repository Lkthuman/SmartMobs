# Architecture du Mod SmartMobs

## 📖 Vue d'ensemble

```
SmartMobs/
├── src/main/java/com/smartmobs/
│   ├── SmartMobs.java (Point d'entrée)
│   ├── ai/ (IA des mobs)
│   ├── behavior/ (Comportements)
│   ├── memory/ (Système de mémoire)
│   ├── adaptation/ (Stratégies d'adaptation)
│   ├── config/ (Configuration)
│   ├── commands/ (Commandes)
│   ├── events/ (Gestionnaires d'événements)
│   └── util/ (Utilitaires)
└── src/main/resources/
    └── META-INF/
        └── mods.toml (Métadonnées NeoForge)
```

## 🔄 Flux de Données

```
Joueur Action
    ↓
PlayerEventHandler.java
    ↓
LearningSystem.java (Détecte et enregistre)
    ↓
PlayerBehaviorMemory.java (Stocke les stats)
    ↓
AdaptationManager.java (Évalue l'adaptation)
    ↓
MobEventHandler.java (Applique aux mobs)
    ↓
AdaptationStrategy (Exécute la stratégie)
    ↓
Mob AI (Changement visible)
```

## 📦 Composants Clés

### 1. **SmartMobs.java**
- Point d'entrée du mod
- Initialisation des gestionnaires d'événements
- Charger la configuration

### 2. **LearningSystem.java**
- Détecte les actions du joueur
- Enregistre dans PlayerBehaviorMemory
- Gère les cooldowns

### 3. **PlayerBehaviorMemory.java**
- Stocke les statistiques du joueur
  - `meleeUsage` : Pourcentage d'attaques mêlée
  - `rangedUsage` : Pourcentage d'attaques distance
  - `buildingUsage` : Pourcentage de construction
  - `highGroundUsage` : Fréquence des combats en hauteur

### 4. **AdaptationManager.java**
- Évalue quand un mob doit s'adapter
- Choisit la stratégie appropriée
- Gère les niveaux d'adaptation (0-3)

### 5. **AdaptationStrategy.java**
- Interface pour les stratégies
- Implémentations :
  - `AntiRangedStrategy` : Éviter les projectiles
  - `AntiHighGroundStrategy` : Gérer les joueurs en hauteur
  - `ClimbStrategy` : Monter vers la cible
  - etc.

### 6. **MobEventHandler.java**
- Applique les stratégies aux mobs
- Met à jour l'IA chaque tick
- Optimisé pour la performance

## 🎯 Système d'Adaptation

### Niveaux d'Adaptation

**Niveau 0 (Aucune)** :
- Comportement vanilla complet

**Niveau 1 (Légère)** :
- Petits ajustements de distance
- Recherche passive d'abri

**Niveau 2 (Visible)** :
- Changements clairs de stratégie
- Utilisation active du terrain

**Niveau 3 (Avancée)** :
- Comportements complexes
- Adaptation rapide aux changements

### Facteurs d'Activation

Une adaptation s'active quand :
1. Le joueur utilise beaucoup une stratégie (seuil 60%+)
2. Plusieurs tentatives échouées du mob
3. Le cooldown d'adaptation est passé
4. La difficulté le permet

## 💾 Persistance des Données

```
.minecraft/config/
└── smartmobs/
    ├── config.json (Configuration générale)
    └── memory/
        └── [UUID_JOUEUR].json (Données persistantes)
```

**Données sauvegardées par joueur :**
- Statistiques de comportement observé
- Niveau d'adaptation atteint
- Mobs rencontrés
- Date de dernière interaction

## ⚡ Optimisations Performance

### Cooldowns
- Détection d'action : 5 ticks (250ms)
- Décision d'adaptation : 30 secondes
- Évaluation de stratégie : 1 seconde

### Caching
- Mémorisation des calculs de distance
- Cache du pathfinding
- Stockage des stratégies actives

### Nettoyage
- Suppression des données après 7 jours d'inactivité
- Limitation de la taille des fichiers JSON
- Déchargement des données inutilisées

## 🔄 Cycle de Mise à Jour

```
Tick 0 : Joueur agit
         ↓
Tick 20 : Détection et enregistrement
         ↓
Tick 600 : Évaluation d'adaptation (30s)
         ↓
Tick 620 : Application de stratégie
         ↓
Tick 1200 : Sauvegarde de données
```

## 🛡️ Sécurité

- ❌ Pas d'accès direct à l'inventaire du joueur
- ❌ Pas de vision à travers les murs
- ❌ Pas d'augmentation excessive de dégâts
- ✅ Tous les calculs côté serveur
- ✅ Validation des données en entrée
- ✅ Limitation des ressources par joueur

## 🧪 Tests

Unitaires (JUnit) :
- LearningSystemTest.java
- MemoryTest.java
- AdaptationTest.java

Intégration :
- Tous les tests dans `src/test/java/`

## 🧠 Extensibilité

Pour ajouter une nouvelle stratégie :

1. Crée `NewStrategy extends AdaptationStrategy`
2. Implémente `shouldApply()` et `execute()`
3. Enregistre dans `AdaptationManager.registerStrategy()`

Exemple minimal : 20 lignes de code !
