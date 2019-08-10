package com.nezspencer.tutorials

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorInt

class CustomClickSpan(
    private val onClickListener: () -> Unit,
    @ColorInt private val textColor: Int,
    private val shouldUnderline: Boolean = false
) : ClickableSpan() {
    override fun onClick(p0: View) = onClickListener.invoke()

    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = shouldUnderline
        ds.color = textColor
    }
}