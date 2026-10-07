# Smart Mobs

Minecraft Java 1.21.1 mod (NeoForge 21.1.x, Java 21). Mobs progressively adapt to how each player fights.

## Features
- Per-player memory (normalized 0..1, persisted, slow decay) for melee, ranged, building, high ground and easy kills.
- Adaptation levels 0-3 (0 = vanilla), scaled by config.
- Strategies: seek cover, flank, alternate path, reach high ground, cautious retreat.
- Mobs: zombie, skeleton, creeper, spider, enderman, drowned, husk, stray, pillager, vindicator, witch (extensible via `MobProfiles`).
- No cheating: mobs only react to their current target and line of sight; no damage/health boosts.
- Throttled checks (every 20 ticks, cooldowns) for low overhead.

## Commands (OP level 2)
- `/smartmobs stats` - show your observed behavior and levels
- `/smartmobs debug` - show debug flag and stats
- `/smartmobs reset` - reset your memory

## Configuration
`config/smartmobs-common.toml`: enabled, learningSpeed, forgettingSpeed, maxAdaptationLevel, affectedMobs, adaptationDifficulty, observationRange, persistentMemory, debug.

## Build
```
./gradlew build
```
Output: `build/libs/smartmobs-1.0.0.jar`

## Status
This code was written without being able to compile it in the authoring environment. Please run the build and report any compile errors. The `reload` command and `persistentMemory` toggle are not implemented yet (config reloads automatically on file change).

## License
MIT
