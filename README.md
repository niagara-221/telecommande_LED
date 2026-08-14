# Show Image on Double-Tap (APK Android)

Une application Android simple (Kotlin + Jetpack/Material) qui **affiche une image à l'écran
lorsque l'utilisateur fait un double-tap** n'importe où sur l'écran. Un simple tap masque
à nouveau l'image.

## Comportement

- **Double-tap** → l'image apparaît en plein écran.
- **Simple tap** → l'image disparaît (retour au texte d'aide).

## Contenu du projet

```
.
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/showimage/MainActivity.kt
│       └── res/
│           ├── drawable/your_image.xml      ← image affichée (REMPLACE-MOI)
│           ├── layout/activity_main.xml
│           ├── mipmap*/ic_launcher*.xml
│           └── values/{strings,colors,themes}.xml
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── gradlew  (+ gradlew.bat pour Windows)
```

## Utiliser ta propre image

L'image affichée est référencée par `@drawable/your_image`. Pour mettre la tienne :

1. Supprime `app/src/main/res/drawable/your_image.xml`.
2. Copie ton image dans `app/src/main/res/drawable/your_image.png` (ou `.jpg`).
   - Nom de fichier **obligatoire** : `your_image` (avant l'extension).
   - Évite les espaces et majuscules dans le nom.
   - Pour de meilleurs résultats, place plutôt les PNG/JPG dans
     `app/src/main/res/drawable-nodpi/` afin d'éviter le redimensionnement automatique.

C'est tout — aucun changement de code nécessaire.

> Note : sur Android, `@drawable/ton_nom` correspond à n'importe quel fichier
> `drawable/ton_nom.{png,jpg,xml,webp}`. Le nom `your_image` est choisi pour rester clair.

## Compiler l'APK

### Prérequis
- **JDK 17**
- **Android SDK** (compileSdk 34) — fourni avec Android Studio.

### Avec Android Studio (le plus simple)
1. `File → Open` et sélectionne ce dossier.
2. Attends la synchronisation Gradle.
3. `Build → Build Bundle(s) / APK(s) → Build APK(s)`.
4. L'APK se trouve dans `app/build/outputs/apk/debug/app-debug.apk`.

### En ligne de commande

Le binaire `gradle-wrapper.jar` n'est pas inclus dans le dépôt.
Génère-le une fois (avec un JDK installé) :

```bash
# Option A : si tu as Gradle installé (>= 8.7)
gradle wrapper --gradle-version 8.7

# Option B : via Android Studio, ouvre le projet une fois, il créera le wrapper.
```

Puis :

```bash
# Sur Linux/macOS
./gradlew assembleDebug

# Sur Windows
gradlew.bat assembleDebug
```

L'APK de débogage est généré dans :
```
app/build/outputs/apk/debug/app-debug.apk
```

### Installer sur le téléphone

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```
(avec le débogage USB activé sur l'appareil), ou copie l'APK sur le téléphone et ouvre-le.

## Version release signée (optionnel)

```bash
# 1. Générer un keystore (une seule fois)
keytool -genkeypair -v -keystore release.keystore -alias mykey \
  -keyalg RSA -keysize 2048 -validity 10000

# 2. Build release
./gradlew assembleRelease
```

## Versions clés

| Composant | Version |
|-----------|---------|
| Gradle | 8.7 |
| Android Gradle Plugin | 8.5.2 |
| Kotlin | 1.9.24 |
| compileSdk / targetSdk | 34 |
| minSdk | 24 (Android 7.0) |
