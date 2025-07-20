package com.google.samples.apps.nowinandroid.ui.tools.rules

import org.junit.AssumptionViolatedException
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DeprecatedTestRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                if (description?.getAnnotation(Deprecated::class.java) != null) {
                    throw AssumptionViolatedException("Тест ${description.methodName} отмечен как @Deprecated и будет пропущен")
                }
                try {
                    base?.evaluate()
                } catch (e: Throwable) {
                    throw e
                }
            }
        }
    }
}