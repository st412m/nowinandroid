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

package com.google.samples.apps.nowinandroid.ui.homework16

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.uiautomator.UiSelector
import com.google.samples.apps.nowinandroid.MainActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import org.junit.Rule
import org.junit.Test

class MainScreenTest3 : TestCase(
    Kaspresso.Builder.withComposeSupport(),
) {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    val topicSelectionScreen = TopicSelectionScreen(composeTestRule)
    val mainScreen = MainScreen3(composeTestRule)
    val newsFeedScreen = NewsFeedScreen(composeTestRule)

    @get: Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true,
    )

    @OptIn(ExperimentalTestApi::class)

    @Test
    fun checkTopicSelection() {
        run {
            step("Нажимаем на кнопку 'Allow' в системном диалоге разрешений") {
                device.uiDevice.findObject(
                    UiSelector().text("Allow"),
                ).click()
            }
//            step("Проверяем отображение элементов 'Topic Selection'") {
//                val itemCount = composeTestRule
//                    .onNode(hasTestTag("forYou:topicSelection"))
//                    .fetchSemanticsNode()
//                    .config[LazyListLengthSemantics]
//                topicSelectionScreen {
//                    for (index in 0 until itemCount) {
//                        list.childAt<TopicSelectionsItems>(index) {
//                            step("Проверяем начальное состояние элемента") {
//                                icon.assertIsDisplayed()
//                                text.assertIsDisplayed()
//                                clearButton.assertIsDisplayed()
//                                checkedButton.assertDoesNotExist()
//                            }
//
//                            step("Клик на clearButton для выбора темы") {
//                                clearButton.performClick()
//                                checkedButton.assertIsDisplayed()
//                                clearButton.assertDoesNotExist()
//                            }
//
//                            step("Клик на checkedButton для отмены выбора") {
//                                checkedButton.performClick()
//                                clearButton.assertIsDisplayed()
//                                checkedButton.assertDoesNotExist()
//                            }
//                        }
//                    }
//                }
//            }
            step("Кликаем в элемент 'Topic Selection' с индексом 0") {
                topicSelectionScreen {
                    list.childAt<TopicSelectionsItems>(0) {
                        clearButton.performClick()
                        composeTestRule.waitForIdle()
                    }
                }
                mainScreen {
                    doneButton.performClick()
                }
            }
            step("Находим карточку с индексом 0") {
                newsFeedScreen {
                    list.childAt<NewsFeedScreenItems>(0) {
                        cardImage.assertIsDisplayed()
                    }
                }
            }
        }
    }
}




