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

package com.google.samples.apps.nowinandroid.ui.searchScreen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.google.samples.apps.nowinandroid.core.designsystem.C
import com.google.samples.apps.nowinandroid.ui.tools.NamedComposeScreen
import com.google.samples.apps.nowinandroid.ui.tools.extensions.setName
import com.kaspersky.components.composesupport.core.KNode

class SearchScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    override val screenName: String = "Search Screen",
) :
    NamedComposeScreen<SearchScreen>(semanticsProvider) {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    val onBackIcon by lazy {
        child<KNode> {
            hasTestTag(C.ON_BACK_ICON)
            useUnmergedTree = true
        }.setName(withParent("On back icon"))
    }
    val searchIcon by lazy {
        child<KNode> {
            hasTestTag(C.SEARCH_ICON)
            useUnmergedTree = true
        }.setName(withParent("Search icon"))
    }
    val searchTextField by lazy {
        child<KNode> {
            hasTestTag(C.SEARCH_TEXT_FIELD)
            useUnmergedTree = true
        }.setName(withParent("Search text field"))
    }

    private fun getStringResource(resourceId: Int): String {
        return context.getString(resourceId)
    }
}