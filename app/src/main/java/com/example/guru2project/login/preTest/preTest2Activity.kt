package com.example.guru2project.login.preTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.guru2project.R

class preTest2Activity : AppCompatActivity() {

    private lateinit var Q2A1: Button
    private lateinit var Q2A2: Button
    private lateinit var Q2A3: Button
    private lateinit var Q2Next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_test2)
        Q2A1 = findViewById(R.id.Q2A1)
        Q2A2 = findViewById(R.id.Q2A2)
        Q2A3 = findViewById(R.id.Q2A3)
        Q2Next = findViewById(R.id.Q2Next)

        var value: Int = intent.getIntExtra("value",0)

        //답변 클릭 시 다음 버튼 활성화, 중복선택 X
        Q2A1.setOnClickListener {
            Q2A1.isSelected = !Q2A1.isSelected
            Q2A2.isSelected = false
            Q2A3.isSelected = false
            Q2Next.isEnabled = Q2A1.isSelected
        }
        Q2A2.setOnClickListener {
            Q2A1.isSelected = false
            Q2A2.isSelected = !Q2A2.isSelected
            Q2A3.isSelected = false
            Q2Next.isEnabled = Q2A2.isSelected
        }
        Q2A3.setOnClickListener {
            Q2A1.isSelected = false
            Q2A2.isSelected = false
            Q2A3.isSelected = !Q2A3.isSelected
            Q2Next.isEnabled = Q2A3.isSelected
        }

        Q2Next.setOnClickListener {
            //현재 선택한 답변 확인 후 점수 부여
            if(Q2A1.isSelected){
                value+=3
            }else if(Q2A2.isSelected){
                value+=2
            }else{
                value+=1
            }

            val intent = Intent(this, preTest3Activity::class.java)
            intent.putExtra("value",value)
            startActivity(intent)
        }

    }
}