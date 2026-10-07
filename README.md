# SmartMobs - Adaptive Mob AI for Minecraft 1.21.1

**SmartMobs** est un mod Minecraft qui donne aux mobs une IA adaptative. Les mobs apprennent progressivement du comportement des joueurs et adaptent leur stratégie en conséquence.

## 🎯 Fonctionnalités Principales

- **Apprentissage Progressif** : Les mobs observent les actions du joueur et adaptent leur comportement
- **Adaptations Visibles** : Les stratégies sont visibles (recherche d'abri, changement de distance, etc.)
- **Système de Mémoire** : Persistance des données entre les sessions
- **Niveaux d'Adaptation** : 4 niveaux (LOW, NORMAL, SMART, VERY_SMART)
- **Multijoueur Compatible** : Fonctionne sur serveurs dédiés
- **Équilibré** : Pas de triche, les mobs restent justes

## 📦 Installation

### Prérequis
- Minecraft 1.21.1
- NeoForge 1.21.1

### Étapes
1. Télécharge le JAR depuis les [Releases](https://github.com/Lkthuman/SmartMobs/releases)
2. Place le fichier JAR dans ton dossier `mods`
3. Lance Minecraft avec NeoForge
4. Profite ! 🎮

## 🎮 Comment Tester

Vois [TESTING_GUIDE.md](TESTING_GUIDE.md) pour un guide complet de test.

## ⚙️ Configuration

La configuration se trouve dans : `config/smartmobs.json`

### Paramètres Principaux
```json
{
  "enabled": true,
  "learningSpeed": 0.5,
  "maxAdaptationLevel": 3,
  "debugMode": false
}
```

## 💻 Commandes

- `/smartmobs debug` - Affiche les infos de debug
- `/smartmobs stats` - Affiche les statistiques
- `/smartmobs reload` - Recharge la config
- `/smartmobs reset` - Réinitialise les données

## 📚 Documentation

- [Guide de Test](TESTING_GUIDE.md) - Comment tester le mod
- [Architecture](ARCHITECTURE.md) - Structure du code
- [Changelog](CHANGELOG.md) - Histoire des versions

## 🛠️ Développement

Pour compiler le mod :

```bash
./gradlew build
```

Le JAR final sera dans `build/libs/`

## 📄 Licence

MIT License - Voir [LICENSE](LICENSE)

## 👤 Auteur

Développé pour offrir une expérience Minecraft plus immersive.
