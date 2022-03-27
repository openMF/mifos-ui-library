package org.mifos.mobile.ui.edit_text

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.EditText
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.mifos.mobile.uihouse.R


private const val TAG = "MifosEditText"

class MifosEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private enum class EditTextType {
        NORMAL,
        OUTLINED
    }

    // use normal one if not specified
    private var type = EditTextType.NORMAL
    private var text: String = ""
    private var hint: String? = null

    private val editText: EditText = EditText(context)
    private val textInputLayout = TextInputLayout(context)

    init {
        attrs?.let { attributeSet ->
            val attributes: TypedArray = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.MifosEditText
            )

            try {
                val temp = attributes.getInt(R.styleable.MifosEditText_type, 0)
                if (temp >= 0 && temp < EditTextType.values().size) {
                    type = EditTextType.values()[temp]
                }
                attributes.getString(R.styleable.MifosEditText_text)?.let {
                    text = it
                }
                attributes.getString(R.styleable.MifosEditText_hint)?.let {
                    hint = it
                }

                when (type) {
                    EditTextType.NORMAL -> setupNormalEditTex()

                    EditTextType.OUTLINED -> setupOutlinedEditText()
                }

            } catch (exception: Exception) {
                Log.e(TAG, "error -> ${exception.message}")
            }
            attributes.recycle()
        }
    }

    private fun setupNormalEditTex() {
        editText.apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            text = text
            hint?.let {
                hint = it
            }
        }

        addView(editText)
    }

    private fun setupOutlinedEditText() {
        val textInputEditText = TextInputEditText(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            text = text
            hint?.let {
                hint = it
            }
        }
        textInputLayout.apply {
            isHintEnabled = true
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            addView(textInputEditText)
        }

        addView(textInputLayout)
    }

}