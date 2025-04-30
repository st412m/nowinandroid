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
/*
ЕСЛИ КОГДА-НИБУДЬ ПОНАДОБИТСЯ РАЗДЕЛИТЬ ШАГИ НА ДЕЙСТВИЯ И ПРОВЕРКИ

import io.github.kakaocup.compose.node.action.NodeActions
import java.util.concurrent.atomic.AtomicReference

class ActionSteps(private val stepsExecutor: StepsExecutor): StepsDSL<ActionSteps>() {
    override val self = this

    fun click(item: NodeActions){
        stepsExecutor.click(
            "Нажимает на кнопку '${item.getName()}'", item
        )
    }

    fun <T> extract(
        item: NodeActions,
        container: AtomicReference<T>,
        extraction: (item: NodeActions) -> T,
        ) {
        stepsExecutor.extractSemantic(
            "Получает данные из элемента '${item.getName()}'",
            item,
            container,
            extraction,
            )
    }

    fun nextStep(step: String) = stepsExecutor.setNextStepName(step)
}
 */