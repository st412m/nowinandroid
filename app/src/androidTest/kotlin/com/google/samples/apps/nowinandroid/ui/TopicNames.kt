/*
 * Copyright 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.nowinandroid.ui

enum class TopicNames(val id: String, val expectedText: String) {
    HEADLINES("1", "Headlines"),
    UI("2", "UI"),
    COMPOSE("3", "Compose"),
    ARCHITECTURE("4", "Architecture"),
    ANDROID_STUDIO_TOOLS("5", "Android Studio & Tools"),
    TESTING("6", "Testing"),
    PERFORMANCE("7", "Performance"),
    NEW_APIS_LIBRARIES("8", "New APIs & Libraries"),
    DATA_STORAGE("9", "Data Storage"),
    KOTLIN("10", "Kotlin"),
    PRIVACY_SECURITY("11", "Privacy & Security"),
    PUBLISHING_DISTRIBUTION("12", "Publishing & Distribution"),
    PLATFORM_RELEASES("13", "Platform & Releases"),
    ACCESSIBILITY("14", "Accessibility"),
    ANDROID_AUTO("15", "Android Auto"),
    ANDROID_TV("16", "Android TV"),
    GAMES("17", "Games"),
    CAMERA_MEDIA("18", "Camera & Media"),
    WEAR_OS("19", "Wear OS");

    fun testTag() = "topicTag:$id"
}