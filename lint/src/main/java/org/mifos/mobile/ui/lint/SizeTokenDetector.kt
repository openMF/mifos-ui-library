package org.mifos.mobile.ui.lint

import com.android.SdkConstants
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr


@Suppress("UnstableApiUsage")
class SizeTokenDetector : LayoutDetector() {
    companion object {
        private const val MESSAGE =
            "Hardcoded size values should not be used. Replace this with Ui library size token i.e. Mifos.DesignSystem.Size.*"

        val sizeTokenIssue = createIssue(
            id = "SizeTokenIssue",
            briefDescription = "Prohibits usage of size value" +
                    " other than size tokens.",
            explanation = "Size outside of " +
                    "UI library size tokens should not be used.",
            implementation = Implementation(
                SizeTokenDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun appliesTo(folderType: ResourceFolderType) = ResourceFolderType.LAYOUT == folderType

    override fun getApplicableElements(): MutableList<String> = XmlScannerConstants.ALL

    override fun getApplicableAttributes() = listOf(
        SdkConstants.ATTR_LAYOUT_WIDTH,
        SdkConstants.ATTR_LAYOUT_HEIGHT,
        SdkConstants.ATTR_MIN_WIDTH,
        SdkConstants.ATTR_MIN_HEIGHT,
        SdkConstants.ATTR_LAYOUT_MIN_HEIGHT,
        SdkConstants.ATTR_LAYOUT_MIN_WIDTH,
        SdkConstants.ATTR_MAX_WIDTH,
        SdkConstants.ATTR_MAX_HEIGHT,
        SdkConstants.ATTR_LAYOUT_MAX_HEIGHT,
        SdkConstants.ATTR_LAYOUT_MAX_WIDTH
    )

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val allowedPrefix = "@dimen/Mifos.DesignSystem.Size."
        attribute.nodeValue.let { attrValue ->
            if(attrValue != SdkConstants.VALUE_WRAP_CONTENT
                && attrValue != SdkConstants.VALUE_MATCH_PARENT
                && attrValue != SdkConstants.VALUE_ZERO_DP
                && !attrValue.startsWith(allowedPrefix)){
                context.report(sizeTokenIssue, attribute, context.getLocation(attribute), MESSAGE)
            }
        }
    }
}