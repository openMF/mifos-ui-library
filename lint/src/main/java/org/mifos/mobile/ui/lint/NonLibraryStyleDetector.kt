package org.mifos.mobile.ui.lint

import com.android.SdkConstants
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class NonLibraryStyleDetector : LayoutDetector() {
    companion object {
        private const val MESSAGE =
            "Styles should only be applied from the ui library"

        val nonLibraryStyleIssue = createIssue(
            id = "NonLibraryStyleIssue",
            briefDescription = "Prohibits usage of styles" +
                    " other ui library styles",
            explanation = "Styles outside of " +
                    "UI library should not be used.",
            implementation = Implementation(
                NonLibraryStyleDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun appliesTo(folderType: ResourceFolderType) = ResourceFolderType.LAYOUT == folderType

    override fun getApplicableElements(): MutableList<String> = XmlScannerConstants.ALL

    override fun getApplicableAttributes() = listOf(
        SdkConstants.ATTR_STYLE
    )

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val allowedPrefix = listOf(
            "@style/Mifos.DesignSystem.TextStyle.",
            "@style/Mifos.DesignSystem.Components.",
            "?"
        )
        attribute.nodeValue.let { attrValue ->
            if (attrValue != SdkConstants.VALUE_WRAP_CONTENT
                && attrValue != SdkConstants.VALUE_MATCH_PARENT
                && attrValue != SdkConstants.VALUE_ZERO_DP
                && allowedPrefix.none { attrValue.startsWith(it) }
            ) {
                context.report(
                    nonLibraryStyleIssue,
                    attribute,
                    context.getLocation(attribute),
                    MESSAGE
                )
            }
        }
    }
}