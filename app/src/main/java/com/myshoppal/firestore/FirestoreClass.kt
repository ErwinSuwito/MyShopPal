package com.myshoppal.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.myshoppal.models.User
import com.myshoppal.ui.activities.LoginActivity
import com.myshoppal.ui.activities.RegisterActivity
import com.myshoppal.ui.activities.UserProfileActivity
import com.myshoppal.utils.Constants

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User)
    {
        mFireStore.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                // Call a function of base activity for transferring result to it
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }

    fun getCurrentUserID(): String {
        // An instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it's not
        // null or else it will be blank
        var currentUserID = ""
        if (currentUser != null)
        {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }

    fun getUserDetails(activity: Activity)
    {
        // Here we pass the collection name from which we want the data
        mFireStore.collection(Constants.USERS)
                .document(getCurrentUserID())
                .get()
                .addOnSuccessListener { document ->
                    Log.i(activity.javaClass.simpleName, document.toString())

                    // Here we have received the document snapshot which is converted
                    // into the User Data model object.
                    val user = document.toObject(User::class.java)!!

                    val sharedPreferences = activity.getSharedPreferences(
                            Constants.MYSHOPPAL_PREFERENCES,
                            Context.MODE_PRIVATE
                    )

                    // Create an instance of the editor which is help
                    // us to edit the SharedPreference.
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString(
                            Constants.LOGGED_IN_USERNAME,
                            "${user.firstName} ${user.lastName}"
                    )

                    editor.apply()

                    when (activity)
                    {
                        is LoginActivity -> {
                            activity.userLoggedInSuccess(user)
                        }
                    }
                }
                .addOnFailureListener { e ->
                    when (activity)
                    {
                        is LoginActivity -> {
                            activity.hideProgressDialog()
                        }
                    }

                    Log.e(activity.javaClass.simpleName, "Error while getting user details.")
                }
    }

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
        // Collection Name
        mFireStore.collection(Constants.USERS)
            // Document ID against which the data to be updated. Here the document id is the current logged in user id.
            .document(getCurrentUserID())
            // A HashMap of fields which are to be updated.
            .update(userHashMap)
            .addOnSuccessListener {
                when (activity) {
                    is UserProfileActivity -> {
                        // Call a function of base activity for transferring the result to it.
                        activity.userProfileUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener { e ->

                when (activity) {
                    is UserProfileActivity -> {
                        // Hide the progress dialog if there is any error. And print the error in log.
                        activity.hideProgressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    "Error while updating the user details.",
                    e
                )
            }
    }

}