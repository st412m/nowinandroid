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

package com.google.samples.apps.nowinandroid.ui.followingScreen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.google.samples.apps.nowinandroid.core.designsystem.C
import com.google.samples.apps.nowinandroid.ui.tools.NamedComposeScreen
import com.google.samples.apps.nowinandroid.ui.tools.extensions.setName
import io.github.kakaocup.compose.node.element.KNode

class FollowingScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    override val screenName: String = "Following Screen",
) :
    NamedComposeScreen<FollowingScreen>(semanticsProvider) {

    val backButton by lazy {
        child<KNode> {
            hasContentDescription(com.google.samples.apps.nowinandroid.core.ui.R.string.core_ui_back)
        }.setName(withParent("Back Button"))
    }

    val filterChip by lazy {
        child<KNode> {
            hasTestTag(C.FOLLOWING_CHIP)
            useUnmergedTree = true
        }.setName(withParent("Filter Chip"))

    }
    val filterChipLabel by lazy {
        child<KNode>{
            hasTestTag(C.FOLLOWING_CHIP_LABEL)
        }.setName(withParent("Filter Chip Label"))
    }
}
