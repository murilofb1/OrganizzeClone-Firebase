package com.murilofb.organizzeclone.helpers

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.murilofb.organizzeclone.models.UserMD

class FirebaseH {
    companion object {
        fun registerUser(activity: AppCompatActivity, userMD: UserMD) {

        }

        fun loginUser(activity: AppCompatActivity, userEmail: String, userPsswd: String) {

        }

        fun isSomeoneLogged(): Boolean {
            return FirebaseAuth.getInstance().currentUser != null
        }
    }
}