package com.myshoppal.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class MSPEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    /**
     * The init block runs every time the class is instantiated
     */
    init {
        // Call the function to apply the font to the component.
        applyFont()
    }

    /**
     * Applies a font to the EditText
     */
    private fun applyFont()
    {
        val typeface: Typeface = Typeface.createFromAsset(context.assets, "Montserrat-Regular.ttf")
        setTypeface(typeface)
    }
}