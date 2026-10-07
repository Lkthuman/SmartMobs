# Guide d'Utilisation - Smart Mobs

## 🎮 Premier Lancement

### Installation

1. **Téléchargez le mod**
   - `smartmobs-1.0.0.jar` depuis les releases

2. **Placez dans le dossier mods**
   ```
   .minecraft/mods/smartmobs-1.0.0.jar
   ```

3. **Lancez Minecraft avec NeoForge 21.1.172**

4. **Le mod se charge automatiquement** ✅

### Configuration Initiale

Après le premier lancement, un fichier de configuration est créé :
```
.minecraft/config/smartmobs-common.toml
```

**Configuration par défaut (recommandée)** :
```toml
enabled = true
learningSpeed = 3
maxAdaptationLevel = 3
observationRange = 32
adaptationDifficulty = 3
debugMode = false
```

Pour activer le mode debug :
```toml
debugMode = true
```

## 🧪 Tester le Mod

### Test 1 : Observation Basique

**Objectif** : Voir le système d'observation fonctionner

1. Créez un nouveau monde en Survival
2. Trouvez un Zombie à proximité
3. Attaquez-le quelques fois avec une épée
4. Reculez et observez-le (10-15 secondes)
5. Vous devriez voir des particules autour du Zombie

✅ **Succès** : Particules visibles = Observation active

### Test 2 : Adaptation au Combat en Hauteur

**Objectif** : Voir le Golem apprendre à monter

**Étapes** :

1. Trouvez ou créez un Iron Golem
   ```
   /summon iron_golem ~ ~1 ~
   ```

2. Construisez une petite tour (3-4 blocs)
   ```
   /setblock ~ ~1 ~ stone
   /setblock ~ ~2 ~ stone
   /setblock ~ ~3 ~ stone
   ```

3. Grimpez au sommet et attaquez le Golem avec une épée

4. Observez pendant 30-60 secondes

**Comportement attendu** :
- ⏰ 0-10s : Le Golem tente de vous atteindre normalement
- ✨ 10-20s : Particules autour du Golem (adaptation)
- 🧱 20-40s : Le Golem cherche des blocs
- 📈 40-60s : Le Golem monte progressivement
- 👊 60+s : Le Golem vous atteint et attaque

✅ **Succès** : Particules → Recherche de bloc → Montée progressive

### Test 3 : Adaptation au Combat à l'Arc

**Objectif** : Voir le Skeleton chercher de la couverture

**Étapes** :

1. Trouvez ou créez un Skeleton à distance
   ```
   /summon skeleton ~ ~1 ~
   ```

2. Placez-vous à 30-40 blocs de distance

3. Tirez 10-15 flèches sur le Skeleton (avec un arc)

4. Continuez à tirer pendant 30-45 secondes

**Comportement attendu** :
- ⏰ 0-10s : Le Skeleton fonce droit vers vous
- 🔍 10-20s : Particules de recherche (cherche couverture)
- 🛡️ 20-30s : Le Skeleton zigzague pour éviter
- 🏃 30-45s : Le Skeleton se rapproche en cherchant un chemin

✅ **Succès** : Comportement change visiblement

### Test 4 : Mode Debug

**Objectif** : Vérifier les statistiques du mod

**Étapes** :

1. Activez le mode debug dans la config :
   ```toml
   debugMode = true
   ```

2. Rechargez le monde

3. Positionnez-vous près d'un mob

4. Tapez la commande :
   ```
   /smartmobs debug
   ```

**Résultat attendu** :
```
=== Smart Mobs Debug ===
Mob: minecraft:zombie
Distance: 5 blocks
Adaptation Level: 1/3
Learning Progress: 45%
Strategy: SEEK_COVER
```

✅ **Succès** : Affichage des statistiques

### Test 5 : Statistiques du Joueur

**Étapes** :

1. Tapez la commande :
   ```
   /smartmobs stats
   ```

**Résultat attendu** :
```
=== Your Behavior Stats ===
Melee: 45.3%
Ranged: 32.1%
Building: 15.2%
High Ground: 7.4%
```

Ces statistiques changent en fonction de vos actions !

## 🔧 Configuration Avancée

### Réduire la Difficulté

Si les mobs s'adaptent trop vite :

```toml
learningSpeed = 1          # Plus lent
forgetSpeed = 5            # Oublient plus vite
maxAdaptationLevel = 1     # Moins de niveaux
adaptationChance = 40      # Moins de chances d'adaptation
```

### Augmenter la Difficulté

Si vous voulez un défi :

```toml
learningSpeed = 8          # Très rapide
forgetSpeed = 1            # N'oublient rien
maxAdaptationLevel = 3     # Tous les niveaux
adaptationDifficulty = 5   # Maximum difficile
adaptationChance = 100     # Toujours adaptent
```

### Portée d'Observation

