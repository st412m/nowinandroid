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

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.element.ComposeScreen

abstract class NamedComposeScreen<out T : ComposeScreen<T>> : ComposeScreen<T>{
    abstract val screenName: String
    private val nameHierarchy by lazy { NameHierarchy(screenName) }

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider?,
        ) : super(semanticsProvider)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider?,
        viewBuilderAction: ViewBuilder.() -> Unit,
        ) : super(semanticsProvider, viewBuilderAction)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider?,
        nodeMatcher: NodeMatcher,
        ) : super(semanticsProvider, nodeMatcher)

    fun withParent(elementName: String) = nameHierarchy.withParent(elementName)
}