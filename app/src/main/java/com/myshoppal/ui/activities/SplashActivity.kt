package com.myshoppal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.myshoppal.R
import com.myshoppal.firestore.FirestoreClass

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Make the activity full screen.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed({
            // If the user is logged in once and did not logged out manually from the app.
            // So, next time when the user is coming into the app user will be redirected to MainScreen.
            // If user is not logged in or logout manually then user will  be redirected to the Login screen as usual.

            // Get the current logged in user id
            val currentUserID = FirestoreClass().getCurrentUserID()

            if (currentUserID.isNotEmpty()) {
                // Start the Main Activity
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                // Launch the Login Activity
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()
        }, 3000)

        /*
          Below is the code to modify a single textview to use a custom font
          val typeface: Typeface =
            Typeface.createFromAsset(assets, "Montserrat-Bold.ttf")
            tv_app_name.typeface = typeface
         */
    }
}