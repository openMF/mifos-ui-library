package org.mifos.mobile.ui.lint

import com.android.SdkConstants
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class SpacingTokenDetector : LayoutDetector() {
    companion object {
        private const val MESSAGE =
            "Hardcoded spacings should not be used. Replace this with Ui library spacing token i.e. Mifos.DesignSystem.Spacing.*"

        val spacingTokenIssue = createIssue(
            id = "SpacingTokenIssue",
            briefDescription = "Prohibits usage of spacing (i.e.,margins and paddings)" +
                    " other than spacing tokens.",
            explanation = "Spacings (i.e.,margins and paddings) outside of " +
                    "UI library spacing tokens should not be used.",
            implementation = Implementation(
                SpacingTokenDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun appliesTo(folderType: ResourceFolderType) = ResourceFolderType.LAYOUT == folderType

    override fun getApplicableElements(): MutableList<String> = XmlScannerConstants.ALL

    override fun getApplicableAttributes() = listOf(
        //margins
        SdkConstants.ATTR_LAYOUT_MARGIN,
        SdkConstants.ATTR_LAYOUT_MARGIN_LEFT,
        SdkConstants.ATTR_LAYOUT_MARGIN_RIGHT,
        SdkConstants.ATTR_LAYOUT_MARGIN_START,
        SdkConstants.ATTR_LAYOUT_MARGIN_END,
        SdkConstants.ATTR_LAYOUT_MARGIN_TOP,
        SdkConstants.ATTR_LAYOUT_MARGIN_BOTTOM,
        SdkConstants.ATTR_LAYOUT_MARGIN_HORIZONTAL,
        SdkConstants.ATTR_LAYOUT_MARGIN_VERTICAL,
        //padding
        SdkConstants.ATTR_PADDING,
        SdkConstants.ATTR_PADDING_BOTTOM,
        SdkConstants.ATTR_PADDING_TOP,
        SdkConstants.ATTR_PADDING_RIGHT,
        SdkConstants.ATTR_PADDING_LEFT,
        SdkConstants.ATTR_PADDING_START,
        SdkConstants.ATTR_PADDING_END,
        SdkConstants.ATTR_PADDING_HORIZONTAL,
        SdkConstants.ATTR_PADDING_VERTICAL
    )

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val allowedPrefix = "@dimen/Mifos.DesignSystem.Spacing."
        if(attribute.nodeValue != SdkConstants.VALUE_ZERO_DP && !attribute.nodeValue.startsWith(allowedPrefix)){
            context.report(spacingTokenIssue, attribute, context.getLocation(attribute), MESSAGE)
        }
    }
}