```toml
observationRange = 16      # Les mobs observent que de près
observationRange = 64      # Les mobs observent de loin
```

### Niveaux d'Intelligence (Future)

⏳ **À venir** : Configuration par niveau de difficulté Minecraft

## 📊 Comprendre les Statistiques

### Adaptation Level

```
Niveau 0 : Aucune adaptation
           Les mobs se comportent normalement
           
Niveau 1 : Adaptation légère
           Les mobs commencent à chercher de la couverture
           Certaines stratégies sont disponibles
           
Niveau 2 : Adaptation visible
           Les mobs utilisent des tactiques clairement différentes
           Plus de stratégies disponibles
           
Niveau 3 : Adaptation avancée
           Les mobs utilisent des stratégies complexes
           Toutes les stratégies sont disponibles
```

### Learning Progress

```
0-25%   : Phase d'observation
25-50%  : Les mobs commencent à comprendre
50-75%  : Adaptation imminente
75-100% : **ADAPTATION** ✨
          Nouvelle stratégie déverrouillée
          Particules : happy_villager
          Son : villager_yes
```

### Behavior Stats

```
Melee     : %age d'attaques au mêlée
Ranged    : %age d'attaques à distance
Building  : %age de blocs placés
HighGround: %age d'attaques depuis la hauteur
```

Le mob observe ces statistiques et adapte ses stratégies !

## 🎨 Observer les Animations

### Particules d'Observation

**MYCELIUM** (particules vertes)
- Signifie : Le mob cherche quelque chose
- Durée : 40 ticks tous les 20 ticks

### Particules d'Adaptation

**HAPPY_VILLAGER** (particules vertes joyeuses)
- Signifie : Le mob a découvert une nouvelle stratégie
- Effet : Burst soudain autour du mob
- Bruit : Son de villager heureux

### Particules d'Apprentissage

**ENCHANT** (particules bleues magiques)
- Signifie : Le mob réfléchit intensément
- Durée : Animation en spirale
- Contexte : Avant une nouvelle stratégie

### Particules de Bloc

**BLOCK** (poussière de cobblestone)
- Signifie : Le mob place un bloc
- Contexte : Stratégie BUILD_UP (Golem)
- Son : Grindstone use

## ⚙️ Multijoueur (Serveur)

### Installation Serveur

1. Placez le mod dans `mods/` du serveur
2. Utilisez NeoForge Server
3. Redémarrez le serveur

### Comportement

- Chaque joueur a sa propre mémoire de mob
- Les mobs ne se confondent pas entre joueurs
- Les stratégies sont par joueur
- La config est commune à tous

### Exemple

```
Joueur A : Utilise un arc → Mob cherche couverture
Joueur B : Utilise épée  → Même mob vous attaque au mêlée
Joueur C : Nouvelle zone → Mob recommence à l'observer
```

## 🐛 Dépannage

### Problème : Les mobs ne s'adaptent pas

**Solutions possibles** :

1. Vérifiez que `enabled = true`
   ```toml
   enabled = true
   ```

2. Vérifiez que `maxAdaptationLevel > 0`
   ```toml
   maxAdaptationLevel = 3
   ```

3. Observez le même mob pendant 30+ secondes
   - Le système a besoin de temps pour observer

4. Vérifiez que le mob est supporté
   - Zombie, Skeleton, Creeper, Spider, etc.

5. Activez le mode debug
   ```
   /smartmobs debug
   ```
   - Vous devriez voir une progression d'apprentissage

### Problème : Les particules ne s'affichent pas

**Solutions** :

1. Vérifiez options graphiques → Particules
   - Mettre sur "Tous" ou "Beaucoup"

2. Augmentez le rendu des particules :
   - Options → Vidéo → Particules : Beaucoup

3. Réduisez la distance :
   - Regardez un mob de près
   - Les particules ne s'affichent que de près

### Problème : Lag ou fps bas

**Solutions** :

1. Réduisez la portée d'observation :
   ```toml
   observationRange = 16  # Au lieu de 32
   ```

2. Ralentissez l'apprentissage :
   ```toml
   learningSpeed = 1  # Au lieu de 3
   ```

3. Désactivez le mode debug :
   ```toml
   debugMode = false
   ```

## 🎬 Enregistrer une Vidéo

Pour démontrer le mod :

1. Activez le mode debug
2. Créez une situation intéressante (hauteur, arc, etc.)
3. Laissez le mob s'adapter pendant 1-2 minutes
4. Enregistrez avec OBS ou similaire
5. Ralentissez la vidéo de 0.5x pour voir les détails

## 📞 Obtenir de l'Aide

- Consultez le `README_COMPLET.md`
- Vérifiez le `CHANGELOG.md`
- Créez une issue sur GitHub

---

**Profitez d'une expérience Minecraft plus dynamique !**
