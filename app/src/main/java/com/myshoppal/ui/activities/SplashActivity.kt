package com.myshoppal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.myshoppal.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Make the activity full screen.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Delays and navigate to MainActiivty
        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish() // Call this when your activity is done and should be closed.

        }, 3000)

        /*
          Below is the code to modify a single textview to use a custom font
          val typeface: Typeface =
            Typeface.createFromAsset(assets, "Montserrat-Bold.ttf")
            tv_app_name.typeface = typeface
         */
    }
}