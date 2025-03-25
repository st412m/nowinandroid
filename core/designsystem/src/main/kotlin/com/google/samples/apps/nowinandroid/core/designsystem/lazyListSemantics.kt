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

package com.google.samples.apps.nowinandroid.core.designsystem

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val LazyListItemPositionSemantics = SemanticsPropertyKey<Int>("LazyListItemPosition")
var SemanticsPropertyReceiver.lazyListItemPosition by LazyListItemPositionSemantics

val LazyListLengthSemantics = SemanticsPropertyKey<Int>("LazyListLength")
var SemanticsPropertyReceiver.lazyListLength by LazyListLengthSemantics