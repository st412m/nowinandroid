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

import android.content.res.Resources.NotFoundException
import android.util.Log
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import com.google.samples.apps.nowinandroid.core.designsystem.LazyListLengthSemantics
import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode
import io.github.kakaocup.compose.node.element.list.KListItemNode
import io.github.kakaocup.compose.node.element.list.KListNode
import kotlin.math.min

@OptIn(ExperimentalTestApi::class)
inline fun <reified T : KLazyListItemNode<*>> KLazyListNode.invokeAtIndex(
    targetIndex: Int,
    function: T.() -> Unit
) {
    val lazyList: KLazyListNode = this
    childAt<T>(position = targetIndex) {
        setName(lazyList.getName().withParent("$targetIndex"))
        function()
    }
}

inline fun <reified T : KListItemNode<*>> KListNode.invokeFirstChild(function: T.() -> Unit) {
    val list = this
    firstChild<T> {
        setName(list.withParent("first"))
        function()
    }
}

inline fun <reified T : KListItemNode<*>> KListNode.invokeChildAtMatcher(
    noinline viewBuilderAction: ViewBuilder.() -> Unit,
    description: String,
    function: T.() -> Unit,
) {
    val list = this
    childWith<T>(viewBuilderAction = viewBuilderAction) {
        setName(list.withParent(description))
        function()
    }
}

inline fun <reified T : KListItemNode<*>> KListNode.findByPredicate(
    targetIndex: Int,
    blockName: String,
    limiter: Int,
    predicate: T.() -> Boolean,
): T {
    val list = this
    var findBlockCounter = 0
    val max = min(limiter, getSize())
    for (i in 0 until max) {
        childAt<T>(i) {
            if (predicate()) {
                if (findBlockCounter == targetIndex) {
                    setName(list.getName().withParent("$targetIndex's block of $blockName"))
                    return this
                }
                findBlockCounter++
            }
        }
    }
    throw NotFoundException("Not found block with $targetIndex index of $blockName")
}

inline fun <reified T : KListItemNode<*>> KListNode.invokeByPredicate(
    targetIndex: Int,
    blockName: String,
    limiter: Int,
    predicate: T.() -> Boolean,
    function: T.() -> Unit,
) {
    val list = this
    var findBlockCounter = 0
    val max = min(limiter, getSize())
    for (i in 0 until max) {
        childAt<T>(i) {
            Log.d(
                "KASPRESSO",
                "Проход №$i, ${getText()}, targetIndex = $targetIndex, findBlockCounter = $findBlockCounter",
            )
            if (predicate()) {
                Log.d("KASPRESSO", "Предикат сработал")
                if (findBlockCounter == targetIndex) {
                    Log.d("KASPRESSO", "Каунтер сработал")
                    setName(list.getName().withParent("$targetIndex's block of $blockName"))
                    function()
                    return
                }
                findBlockCounter++
            }
        }
    }
    throw NotFoundException("Not found block with $targetIndex index of $blockName")
}

fun NodeActions.getSize(): Int {
    return this.delegate
        .interaction
        .semanticsNodeInteraction
        .fetchSemanticsNode()
        .config
        .getOrNull(LazyListLengthSemantics) ?: 0
}