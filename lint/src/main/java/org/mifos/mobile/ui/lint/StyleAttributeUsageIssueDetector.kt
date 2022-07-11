package org.mifos.mobile.ui.lint

import com.android.SdkConstants
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class StyleAttributeUsageIssueDetector : ResourceXmlDetector() {
    companion object {
        const val MESSAGE = "Style attribute usage in layout detected"
        val styleAttributeUsageIssue = createIssue(
            id = "StyleAttributeUsageIssue",
            briefDescription = "Usage of style attribute. Use theme styles instead",
            explanation = """
                For consistency of UI, changing Views' style attributes in layout files is discouraged.
                Click here to learn more: https://sample.doc.link.com
            """.trimIndent(),
            implementation = Implementation(
                StyleAttributeUsageIssueDetector::class.java,
                Scope.ALL_RESOURCES_SCOPE
            )
        )
    }

    override fun appliesTo(folderType: ResourceFolderType) = ResourceFolderType.LAYOUT == folderType

    override fun getApplicableElements(): MutableList<String> = XmlScannerConstants.ALL

    override fun getApplicableAttributes() = listOf(
        //Text attributes
        SdkConstants.ATTR_TEXT_SIZE,
        SdkConstants.ATTR_TEXT_COLOR,
        SdkConstants.ATTR_TEXT_STYLE,
        //Color attributes
        SdkConstants.ATTR_TINT,
        SdkConstants.ATTR_BACKGROUND_TINT,
        SdkConstants.ATTR_ICON_TINT,
        SdkConstants.ATTR_BUTTON_TINT,
        SdkConstants.ATTR_DRAWABLE_TINT,
        SdkConstants.ATTR_FOREGROUND_TINT,
        SdkConstants.ATTR_PROGRESS_TINT,
        SdkConstants.ATTR_INDETERMINATE_TINT,
        SdkConstants.ATTR_END_ICON_TINT,
        SdkConstants.ATTR_START_ICON_TINT,
        SdkConstants.ATTR_TAB_ICON_TINT,
        SdkConstants.ATTR_TAB_ICON_TINT,
        SdkConstants.ATTR_CHECK_MARK_TINT,
        SdkConstants.ATTR_SECONDARY_PROGRESS_TINT,
        SdkConstants.ATTR_PASSWORD_TOGGLE_TINT,
        SdkConstants.ATTR_CHIP_ICON_TINT,
        SdkConstants.ATTR_CLOSE_ICON,
        SdkConstants.ATTR_ERROR_ICON_TINT,
        SdkConstants.ATTR_ERROR_TEXT_COLOR,
        //card attributes
        SdkConstants.ATTR_ELEVATION,
        SdkConstants.ATTR_CARD_ELEVATION,
        SdkConstants.ATTR_CORNER_RADIUS,
        SdkConstants.ATTR_CARD_CORNER_RADIUS,
        //other attributes
        SdkConstants.ATTR_FONT,
        SdkConstants.ATTR_FONT_STYLE,
        SdkConstants.ATTR_FONT_FAMILY,
        SdkConstants.ATTR_TEXT_STYLE,
    )


    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val incident = Incident(context, styleAttributeUsageIssue)
            .message(MESSAGE)
            .at(attribute)
        context.report(incident)
    }
}