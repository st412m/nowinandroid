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

package com.google.samples.apps.nowinandroid.ui.homework25

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.uiautomator.UiSelector
import com.google.samples.apps.nowinandroid.MainActivity
import com.google.samples.apps.nowinandroid.ui.homework16.MainScreen3
import com.google.samples.apps.nowinandroid.ui.homework16.NewsFeedScreen
import com.google.samples.apps.nowinandroid.ui.homework16.TopicSelectionScreen
import com.google.samples.apps.nowinandroid.ui.tools.steps
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.components.composesupport.config.addComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import org.junit.Rule
import org.junit.Test

class NamedMainScreenTest : TestCase(
    Kaspresso.Builder.withForcedAllureSupport().apply {
        addComposeSupport()
    },
) {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    val topicSelectionScreen = TopicSelectionScreen(composeTestRule)
    val newsFeedScreen = NewsFeedScreen(composeTestRule)

    @get: Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true,
    )

    @Test
    fun checkTopicSelection() {
        run {
            device.uiDevice.findObject(
                UiSelector().text("Allow"),
            ).click()
            steps {
                topicSelectionScreen {
                    topicSelectionsItems(1) {
                        isDisplayed(icon)
                        isDisplayed(text)
                        isDisplayed(clearButton)
                        doesNotExist(checkedButton)
                        click(clearButton)
                        isDisplayed(checkedButton)
                        doesNotExist(clearButton)
                    }
                }
                newsFeedScreen {
                    newsFeedScreenItems(0) {
                        isDisplayed(cardImage)
                    }
                }
            }
        }
    }
}