package org.mifos.mobile.ui

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import androidx.core.view.ViewCompat


fun View.setStatusBarColor(@ColorInt color: Int){
    if (!isInEditMode) {
        (context as Activity).window.statusBarColor = color
        ViewCompat.getWindowInsetsController(this)?.isAppearanceLightStatusBars = ColorUtils.calculateLuminance(color) > 0.5
    }
}

@ColorInt
fun Context.getThemeAttributeColor(@AttrRes colorAttribute: Int):  Int{
    val typedValue = TypedValue()
    theme.resolveAttribute(colorAttribute, typedValue, true)
    return typedValue.data
}