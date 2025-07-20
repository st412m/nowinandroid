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
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.assertion.NodeAssertions

class StepsExecutor(private val testContext: TestContext<*>) {

    private var nextStepName: String = ""

    private fun execute(step: String, actions: (StepInfo) -> Unit) {
        val stepName = nextStepName.ifBlank { step }
        testContext.step(step, actions)
        nextStepName = ""
    }

    fun setNextStepName(name: String) {
        nextStepName = name
    }

    // Actions start
    fun click(step: String, item: NodeActions) {
        execute(step) {
            item.performClick()
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
// Assertions end

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