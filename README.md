# Custom Solution for Android UI Testing

## Overview

This project presents a custom solution for UI testing of Android applications, built on top of the **Kaspresso** and **Kakao** libraries. The architecture implements the **Domain-Specific Language (DSL)** pattern, enabling readable, maintainable, and efficient automated tests for modern Android applications, including support for **Jetpack Compose**.

## Named Steps

### Core Components

| Component | Purpose | Link |
|-----------|---------|------|
| **ActionSteps** | Performing UI actions (e.g., clicks, swipes) | [ActionSteps.kt](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/ActionSteps.kt) |
| **CheckSteps** | Verifying UI state (e.g., visibility, text) | [CheckSteps.kt](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/CheckSteps.kt) |
| **StepsExecutor** | Central command executor with low-level logic | [StepsExecutor.kt](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/StepsExecutor.kt) |

## Key Features

### 1. DSL Approach for UI Testing

#### Benefits
- **Readability**: Tests are written in a style close to natural language, making them accessible to QA engineers, managers, and other team members.
- **Declarative Syntax**: Code describes *what* to do, not *how*, simplifying test creation.
- **Accessibility**: Understandable even for those without deep technical expertise.

#### Example Usage
```kotlin
actions {
    uiClick("Login")
    scrollTo(someElement)
    swipeVertically(1.0, 10)
}
```

*[Full test example](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tests/TopAppBarTests.kt)*

### 2. Unified and Centralized Logic
- **Single Point of Implementation**: All low-level operations are encapsulated in [StepsExecutor](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/StepsExecutor.kt).
- **Extensibility**: Supports [NodeActions Extensions](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/extensions/NodeActionExt.kt).
- **Isolation**: Tests are independent of low-level UI interaction details.

### 3. Integration with Allure Reports
- **Automatic Logging**: Each step is added to the report with a detailed description.
- **Visualization**: Example reports are available [here](https://github.com/st412m/st412m/blob/main/scr/allure_report_example.png).
- **Flexible Naming**: Supports custom step names for precise reporting.

## Jetpack Compose Support

### List Extensions

Specialized extensions for working with `LazyColumn` and standard lists in Jetpack Compose:

| Extension | Purpose |
|-----------|---------|
| [KLazyListNodeExt](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/extensions/KLazyListNodeExt.kt) | Handling lazy lists |

#### Key Capabilities
- **Indexed Access**: `list.invokeAtIndex<TopicSelectionsListItems>(index = 2) { performClick() }`
- **Predicate-Based Search**: Dynamic element search based on custom conditions.
- **Type Safety**: Use of generics with IDE support.
- **Allure Integration**: Automatic element naming in reports.

#### Report Example
![Topic Selection List Report](https://github.com/st412m/st412m/blob/main/scr/allure_report_topic_selection_list.png)

*[Usage example](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tests/TopicSelectionListTests.kt)*

## Screenshot System

### Custom Interceptors

The project includes two specialized interceptors for automatic screenshot generation:

| Interceptor | Purpose | Link |
|-------------|---------|------|
| **FailOnlyScreenshotStepInterceptor** | Screenshots on step failures | [FailOnlyScreenshotStepInterceptor.kt](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/interceptors/FailOnlyScreenshotStepInterceptor.kt) |
| **SuccessFinaleScreenshotTestInterceptor** | Final test screenshots | [SuccessFinaleScreenshotTestInterceptor.kt](https://github.com/st412m/nowinandroid/blob/main/app/src/androidTest/kotlin/com/google/samples/apps/nowinandroid/ui/tools/interceptors/SuccessFinaleScreenshotTestInterceptor.kt) |

#### Features
- **Resource Efficiency**: Screenshots are created only when needed.
- **Automatic Naming**: Clear tagging scheme for each screenshot type.
- **Allure Integration**: Automatic attachment to reports.

#### Naming Scheme
- **On Failures**: `<testClassName>_step_<ordinal>_failure_<errorType>`
- **Final Screenshots**: `<testName>_success` or `<testName>_failed`

## Gradle Integration

### Automation of Allure Results Management

Custom Gradle tasks in `build.gradle` manage test results:

```kotlin
tasks.register<Delete>("deleteLocalAllureResults") {
    group = "verification"
    description = "Deletes local allure-results directory"
    
    val targetDir = file("build/allure-results")
    delete(targetDir)
}

tasks.register<Exec>("pullAllureResults") {
    group = "verification"
    description = "Pulls allure-results from device to app/build"
    
    dependsOn(tasks.named("deleteLocalAllureResults"))
    
    val adbCommand = listOf("adb", "pull", "/sdcard/Documents/allure-results", "build")
    commandLine = adbCommand
    isIgnoreExitValue = true
}

tasks.register<Exec>("clearDeviceAllureResults") {
    group = "verification"
    description = "Clears allure-results directory on Android device"
    
    val adbCommand = listOf("adb", "shell", "rm", "-rf", "/sdcard/Documents/allure-results")
    commandLine = adbCommand
    isIgnoreExitValue = true
}

tasks.withType<DeviceProviderInstrumentTestTask>().configureEach {
    if (name.lowercase().startsWith("connected") && name.lowercase().endsWith("androidtest")) {
        dependsOn(tasks.named("clearDeviceAllureResults"))
        finalizedBy(tasks.named("pullAllureResults"))
    }
}
```

#### Task Lifecycle
1. **Device Cleanup** → 2. **Test Execution** → 3. **Results Retrieval**

### Available Tasks

| Task | Description |
|------|-------------|
| `deleteLocalAllureResults` | Deletes local test results |
| `pullAllureResults` | Copies results from device |
| `clearDeviceAllureResults` | Clears results on device |

## Benefits of the Approach

### For Developers
- **Low Entry Barrier**: Simple and intuitive DSL.
- **Fast Development**: Pre-built components and methods.
- **Easy Debugging**: Detailed Allure reports with screenshots.

### For Maintenance
- **Modularity**: Clear separation of responsibilities.
- **Extensibility**: Easy addition of new actions.
- **Centralization**: Single point for logic modifications.

### For CI/CD
- **Automation**: Full integration with pipelines.
- **Resilience**: Error handling and recovery mechanisms.
- **Monitoring**: Comprehensive reports for analysis.

## Applicability

This solution is particularly effective for:
- **Large Projects** with extensive UI testing needs.
- **Development Teams** with varying expertise levels.
- **CI/CD Pipelines** requiring detailed reporting.
- **Jetpack Compose Projects** with dynamic content.

## Conclusion

This implementation provides:
- ✅ **Readable and Maintainable** code.
- ✅ **Unified and Centralized** logic.
- ✅ **Integration with Modern Reporting Tools**.
- ✅ **Flexible and Extensible** architecture.
- ✅ **Efficient Team Collaboration**.

Built with Android development best practices, this solution can be adapted to meet the specific needs of any project.
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
