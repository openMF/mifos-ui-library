package org.mifos.mobile.ui

import android.content.res.Resources
import android.util.TypedValue

/**
 * Given value in dp is converted in actual pixel value
 */
val Number.dp get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
)
