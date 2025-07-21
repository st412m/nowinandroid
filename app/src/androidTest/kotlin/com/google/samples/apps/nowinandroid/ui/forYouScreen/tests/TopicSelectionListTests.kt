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

package com.google.samples.apps.nowinandroid.ui.forYouScreen.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.uiautomator.UiSelector
import com.google.samples.apps.nowinandroid.MainActivity
import com.google.samples.apps.nowinandroid.core.designsystem.LazyListLengthSemantics
import com.google.samples.apps.nowinandroid.ui.TopicNames
import com.google.samples.apps.nowinandroid.ui.forYouScreen.TopicSelectionList
import com.google.samples.apps.nowinandroid.ui.tools.checks
import com.google.samples.apps.nowinandroid.ui.tools.interceptors.FailOnlyScreenshotStepInterceptor
import com.google.samples.apps.nowinandroid.ui.tools.interceptors.SuccessFinaleScreenshotTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.step.ScreenshotStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.video.VideoRecordingInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import org.junit.Rule
import org.junit.Test

class TopicSelectionListTests : TestCase(
    Kaspresso.Builder.withComposeSupport().apply {
        Kaspresso.Builder.withForcedAllureSupport()
        stepWatcherInterceptors.removeIf {
            it is ScreenshotStepInterceptor
        }
        stepWatcherInterceptors.addAll(
            listOf(
                AllureMapperStepInterceptor(),
                FailOnlyScreenshotStepInterceptor(screenshots),
            ),
        )
        testRunWatcherInterceptors.addAll(
            listOf(
                SuccessFinaleScreenshotTestInterceptor(screenshots),
                VideoRecordingInterceptor(videos),
                DumpLogcatTestInterceptor(logcatDumper),
            ),
        )
    },
) {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    val topicSelectionList = TopicSelectionList(composeTestRule)

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true,
    )

    @OptIn(ExperimentalTestApi::class)

    @Test
    fun topicSelectionItemsDisplay() {
        composeTestRule.waitForIdle()

        run {
            device.uiDevice.findObject(
                UiSelector().text("Allow"),
            ).click()
            val itemCount = composeTestRule
                .onNode(hasTestTag("forYou:topicSelection"))
                .fetchSemanticsNode()
                .config[LazyListLengthSemantics]
            val expectedTopics = TopicNames.entries.toTypedArray()
            topicSelectionList {
                for (index in 0 until itemCount) {
                    val expectedText = expectedTopics.getOrNull(index)?.expectedText ?: continue
                    topicSelectionsItems(index) {
                        checks {
                            isDisplayed(icon)
                            isDisplayed(text)
                            checkText(text, expectedText)
                            isDisplayed(clearButton)
                            doesNotExist(checkedButton)
                        }
                    }
                }
            }
        }
    }
}