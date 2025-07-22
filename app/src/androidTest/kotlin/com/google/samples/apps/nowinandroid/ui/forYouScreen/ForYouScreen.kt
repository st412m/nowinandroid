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

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.google.samples.apps.nowinandroid.core.designsystem.C
import com.google.samples.apps.nowinandroid.core.designsystem.LazyListItemPositionSemantics
import com.google.samples.apps.nowinandroid.core.designsystem.LazyListLengthSemantics
import com.google.samples.apps.nowinandroid.ui.tools.NamedComposeScreen
import com.google.samples.apps.nowinandroid.ui.tools.invokeAtIndex
import com.google.samples.apps.nowinandroid.ui.tools.setName
import com.kaspersky.components.composesupport.core.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode

class ForYouScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    NamedComposeScreen<ForYouScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("forYou:feed") },
    ) {
    override val screenName = "For You Screen"

    val centerAlignedTopAppBar by lazy {
        child<KNode> {
            hasTestTag("niaTopAppBar")
        }.setName(withParent("Top app bar"))
    }

    val decorativeScrollbar by lazy {
        child<KNode> {
            hasTestTag(C.SCROLLBAR)
        }.setName(withParent("Decorative scrollbar"))
    }
    val list by lazy {
        KLazyListNode(
            semanticsProvider = semanticsProvider,
            viewBuilderAction = { hasTestTag(C.NEWS_RESOURCE_CARD) },
            itemTypeBuilder = {
                itemType(::NewsFeedCards)
                itemType(::NewsCardItems)
            },
            positionMatcher = { position ->
                SemanticsMatcher.expectValue(
                    LazyListItemPositionSemantics,
                    position,
                )
            },
            lengthSemanticsPropertyKey = LazyListLengthSemantics,
        ).setName(withParent("Block list"))
    }

    fun newsFeedCards(index: Int, function: NewsFeedCards.() -> Unit) {
        list.invokeAtIndex(index, function)
    }

    fun newsCardItems(index: Int, function: NewsCardItems.() -> Unit) {
        list.invokeAtIndex(index, function)
    }
}


