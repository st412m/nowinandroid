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

package com.google.samples.apps.nowinandroid.ui.tools

import androidx.lifecycle.AtomicReference
import androidx.test.uiautomator.UiSelector
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.assertion.NodeAssertions

class StepsExecutor(private val testContext: TestContext<*>) {

    private var nextStepName: String = ""

    // Actions start

    fun uiClick(step: String, text: String) {
        execute(step) {
            val uiObject = testContext.device.uiDevice.findObject(
                UiSelector().text(text)
            )
            if (uiObject.exists()) {
                uiObject.click()
            }
        }
    }
    fun click(step: String, item: NodeActions) {
        execute(step) {
            item.performClick()
        }
    }

    fun scrollTo(step: String, item: NodeActions) {
        execute(step) {
            item.performScrollTo()
        }
    }

    fun swipeVertically(step: String, screens: Double, steps: Int, isUp: Boolean = true) {
        execute(step) {
            val displayWidth = testContext.device.uiDevice.displayWidth
            val displayHeight = testContext.device.uiDevice.displayHeight

            val startX = displayWidth / 2
            val startY = (displayHeight * if (isUp) 0.66 else 0.33).toInt()
            val endY = (startY + (displayHeight * if (isUp) -screens else screens)).toInt()

            val clampedEndY = endY.coerceIn(0, displayHeight)

            try {
                testContext.device.uiDevice.swipe(startX, startY, startX, clampedEndY, steps)
            } catch (e: Exception) {
                testContext.testLogger.e("Error with vertical swipe: ${e.message}")
                throw RuntimeException("Vertical swipe failed, cannot continue test.", e)
            }
        }
    }

    fun swipeHorizontally(step: String, screens: Double, steps: Int, isRight: Boolean = true) {
        execute(step) {
            val displayWidth = testContext.device.uiDevice.displayWidth
            val displayHeight = testContext.device.uiDevice.displayHeight

            val startY = displayHeight / 2
            val startX = (displayWidth * if (isRight) 0.66 else 0.33).toInt()
            val endX = (startX + (displayWidth * if (isRight) -screens else screens)).toInt()

            val clampedEndX = endX.coerceIn(0, displayWidth)

            try {
                testContext.device.uiDevice.swipe(startX, startY, clampedEndX, startY, steps)
            } catch (e: Exception) {
                testContext.testLogger.e("Error with horizontal swipe: ${e.message}")
                throw RuntimeException("Horizontal swipe failed, cannot continue test.", e)
            }
        }
    }
// Actions end
// Assertions start
    fun checkText(step: String, item: NodeAssertions, expectedText: String) {
        execute(step) {
            item.assertTextEquals(expectedText)
        }
    }

    fun isDisplayed(step: String, item: NodeAssertions) {
        execute(step) {
            item.assertIsDisplayed()
        }
    }

    fun isClickable(step: String, item: NodeAssertions) {
        execute(step) {
            item.assertHasClickAction()
        }
    }

    fun doesNotExist(step: String, item: NodeAssertions) {
        execute(step) {
            item.assertDoesNotExist()
        }
    }

    fun isEnable(step: String, item: NodeAssertions) {
        execute(step){
            item.assertIsEnabled()
        }
    }

    fun isChecked(step: String, item: NodeAssertions) {
        execute(step){
            item.assertIsOn()
        }
    }

    fun isNotChecked(step: String, item: NodeAssertions) {
        execute(step){
            item.assertIsOff()
        }
    }
// Assertions end

    private fun execute(step: String, actions: (StepInfo) -> Unit) {
        val stepName = nextStepName.ifBlank { step }
        testContext.step(step, actions)
        nextStepName = ""
    }

    fun setNextStepName(name: String) {
        nextStepName = name
    }

    fun <T> extractSemantic(
        step: String,
        item: NodeActions,
        container: AtomicReference<T>,
        extraction: (item: NodeActions) -> T,
    ) {
        execute(step) {
            container.set(extraction(item))
        }
    }
}