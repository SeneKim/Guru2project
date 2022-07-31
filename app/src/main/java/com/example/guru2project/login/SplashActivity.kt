package com.example.guru2project.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.guru2project.MainActivity
import com.example.guru2project.MainScreenActivity
import com.example.guru2project.R
import com.example.guru2project.fragments.ChallengeFragment
import com.example.guru2project.fragments.HomeFragment
import com.example.guru2project.login.auth.IntroActivity
import com.example.guru2project.story.Story3Activity
import com.example.guru2project.story.StoryActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //3초 지나면 스플래시로 스토리
        Handler().postDelayed({
            startActivity(Intent(this, StoryActivity::class.java))
            finish()
        }, 3000)
    }
}