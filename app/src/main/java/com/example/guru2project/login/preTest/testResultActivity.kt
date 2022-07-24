package com.example.guru2project.login.preTest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.guru2project.MainActivity
import com.example.guru2project.MainScreenActivity
import com.example.guru2project.R
import com.example.guru2project.fragments.HomeFragment

class testResultActivity : AppCompatActivity() {

    private lateinit var levelImg: ImageView
    private lateinit var LevelPhrase: TextView
    private lateinit var subPhrase: TextView
    private lateinit var finshBtn: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_result)
        var value: Int = intent.getIntExtra("value", 0)

        levelImg = findViewById(R.id.levelImg)
        LevelPhrase = findViewById(R.id.LevelPhrase)
        subPhrase = findViewById(R.id.subPhrase)
        finshBtn = findViewById(R.id.finshBtn)

        //답변 점수를 바탕으로 레벨 선정
        when (value) {
            3 -> {
                levelImg.setImageResource(R.drawable.level1)
                LevelPhrase.text = "당신의 프로필은 \n LV.1"
                subPhrase.text = "비건을 관심있는 당신!"
            }
            4 -> {
                levelImg.setImageResource(R.drawable.level2)
                LevelPhrase.text = "당신의 프로필은 \n LV.2"
                subPhrase.text = "비건에 시작하는 당신"
            }
            5 -> {
                levelImg.setImageResource(R.drawable.level3)
                LevelPhrase.text = "당신의 프로필은 \n LV.3"
                subPhrase.text = "비건을 도전하는 당신!"
            }
            6 -> {
                levelImg.setImageResource(R.drawable.level3)
                LevelPhrase.text = "당신의 프로필은 \n LV.3"
                subPhrase.text = "비건을 도전하는 당신!"
            }
            7 -> {
                levelImg.setImageResource(R.drawable.level4)
                LevelPhrase.text = "당신의 프로필은 \n LV.4"
                subPhrase.text = "비건을 지속하는 당신!"
            }
            8 -> {
                levelImg.setImageResource(R.drawable.level4)
                LevelPhrase.text = "당신의 프로필은 \n LV.4"
                subPhrase.text = "비건을 지속하는 당신!"
            }
            9 -> {
                levelImg.setImageResource(R.drawable.level5)
                LevelPhrase.text = "당신의 프로필은 \n LV.5"
                subPhrase.text = "앞장서 비건을 실천하는 당신! \n 당신은 이미 고수군요!"
            }
        }

        finshBtn.setOnClickListener {
            Toast.makeText(this, "어서오세요", Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }

    }
}