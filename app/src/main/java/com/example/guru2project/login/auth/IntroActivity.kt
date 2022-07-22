package com.example.guru2project.login.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.guru2project.MainScreenActivity
import com.example.guru2project.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {

    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button

    private lateinit var emailArea: EditText
    private lateinit var pwdArea: EditText

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        auth = Firebase.auth

        signUpBtn = findViewById(R.id.signUpBtn)
        loginBtn = findViewById(R.id.loginBtn)
        emailArea = findViewById(R.id.login_emailArea)
        pwdArea = findViewById(R.id.login_pwdArea)

        //회원가입 버튼 클릭 -> 회원가입 화면으로 이동
        signUpBtn.setOnClickListener {
            val intent = Intent(this, signUpActivity::class.java)
            startActivity(intent)
        }

        //로그인 버튼 활성화
        emailArea.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                /*입력이 끝날때 작동*/
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*입력하기 전 작동*/
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*타이핑되는 텍스트에 변화가 있으면 작동*/
                if (emailArea.text.toString().isNotEmpty() &&
                    pwdArea.text.toString().isNotEmpty()
                ) {
                    loginBtn.isEnabled = true
                }
            }
        })

        pwdArea.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                /*입력이 끝날때 작동*/
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*입력하기 전 작동*/
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*타이핑되는 텍스트에 변화가 있으면 작동*/
                if (emailArea.text.toString().isNotEmpty() &&
                    pwdArea.text.toString().isNotEmpty()
                ) {
                    loginBtn.isEnabled = true
                }
            }
        })

        //로그인 버튼 클릭 -> 이메일/비밀번호 확인 -> 메인화면으로 이동
        loginBtn.setOnClickListener {
            val email = emailArea.text.toString()
            val pwd = pwdArea.text.toString()

            auth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainScreenActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }
}
