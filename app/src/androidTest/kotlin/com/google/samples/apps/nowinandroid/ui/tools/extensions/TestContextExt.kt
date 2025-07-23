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

package com.google.samples.apps.nowinandroid.ui.tools.extensions

import com.google.samples.apps.nowinandroid.ui.tools.ActionSteps
import com.google.samples.apps.nowinandroid.ui.tools.CheckSteps
import com.google.samples.apps.nowinandroid.ui.tools.StepsExecutor
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

// Отдельные мапы для ActionSteps и CheckSteps чтобы небыло конфликтов
private val actionStepsMap = mutableMapOf<String, ActionSteps>()
private val checkStepsMap = mutableMapOf<String, CheckSteps>()

val TestContext<*>.actions: ActionSteps
    get() {
        val key = "${ActionSteps::class.java.name}_${this.hashCode()}"
        return actionStepsMap.getOrPut(key) {
            ActionSteps(StepsExecutor(this))
        }
    }

val TestContext<*>.checks: CheckSteps
    get() {
        val key = "${CheckSteps::class.java.name}_${this.hashCode()}"
        return checkStepsMap.getOrPut(key) {
            CheckSteps(StepsExecutor(this))
        }
    }
