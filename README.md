# Shadow Modifiers Demo

Android Compose shadow effects demonstration using the latest shadow modifiers.

## Features

- **Drop Shadows**: CSS-style shadows with blur, offset, and spread control
- **Inner Shadows**: Inward shadows for depth and pressed states
- **Material Shadows**: Multi-layer realistic lighting effects
- **Colored Shadows**: Brand-specific colored shadow effects
- **Glow Effects**: Radial glow and highlight effects
- **Neumorphic Design**: Soft UI with light and dark shadows
- **Interactive Buttons**: Dynamic shadow transitions on press

## Shadow APIs

### dropShadow()
```kotlin
Modifier.dropShadow(
    shape = RoundedCornerShape(8.dp),
    color = Color.Black.copy(alpha = 0.25f),
    blur = 4.dp,
    offsetX = 2.dp,
    offsetY = 4.dp,
    spread = 1.dp
)
```

### innerShadow()
```kotlin
Modifier.innerShadow(
    shape = RoundedCornerShape(12.dp),
    color = Color.Gray.copy(alpha = 0.3f),
    blur = 6.dp
)
```

## Tech Stack

- **Compose BOM**: 2025.09.00
- **Kotlin**: 2.2.20
- **Target SDK**: 36
- **Min SDK**: 28

## Project Structure

```
app/src/main/java/com/vamsi/shadowmodifiers/
├── MainActivity.kt                          # Main activity
├── navigation/
│   └── ShadowNavigation.kt                 # Navigation setup
├── shadows/
│   └── StableShadowUtils.kt                # Custom shadow utilities
└── ui/
    ├── screens/
    │   └── ModifierShadowShowcase.kt       # Shadow showcase screen
    └── theme/
        ├── Color.kt                        # Color definitions
        ├── Theme.kt                        # App theme
        └── Type.kt                         # Typography
```

## Setup

1. Open in Android Studio
2. Sync project
3. Run on device or emulator (API 28+)
