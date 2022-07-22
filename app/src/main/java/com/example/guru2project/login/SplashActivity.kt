package com.example.guru2project.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.guru2project.R
import com.example.guru2project.login.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /*스플래쉬 화면 3초 뒤 종료 -> intro 화면으로 이동*/
        Handler(Looper.getMainLooper()).postDelayed({

            /*일정 시간이 지나면 로그인 창으로 이동*/
            val intent = Intent(this, IntroActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()        /*이동한 다음 사용안함*/
        }, DURATION)

   }

    companion object{private const val DURATION:Long=2000}

    override fun onBackPressed() {
        super.onBackPressed()
    }
}