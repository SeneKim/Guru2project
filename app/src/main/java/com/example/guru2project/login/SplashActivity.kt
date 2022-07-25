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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth=Firebase.auth

        /*비로그인 상태 -> 로그인 화면*/
        if(auth.currentUser?.uid==null){
            Log.d("SplashActivity","null")
            /*스플래쉬 화면 3초 뒤 종료 -> intro 화면으로 이동*/
            Handler(Looper.getMainLooper()).postDelayed({

                /*일정 시간이 지나면 로그인 창으로 이동*/
                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()        /*이동한 다음 사용안함*/
            }, DURATION)
        }
        /*이미 로그인한 상태 -> 바로 메인화면으로 이동*/
        else{
            Log.d("SplashActivity","not null")
            /*스플래쉬 화면 3초 뒤 종료 -> intro 화면으로 이동*/
            Handler(Looper.getMainLooper()).postDelayed({

                /*일정 시간이 지나면 메인화면으로 이동*/
                val intent = Intent(this, MainScreenActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                Log.d("SplashActivity","메인화면으로 이동")
                startActivity(intent)
                Log.d("SplashActivity","메인화면으로 완료")
                finish()        /*이동한 다음 사용안함*/
            }, DURATION)
        }

   }

    companion object{private const val DURATION:Long=2000}

    override fun onBackPressed() {
        super.onBackPressed()
    }
}