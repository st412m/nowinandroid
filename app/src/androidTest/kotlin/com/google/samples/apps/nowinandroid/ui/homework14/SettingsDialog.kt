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

package com.google.samples.apps.nowinandroid.ui.homework14

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.components.composesupport.core.KNode
import io.github.kakaocup.compose.node.element.ComposeScreen

class SettingsDialog(semanticProvides: SemanticsNodeInteractionsProvider) :
    ComposeScreen<SettingsDialog>(semanticProvides) {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun getStringResource(resourceName: String): String {
        val resId = context.resources.getIdentifier(
            resourceName,
            "string",
            context.packageName,
        )
        return context.getString(resId)
    }

    val privacyPolicyButton = KNode(semanticProvides) {
        hasText(this@SettingsDialog.getStringResource("feature_settings_privacy_policy"))
    }

    val brandGuideButton = KNode(semanticProvides) {
        hasText(this@SettingsDialog.getStringResource("feature_settings_brand_guidelines"))
    }

    val feedbackButton = KNode(semanticProvides) {
        hasText(this@SettingsDialog.getStringResource("feature_settings_feedback"))
    }

    val licensesButton = KNode(semanticProvides) {
        hasText(this@SettingsDialog.getStringResource("feature_settings_licenses"))
    }

    val okButton = KNode(semanticProvides) {
        hasText(this@SettingsDialog.getStringResource("feature_settings_dismiss_dialog_button_text"))
    }
}
