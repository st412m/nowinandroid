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

package com.google.samples.apps.nowinandroid.ui.homework14

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.uiautomator.UiSelector
import com.google.samples.apps.nowinandroid.MainActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class MainScreenTest : TestCase(
    Kaspresso.Builder.withComposeSupport(),
) {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    val mainScreen = MainScreen(composeTestRule)
    val nowInAndroid = NowInAndroid(composeTestRule)
    val settingsDialog = SettingsDialog(composeTestRule)

    @Test
    fun checkButtonTest() {
        run {
            step("Нажимаем на кнопку 'Allow' в системном диалоге разрешений") {
                device.uiDevice.findObject(
                    UiSelector().text("Allow"),
                ).click()
            }
            step("Отмечаем элементы") {
                mainScreen {
                    headlinesIcon.perform {
                        assertContentDescriptionEquals("Headlines")
                        composeTestRule.waitForIdle()
                        performClick()
                    }
                    uiIcon.perform {
                        assertContentDescriptionEquals("UI")
                        composeTestRule.waitForIdle()
                        performClick()
                    }
                    composeIcon.perform {
                        assertContentDescriptionEquals("Compose")
                        composeTestRule.waitForIdle()
                        performClick()
                    }
                }
            }
            step("check button 'done' and click") {
                mainScreen {
                    doneButton.assertTextContains("Done")
                    composeTestRule.waitForIdle()
                    performClick()
                }
            }
            step("Проверяем отображение иконки 'Search'") {
                nowInAndroid {
                    searchIcon.assertIsDisplayed()
                }
            }
            step("Проверяем отображение иконки 'Settings' и кликаем на нее") {
                nowInAndroid {
                    settingsIcon.assertIsDisplayed()
                    settingsIcon.performClick()
                }
            }
            step(
                "Проверяем наличие кнопки 'Privacy Police' кликаем на нее, потом возвращаемся",
            ) {
                settingsDialog {
                    privacyPolicyButton.assertIsDisplayed()
                    privacyPolicyButton.performClick()
                    composeTestRule.waitForIdle()
                    Thread.sleep(5000)
                    device.uiDevice.pressBack()
                }
            }
            step("Проверяем наличие кнопки 'Brand Guide' кликаем на нее, потом возвращаемся") {
                settingsDialog {
                    brandGuideButton.assertIsDisplayed()
                    brandGuideButton.performClick()
                    composeTestRule.waitForIdle()
                    Thread.sleep(5000)
                    device.uiDevice.pressBack()
                }
            }
            step("Проверяем наличие кнопки 'feedback' кликаем на нее, потом возвращаемся") {
                settingsDialog {
                    feedbackButton.assertIsDisplayed()
                    feedbackButton.performClick()
                    composeTestRule.waitForIdle()
                    Thread.sleep(5000)
                    device.uiDevice.pressBack()
                }
            }
            step("Проверяем наличие кнопки 'licenses' кликаем на нее, потом возвращаемся") {
                settingsDialog {
                    licensesButton.assertIsDisplayed()
                    licensesButton.performClick()
                    composeTestRule.waitForIdle()
                    Thread.sleep(5000)
                    device.uiDevice.pressBack()
                }
            }
            step("Проверяем наличие кнопки 'ок' и выходим из меню через клик на нее") {
                settingsDialog {
                    okButton.assertIsDisplayed()
                    okButton.performClick()
                    composeTestRule.waitForIdle()
                    Thread.sleep(5000)
                }
            }
        }
    }
}
