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

package com.google.samples.apps.nowinandroid.ui.homework16

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.google.samples.apps.nowinandroid.core.designsystem.C
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode

class TopicSelectionsItems(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<TopicSelectionsItems>(semanticsNode, semanticsProvider) {
    val icon = child<KNode> {
        hasTestTag(C.TOPIC_SELECTION_ICON)
    }
    val text = child<KNode> {
        hasTestTag(C.TOPIC_SELECTION_TEXT)
    }
    val clearButton = child<KNode> {
        hasTestTag(C.TOPIC_SELECTION_CLEAR_BUTTON)
    }
    val checkedButton = child<KNode> {
        hasTestTag(C.TOPIC_SELECTION_CHECKED_BUTTON)
    }

}
