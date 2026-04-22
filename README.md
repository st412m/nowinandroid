# UI Test Automation — Custom Framework for Now in Android

This repository contains UI test automation built on top of the [Now in Android](https://github.com/android/nowinandroid) open source project by Google.

## Testing Approach

A custom DSL-based testing framework built with **Kaspresso** and **Kakao** for modern Android UI testing, including full **Jetpack Compose** support.

Full documentation: [Testing Strategy & Architecture](https://github.com/st412m/st412m/blob/main/testing_approach_readme.md)

### Key Components

| Component | Purpose |
|-----------|---------|
| [ActionSteps](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/ActionSteps.kt) | Performing UI actions (clicks, swipes, scrolls) |
| [CheckSteps](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/CheckSteps.kt) | Verifying UI state (visibility, text, attributes) |
| [StepsExecutor](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/StepsExecutor.kt) | Central command executor with low-level logic |

### DSL Syntax

```kotlin
actions {
    uiClick("Login")
    scrollTo(someElement)
    swipeVertically(1.0, 10)
}
```

### Highlights

- **Human-readable tests** — DSL syntax close to natural language
- **Allure integration** — automatic step logging with screenshots
- **Custom interceptors** — screenshots on failure only, no noise in reports
- **Jetpack Compose support** — lazy list extensions with predicate-based search
- **Gradle automation** — full CI/CD pipeline for test results management

### Test Examples

- [`TopAppBarTests.kt`](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tests/TopAppBarTests.kt)
- [`TopicSelectionListTests.kt`](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tests/TopicSelectionListTests.kt)

---

<details>
<summary>📱 Original Now in Android README (Google)</summary>

![Now in Android](docs/images/nia-splash.jpg "Now in Android")

<a href="https://play.google.com/store/apps/details?id=com.google.samples.apps.nowinandroid"><img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" height="70"></a>

Now in Android App
==================

**Learn how this app was designed and built in the [design case study](https://goo.gle/nia-figma), [architecture learning journey](docs/ArchitectureLearningJourney.md) and [modularization learning journey](docs/ModularizationLearningJourney.md).**

This is the repository for the [Now in Android](https://developer.android.com/series/now-in-android)
app. It is a **work in progress** 🚧.

**Now in Android** is a fully functional Android app built entirely with Kotlin and Jetpack Compose. It
follows Android design and development best practices and is intended to be a useful reference
for developers. As a running app, it's intended to help developers keep up-to-date with the world
of Android development by providing regular news updates.

The app is currently in development. The `prodRelease` variant is [available on the Play Store](https://play.google.com/store/apps/details?id=com.google.samples.apps.nowinandroid).

# Features

**Now in Android** displays content from the
[Now in Android](https://developer.android.com/series/now-in-android) series. Users can browse for
links to recent videos, articles and other content. Users can also follow topics they are interested
in, and be notified when new content is published which matches interests they are following.

## Screenshots

![Screenshot showing For You screen, Interests screen and Topic detail screen](docs/images/screenshots.png "Screenshot showing For You screen, Interests screen and Topic detail screen")

# Development Environment

**Now in Android** uses the Gradle build system and can be imported directly into Android Studio (make sure you are using the latest stable version available [here](https://developer.android.com/studio)).

Change the run configuration to `app`.

# Architecture

The **Now in Android** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture)
and is described in detail in the
[architecture learning journey](docs/ArchitectureLearningJourney.md).

# Modularization

The **Now in Android** app has been fully modularized and you can find the detailed guidance and
description of the modularization strategy used in
[modularization learning journey](docs/ModularizationLearningJourney.md).

# Testing

To facilitate testing of components, **Now in Android** uses dependency injection with
[Hilt](https://developer.android.com/training/dependency-injection/hilt-android).

# License

**Now in Android** is distributed under the terms of the Apache License (Version 2.0). See the
[license](LICENSE) for more information.

</details>


# License

**Now in Android** is distributed under the terms of the Apache License (Version 2.0). See the
[license](LICENSE) for more information.
