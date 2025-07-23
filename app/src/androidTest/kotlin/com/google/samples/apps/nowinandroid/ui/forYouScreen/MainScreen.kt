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

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.google.samples.apps.nowinandroid.core.designsystem.C
import com.google.samples.apps.nowinandroid.feature.foryou.R.string
import com.google.samples.apps.nowinandroid.ui.tools.NamedComposeScreen
import com.google.samples.apps.nowinandroid.ui.tools.extensions.setName
import com.kaspersky.components.composesupport.core.KNode

class MainScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    override val screenName: String = "Main Screen",
) :
    NamedComposeScreen<MainScreen>(semanticsProvider) {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun getStringResource(resourceId: Int): String {
        return context.getString(resourceId)
    }

    val searchButton by lazy {
        child<KNode> {
            hasTestTag("NiaTopAppBarSearch")
        }.setName(withParent("Search Button"))
    }

    val searchIcon by lazy {
        child<KNode> {
            hasTestTag(C.UPPER_SEARCH_ICON)
            useUnmergedTree = true
        }.setName(withParent("Search Icon"))
    }

    val topAppBarTitle by lazy {
        child<KNode> {
            hasTestTag("NiaTopAppBarTitle")
        }.setName(withParent("Top app bar Title"))
    }

    val settingButton by lazy {
        child<KNode> {
            hasTestTag("NiaTopAppBarSettings")
        }.setName(withParent("Setting Button"))
    }

    val settingIcon by lazy {
        child<KNode> {
            hasTestTag(C.UPPER_SETTINGS_ICON)
            useUnmergedTree = true
        }.setName(withParent("Setting Icon"))
    }

    val textTitle by lazy {
        child<KNode> {
            hasText(this@MainScreen.getStringResource(string.feature_foryou_onboarding_guidance_title))
        }.setName(withParent("Text title"))
    }

    val textSubTitle by lazy {
        child<KNode> {
            hasText(this@MainScreen.getStringResource(string.feature_foryou_onboarding_guidance_subtitle))
        }.setName(withParent("Text subtitle"))
    }

    val doneButton by lazy {
        child<KNode> {
            hasTestTag(C.DONE_BUTTON)
        }.setName(withParent("[Done] button"))
    }


    val forYouButton by lazy {
        child<KNode> {
            hasTestTag("NiaNavItem")
            hasPosition(0)
        }.setName(withParent("[For you] button"))
    }

    val savedButton by lazy {
        child<KNode> {
            hasTestTag("NiaNavItem")
            hasPosition(1)
        }.setName(withParent("[Saved] button"))
    }

    val interestsButton by lazy {
        child<KNode> {
            hasTestTag("NiaNavItem")
            hasPosition(2)
        }.setName(withParent("[Interests] button"))
    }
}