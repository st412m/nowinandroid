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

package com.google.samples.apps.nowinandroid.ui.forYouScreen

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.google.samples.apps.nowinandroid.core.designsystem.C
import com.google.samples.apps.nowinandroid.ui.TopicNames
import com.google.samples.apps.nowinandroid.ui.tools.extensions.setName
import com.google.samples.apps.nowinandroid.ui.tools.extensions.withParent
import com.kaspersky.components.composesupport.core.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode

class NewsFeedCards(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<NewsFeedCards>(semanticsNode, semanticsProvider) {

    val cardImage by lazy {
        child<KNode> {
            hasTestTag(C.NEWS_RESOURCE_CARD_IMAGE)
        }.setName(withParent("Image"))
    }

    val cardTitle by lazy {
        child<KNode> {
            hasTestTag(C.NEWS_RESOURCE_CARD_TITLE)
        }.setName(withParent("Title"))
    }

    val cardDate by lazy {
        child<KNode> {
            hasTestTag(C.NEWS_RESOURCE_CARD_DATE)
        }.setName(withParent("Date"))
    }

    val cardShortDescription by lazy {
        child<KNode> {
            hasTestTag(C.NEWS_RESOURCE_CARD_SHORT_DESCRIPTION)
        }.setName(withParent("Description"))
    }

    val bookmarkButton by lazy {
        child<KNode> {
            hasTestTag(C.BOOKMARK_BUTTON)
        }.setName(withParent("Bookmark button"))
    }

    val topicTagButton by lazy {
        TopicNames.entries.associateWith { topic ->
            child<KNode> {
                hasTestTag("topicTag:${topic.id}")
            }.setName(withParent("[${topic.expectedText.uppercase()}] tag Button"))
        }
    }

    fun topicTagButton(topic: TopicNames): KNode {
        return child<KNode> {
            hasTestTag(topic.testTag())
        }.setName(withParent("[${topic.expectedText.uppercase()}] tag Button"))
    }
}
