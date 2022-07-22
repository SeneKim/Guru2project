package com.example.guru2project.login.preTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.guru2project.R

class preTest3Activity : AppCompatActivity() {
    private lateinit var Q3A1: Button
    private lateinit var Q3A2: Button
    private lateinit var Q3A3: Button
    private lateinit var Q3Next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_test3)
        Q3A1 = findViewById(R.id.Q3A1)
        Q3A2 = findViewById(R.id.Q3A2)
        Q3A3 = findViewById(R.id.Q3A3)
        Q3Next = findViewById(R.id.Q3Next)

        var value: Int = intent.getIntExtra("value",0)

        //답변 클릭 시 다음 버튼 활성화, 중복선택 X
        Q3A1.setOnClickListener {
            Q3A1.isSelected = !Q3A1.isSelected
            Q3A2.isSelected = false
            Q3A3.isSelected = false
            Q3Next.isEnabled = Q3A1.isSelected
        }
        Q3A2.setOnClickListener {
            Q3A1.isSelected = false
            Q3A2.isSelected = !Q3A2.isSelected
            Q3A3.isSelected = false
            Q3Next.isEnabled = Q3A2.isSelected
        }
        Q3A3.setOnClickListener {
            Q3A1.isSelected = false
            Q3A2.isSelected = false
            Q3A3.isSelected = !Q3A3.isSelected
            Q3Next.isEnabled = Q3A3.isSelected
        }

        Q3Next.setOnClickListener {
            //현재 선택한 답변 확인 후 점수 부여
            if(Q3A1.isSelected){
                value+=3
            }else if(Q3A2.isSelected){
                value+=2
            }else{
                value+=1
            }
            val intent = Intent(this, testResultActivity::class.java)
            intent.putExtra("value",value)
            startActivity(intent)
        }

    }
}