package com.roshanaryal.mymall

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        firebaseAuth= FirebaseAuth.getInstance()
        SystemClock.sleep(3000)


    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser!=null)
        {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            val intent = Intent(this@SplashActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}