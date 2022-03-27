package org.mifos.mobile.ui.button

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import org.mifos.mobile.uihouse.R

class MifosButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val textView = TextView(context)
    private val imageView = ImageView(context)

    init {
        attrs?.let { attributeSet ->
            val attributes: TypedArray = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.MifosButton
            )
            // TODO: complete this
            attributes.recycle()
        }
    }

}