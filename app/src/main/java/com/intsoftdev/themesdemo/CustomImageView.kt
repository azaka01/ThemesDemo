package com.intsoftdev.themesdemo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

@SuppressLint("ResourceAsColor")
class CustomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var highlightColor: Int = R.color.colorBlack

    init {
        attrs?.let {
            val attributes = context.theme.obtainStyledAttributes(
                it,
                R.styleable.CustomImageView,
                0, 0
            )
            highlightColor = attributes.getColor(
                R.styleable.CustomImageView_custom_component_color,
                context.getColor(R.color.colorBlack)
            )
            attributes.recycle()
        }
    }

    private val paint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL_AND_STROKE
            color = highlightColor
        }
    }

    private val badgeHorizontalLocationRatio = 0.74f
    private val badgeVerticalLocationRatio = 0.26f
    private val badgeSizeRatio = 0.12f

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(
            width * badgeHorizontalLocationRatio,
            height * badgeVerticalLocationRatio,
            height * badgeSizeRatio,
            paint
        )
    }
}