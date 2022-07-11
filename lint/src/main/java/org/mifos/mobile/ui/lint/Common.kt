@file:Suppress("UnstableApiUsage")

package org.mifos.mobile.ui.lint

import com.android.tools.lint.detector.api.*

val libraryLintCategory = Category.create("UI library lint issues", 7)

fun createIssue(
    id: String,
    briefDescription: String,
    explanation: String,
    implementation: Implementation
) = Issue.create(
    id = id,
    briefDescription = briefDescription,
    explanation = explanation,
    category = libraryLintCategory,
    priority = 7,
    severity = Severity.FATAL,
    implementation = implementation
)