package org.mifos.mobile.ui

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel

class ProfileImageView @JvmOverloads  constructor(
    context: Context,
    AttributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.profileImageViewStyle
) : FrameLayout(context, AttributeSet, defStyleAttr) {

    private val userImage by lazy { findViewById<ShapeableImageView>(R.id.user_image_drawable) }
    private val userImageChangeButton by lazy { findViewById<MaterialCardView>(R.id.image_change_button) }
    private val userImageChangeButtonImage by lazy { findViewById<AppCompatImageView>(R.id.image_change_button_image) }

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.profile_image, this, true)
        val attributeArray: TypedArray = context.theme.obtainStyledAttributes(
            AttributeSet,
            R.styleable.profileImageStyleable, defStyleAttr, 0
        )
        attributeArray.run {
            val userImageRes = getResourceIdOrNull(R.styleable.profileImageStyleable_userImage)
            val strokeColor = getColorOrNull(R.styleable.profileImageStyleable_strokeColor)
            val strokeWidth = getDimensionOrNull(R.styleable.profileImageStyleable_strokeWidth)
            val shapeAppearance = getResourceIdOrNull(R.styleable.profileImageStyleable_shapeAppearance)
            val imageChangeButtonSize = getDimensionOrNull(R.styleable.profileImageStyleable_imageChangeButtonSize)
            val imageChangeButtonDrawable = getResourceIdOrNull(R.styleable.profileImageStyleable_imageChangeButtonSrc)
            val imageChangeButtonBottomEndMargin = getDimensionOrNull(R.styleable.profileImageStyleable_imageChangeButtonBottomEndPadding)
            val imageChangeButtonInnerPadding = getDimensionOrNull(R.styleable.profileImageStyleable_imageChangeButtonInnerPadding)

            userImageRes?.let { setUserImage(it) }
            strokeColor?.let { setStrokeColorInt(it) }
            strokeWidth?.let { setStrokeWidth(it) }
            shapeAppearance?.let { setShapeAppearance(it) }
            imageChangeButtonSize?.toInt()?.let { setImageChangeButtonSize(it) }
            imageChangeButtonDrawable?.let { setImageChangeButtonDrawable(it) }
            imageChangeButtonBottomEndMargin?.toInt()?.let { setImageChangeButtonBottomEndMargin(it) }
            imageChangeButtonInnerPadding?.toInt()?.let { setImageChangeButtonInnerPadding(it) }

            recycle()
        }

    }



    
    fun setStrokeColorRes(@ColorRes resId: Int){
        userImage.strokeColor =  ColorStateList.valueOf(ContextCompat.getColor(context, resId))
    }
    fun setStrokeColorInt(@ColorInt strokeColor: Int){
        userImage.strokeColor =  ColorStateList.valueOf(strokeColor)
    }
    fun setStrokeWidth(@Dimension strokeWidth: Float){
        userImage.strokeWidth = strokeWidth
        userImage.setPadding(strokeWidth.toInt())
    }
    fun setShapeAppearance(@StyleRes shapeAppearanceRes: Int){
        userImage.shapeAppearanceModel =  ShapeAppearanceModel
            .builder(context, com.google.android.material.R.style.ShapeAppearance_Material3_SmallComponent, shapeAppearanceRes)
            .build()
    }

    fun setImageChangeButtonSize(@Dimension size: Int){
        userImageChangeButton.layoutParams.let { oldLayoutParams ->
            oldLayoutParams.height = size
            oldLayoutParams.width = size
        }
    }

    fun setImageChangeButtonBottomEndMargin(@Dimension margin: Int){
        (userImageChangeButton.layoutParams as FrameLayout.LayoutParams).apply {
            setMargins(0,0,0,margin)
            marginEnd = margin
        }
    }

    fun setImageChangeButtonInnerPadding(@Dimension padding: Int){
        userImageChangeButton.setContentPadding(padding, padding, padding, padding)
    }

    fun setImageChangeButtonDrawable(@DrawableRes drawable: Int){
        userImageChangeButtonImage.setImageResource(drawable)
    }


    fun setUserImage(@DrawableRes resId: Int){
        userImage.setImageResource(resId)
    }

    fun setOnImageChangeButtonListener(listener: () -> Unit){
        userImageChangeButton.setOnClickListener{
            listener()
        }
    }
}

@ColorInt
fun TypedArray.getColorOrNull(@StyleableRes res: Int): Int? {
    return if(this.hasValue(res))
        this.getColor(res, 0)
    else
        null
}

fun TypedArray.getDimensionOrNull(@StyleableRes res: Int): Float? {
    return if(this.hasValue(res))
        this.getDimension(res, 0f)
    else
        null
}

@AnyRes
fun TypedArray.getResourceIdOrNull(@StyleableRes res: Int): Int? {
    return if(this.hasValue(res))
        this.getResourceId(res, 0)
    else
        null
}