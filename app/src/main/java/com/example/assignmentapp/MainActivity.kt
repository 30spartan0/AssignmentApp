package com.example.assignmentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.assignmentapp.data.UserPreferences
import com.example.assignmentapp.ui.auth.AuthActivity
import com.example.assignmentapp.ui.home.HomeActivity
import com.example.assignmentapp.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if(it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}