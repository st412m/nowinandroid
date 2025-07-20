package com.google.samples.apps.nowinandroid.ui.tools.interceptors

import com.kaspersky.components.alluresupport.files.attachScreenshotToAllureReport
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class FailOnlyScreenshotStepInterceptor(
    private val screenshots: Screenshots
) : StepWatcherInterceptor {

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        intercept("${makeTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    private fun intercept(tag: String) {
        screenshots.takeAndApply(tag) { attachScreenshotToAllureReport() }
    }

    private fun makeTag(stepInfo: StepInfo): String =
        "${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}