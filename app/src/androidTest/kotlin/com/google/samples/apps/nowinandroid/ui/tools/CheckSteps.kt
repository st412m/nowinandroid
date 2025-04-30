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

import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.assertion.NodeAssertions

class CheckSteps(private val stepsExecutor: StepsExecutor): StepsDSL<CheckSteps>() {
    override val self = this

    fun checkText(item: NodeAssertions, expectedText: String){
        stepsExecutor.checkText(
            "Проверяет, что в ноде '${(item as NodeActions).getName()}' установлен текст '$expectedText'", item, expectedText,
        )
    }
    fun isDisplayed(item: NodeAssertions){
        stepsExecutor.isDisplayed(
            "Проверяет, что объект '${(item as NodeActions).getName()}' отображается", item
        )
    }
    fun doesNotExist(item: NodeAssertions){
        stepsExecutor.isDisplayed(
            "Проверяет, что объект '${(item as NodeActions).getName()}' не существует", item
        )
    }
}