package org.mifos.mobile.ui.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import org.mifos.mobile.ui.lint.NonLibraryStyleDetector.Companion.nonLibraryStyleIssue
import org.mifos.mobile.ui.lint.SizeTokenDetector.Companion.sizeTokenIssue
import org.mifos.mobile.ui.lint.SpacingTokenDetector.Companion.spacingTokenIssue
import org.mifos.mobile.ui.lint.StyleAttributeUsageIssueDetector.Companion.styleAttributeUsageIssue


@Suppress("UnstableApiUsage")
class LintRegistry : IssueRegistry() {

    override val api: Int = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(
            styleAttributeUsageIssue,
            spacingTokenIssue,
            sizeTokenIssue,
            nonLibraryStyleIssue
        )
    override val vendor: Vendor
        get() = Vendor(vendorName = "Mifos", identifier = "mifos ui library")
}