package com.google.samples.apps.nowinandroid.ui.tools.rules

import com.kaspersky.kaspresso.logger.UiTestLogger
import io.qameta.allure.kotlin.AllureId
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AllureAnnotationRule(private val testLogger: UiTestLogger) : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                val value = description?.getAnnotation(AllureId::class.java)?.value
                testLogger.i("AllureId equals $value")
                try {
                    base?.evaluate()
                } catch (e: Throwable) {
                    throw e
                } finally {
                    testLogger.i("Bye-bye")
                }
            }
        }
    }
}