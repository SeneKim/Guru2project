package com.example.guru2project.login.auth

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.*
import com.example.guru2project.R
import com.example.guru2project.login.preTest.preTest1Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class signUpActivity : AppCompatActivity() {
    private lateinit var cancelBtn: ImageButton
    private lateinit var signNext: Button

    private lateinit var emailArea: EditText
    private lateinit var pwdArea: EditText
    private lateinit var pwdChkArea: EditText
    private lateinit var nickNameArea: EditText

    private lateinit var emailMsg: TextView
    private lateinit var pwdMsg: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        cancelBtn = findViewById(R.id.cancelBtn)
        signNext = findViewById<Button>(R.id.signNext)
        emailArea = findViewById(R.id.emailArea)
        pwdArea = findViewById(R.id.pwdArea)
        pwdChkArea = findViewById(R.id.pwdChkArea)
        nickNameArea = findViewById(R.id.nickNameArea)
        emailMsg = findViewById(R.id.emailMsg)
        pwdMsg = findViewById(R.id.pwdMsg)

        //취소 버튼 클릭 -> 인트로 화면으로 이동
        cancelBtn.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }

        //회원가입 버튼 활성화 -> editText 값이 있으면
        emailArea.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                /*입력이 끝날때 작동*/
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*입력하기 전 작동*/
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*타이핑되는 텍스트에 변화가 있으면 작동*/
                if (emailArea.text.toString().isNotEmpty()
                ) {
                    signNext.isEnabled = true
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
                if (
                    pwdArea.text.toString().isNotEmpty()) {
                    signNext.isEnabled = true
                }
            }
        })
        pwdChkArea.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                /*입력이 끝날때 작동*/
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*입력하기 전 작동*/
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*타이핑되는 텍스트에 변화가 있으면 작동*/
                if (pwdChkArea.text.toString().isNotEmpty()
                ) {
                    signNext.isEnabled = true
                }
            }
        })
        nickNameArea.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                /*입력이 끝날때 작동*/
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*입력하기 전 작동*/
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*타이핑되는 텍스트에 변화가 있으면 작동*/
                if (nickNameArea.text.toString().isNotEmpty()
                ) {
                    signNext.isEnabled = true
                }
            }
        })

        //회원가입 실행
        auth = Firebase.auth
        signNext.setOnClickListener {

            var isGoToSign = true

            // 이메일, 비밀번호 확인
            val id = emailArea.text.toString()
            val pwd = pwdArea.text.toString()
            val pwdChk = pwdChkArea.text.toString()
            val nickname = nickNameArea.text.toString()

//editText 조건 확인
            //영어, 숫자, 특수기호(~,!,@,#)를 포함한 8자 이상 (비밀번호 조건)
            val regex2 =
                """^.*(?=^.{8,}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$""".toRegex()    //정규식 지정

            //아이디
            if (id.isEmpty()) {
                Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToSign = false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
                var t1 = Toast.makeText(this, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT)
                t1.show()
                emailMsg.setTextColor(Color.RED)
                isGoToSign = false
            }

            //비밀번호 유효성 검사
            else if (pwd.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToSign = false
            } else if (!pwd.matches(regex2)) {  //matches(): 특정 패턴의 문자열을 포함하는지 확인
                Toast.makeText(this, "비밀번호 형식을 지켜주세요", Toast.LENGTH_LONG).show()
                pwdMsg.setTextColor(Color.RED)
                isGoToSign = false
            }
            //--------------------------------------------------------------------------------------ㅇ
            else if (pwdChk.isEmpty()) {
                Toast.makeText(this, "비밀번호를 한번 더 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToSign = false
            }
            //비밀번호 2개가 같은지 확인
            else if (pwd != pwdChk) {
                Toast.makeText(this, "비밀번호를 똑같이 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToSign = false
            }
            //닉네임
            else if (nickname.isEmpty()) {
                Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToSign = false
            }

            //아이디, 비밀번호 조건 통과 -> 조건 메세지 색상 초기화
            if (Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
                emailMsg.setTextColor(Color.parseColor("#BDBDBD"))
            }
            if (pwd.matches(regex2)) {
                pwdMsg.setTextColor(Color.parseColor("#BDBDBD"))
            }

            if (isGoToSign) {
                auth.createUserWithEmailAndPassword(id, pwd)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()

                            //회원가입 후 사전 질문 화면으로 이동
                            val intent = Intent(this, preTest1Activity::class.java)
                            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                        }
                    }

            }

        }

    }
}