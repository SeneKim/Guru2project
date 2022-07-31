package com.example.guru2project.story

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import com.example.guru2project.R
import render.animations.*
import kotlinx.android.synthetic.main.activity_story.*


class StoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        //오른쪽 송아지
        val imageView: ImageView = findViewById(R.id.animal1)
        val render = Render(this)
        render.setAnimation(Fade().InRight(imageView))
        render.start()

        //오리들
        val imageView2: ImageView = findViewById(R.id.animal2)
        render.setAnimation(Fade().InUp(imageView2))
        render.start()

        //젖소
        val imageView3: ImageView = findViewById(R.id.animal3)
        render.setAnimation(Fade().InLeft(imageView3))
        render.start()

        //머리 위 링
        val imageView4: ImageView = findViewById(R.id.ring)
        render.setAnimation(Rotate().InUpLeft(imageView4))
        render.start()

        //텍스트
        val textView: TextView = findViewById(R.id.text)
        render.setAnimation(Zoom().In(textView))
        render.start()

        //3초 지나면 스플래시로 다음장면
        Handler().postDelayed({
            startActivity(Intent(this, Story2Activity::class.java))
            finish()
        }, 3000)
    }
}