package com.google.samples.apps.nowinandroid.ui.tools.interceptors

import com.kaspersky.components.alluresupport.files.attachScreenshotToAllureReport
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo


class SuccessFinaleScreenshotTestInterceptor(
    private val screenshots: Screenshots
) : TestRunWatcherInterceptor {

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        onSectionFailed(makeTag(testInfo, success))
    }

    private fun onSectionFailed(tag: String) {
        screenshots.takeAndApply(tag) { attachScreenshotToAllureReport() }
    }

    private fun makeTag(testInfo: TestInfo, success: Boolean): String =
        "${testInfo.testName}_${if (success) "success" else "failed"}"
}