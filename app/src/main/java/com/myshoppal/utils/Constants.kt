package com.myshoppal.utils

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

// A custom object to declare all the constant values in a
// single file. The constant values declared here is can
// be used in whole application.
object Constants {

    // Firebase constants
    // This is used for the collection name for USERS
    const val USERS: String = "users"

    const val MYSHOPPAL_PREFERENCES: String = "MyShopPalPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"

    const val EXTRA_USER_DETAILS: String = "extra_user_details"

    // A unique code for asking the Read Storage Permission using this we will be check and identify
    // in the method onRequestPermissionsResult in the Base Activity.
    const val READ_STORAGE_PERMISSION_CODE = 2

    // A unique code of image selection from Phone Storage.
    const val PICK_IMAGE_REQUEST_CODE = 2

    /**
     * A function for user profile image selection from phone storage.
     */
    fun showImageChooser(activity: Activity) {
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }
}