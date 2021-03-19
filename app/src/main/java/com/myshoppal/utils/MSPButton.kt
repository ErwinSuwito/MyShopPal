package com.myshoppal.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class MSPButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

    /**
     * The init block runs every time the class is instantiated
     */
    init {
        applyFont()
    }

    /**
     * Applies a font to the control
     */
    private fun applyFont()
    {
        val typeface: Typeface = Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        setTypeface(typeface)
    }
}