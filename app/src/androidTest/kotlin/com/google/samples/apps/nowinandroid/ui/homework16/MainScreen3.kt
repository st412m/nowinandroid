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

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.google.samples.apps.nowinandroid.core.designsystem.C
import com.kaspersky.components.composesupport.core.KNode
import io.github.kakaocup.compose.node.element.ComposeScreen

class MainScreen3(semanticProvides: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainScreen3>(semanticProvides) {

    val centerAlignedTopAppBar = child<KNode> {
        hasTestTag("niaTopAppBar")
    }

    val titleText = child<KNode> {
        hasTestTag(C.UPPER_TITLE_TEXT)
        useUnmergedTree = true
    }

    val searchIcon = child<KNode> {
        hasTestTag(C.UPPER_SEARCH_ICON)
        useUnmergedTree = true
    }

    val settingIcon = child<KNode> {
        hasTestTag(C.UPPER_SETTINGS_ICON)
        useUnmergedTree = true
    }

    val forYouIcon = child<KNode> {
        hasTestTag("NiaNavItem")
//        hasText("For you")   не определился, что в данном случае надежнее
        hasPosition(0)
    }
    val savedIcon = child<KNode> {
        hasTestTag("NiaNavItem")
//        hasText("Saved")
        hasPosition(1)
    }
    val interestsIcon = child<KNode> {
        hasTestTag("NiaNavItem")
//        hasText("Interests")
        hasPosition(2)
    }
    val doneButton = child<KNode> {
        hasTestTag(C.DONE_BUTTON)
    }
    val scrollbar = child<KNode>{
        hasTestTag(C.SCROLLBAR)
    }
}