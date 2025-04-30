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
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull

val elementNames = mutableMapOf<NodeActions, NameHierarchy>()

fun <T: NodeActions> T.setName(nameHierarchy: NameHierarchy): T{
    elementNames[this] = nameHierarchy
    return this
}

fun NodeActions.getName(): NameHierarchy {
    return elementNames[this] ?: throw RuntimeException("Необходимо указать имя")
//    elementNames.getOrDefault(this, NameHierarchy("No label"))
}

fun NodeActions.withParent(elementName: String) = getName().withParent(elementName)

fun NodeActions.getText(): List<String> {
    return this.delegate
        .interaction
        .semanticsNodeInteraction
        .fetchSemanticsNode()
        .config
        .getOrNull(SemanticsProperties.Text)?.map {
            it.text
        } ?: emptyList()
}