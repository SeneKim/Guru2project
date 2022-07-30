package com.example.guru2project.story

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.guru2project.R
import render.animations.*
import kotlinx.android.synthetic.main.activity_story2.*

class Story2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story2)

        //물음표
        val imageView: ImageView = findViewById(R.id.qMark)
        val render = Render(this)
        render.setAnimation(Rotate().InUpLeft(imageView))
        render.start()

        //텍스트
        val textView: TextView = findViewById(R.id.text2)
        render.setAnimation(Zoom().InUp(textView))
        render.start()


        nextBtn2.setOnClickListener {
            val nextIntent = Intent(this, Story3Activity::class.java)
            startActivity(nextIntent)
        }

    }
}