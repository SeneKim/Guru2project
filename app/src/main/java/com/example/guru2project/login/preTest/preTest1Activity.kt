package com.example.guru2project.login.preTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.guru2project.R

class preTest1Activity : AppCompatActivity() {

    private lateinit var Q1A1: Button
    private lateinit var Q1A2: Button
    private lateinit var Q1A3: Button
    private lateinit var Q1Next: Button

    var value:Int=0 //레벨 선정을 위한 값

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_test1)
        Q1A1 = findViewById(R.id.Q1A1)
        Q1A2 = findViewById(R.id.Q1A2)
        Q1A3 = findViewById(R.id.Q1A3)
        Q1Next = findViewById(R.id.Q1Next)

        //답변 클릭 시 다음 버튼 활성화, 중복선택 X
        Q1A1.setOnClickListener {
            Q1A1.isSelected = !Q1A1.isSelected
            Q1A2.isSelected = false
            Q1A3.isSelected = false
            Q1Next.isEnabled = Q1A1.isSelected
        }

        Q1A2.setOnClickListener {
            Q1A1.isSelected = false
            Q1A2.isSelected = !Q1A2.isSelected
            Q1A3.isSelected = false
            Q1Next.isEnabled = Q1A2.isSelected
        }

        Q1A3.setOnClickListener {
            Q1A1.isSelected = false
            Q1A2.isSelected = false
            Q1A3.isSelected = !Q1A3.isSelected
            Q1Next.isEnabled = Q1A3.isSelected
        }

        Q1Next.setOnClickListener {
            //현재 선택한 답변 확인 후 점수 부여
            if(Q1A1.isSelected){
                value+=3
            }else if(Q1A2.isSelected){
                value+=2
            }else{
                value+=1
            }

            val intent = Intent(this, preTest2Activity::class.java)
            intent.putExtra("value",value)
            startActivity(intent)
        }

    }
}