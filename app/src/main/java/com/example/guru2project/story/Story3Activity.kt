package com.example.guru2project.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.guru2project.R
import render.animations.*

class Story3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story3)

        //텍스트
        val textView: TextView = findViewById(R.id.text3)
        val render = Render(this)
        render.setAnimation(Bounce().InDown(textView))
        render.start()

        //친구
        val imageView: ImageView = findViewById(R.id.friend)
        render.setAnimation(Fade().InUp(imageView))
        render.start()

        //나
        val imageView2: ImageView = findViewById(R.id.me3)
        render.setAnimation(Fade().InUp(imageView2))
        render.start()


    }
}