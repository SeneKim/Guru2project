package com.example.guru2project.post

import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2project.R
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class RegisterActivity : AppCompatActivity() {
    // 로그에 사용할 TAG 변수 선언
    private val TAG = javaClass.simpleName

    // 사용할 컴포넌트 선언
    var editText: EditText? = null
    var finsh  :Button? = null

    // 유저아이디 변수
    var userid: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

// ListActivity 에서 넘긴 userid 를 변수로 받음
        userid = intent.getStringExtra("userid")

// 컴포넌트 초기화
        finsh = findViewById(R.id.finsh)
        editText = findViewById(R.id.editText)

// 버튼 이벤트 추가
        finsh.setOnClickListener(View.OnClickListener { // 게시물 등록 함수
            val regBoard: RegBoard = RegBoard()
            regBoard.execute(userid,  editText.getText().toString())
        })
    }

    internal inner class finsh :
        AsyncTask<String?, Void?, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute")
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute, $result")
            if (result == "success") {
// 결과값이 success 이면
// 토스트 메시지를 뿌리고
// 이전 액티비티(ListActivity)로 이동,
// 이때 ListActivity 의 onResume() 함수 가 호출되며, 데이터를 새로 고침
                Toast.makeText(this@RegisterActivity, "등록되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@RegisterActivity, result, Toast.LENGTH_SHORT).show()
            }
        }

        protected override fun doInBackground(vararg params: String): String {
            val userid = params[0]
            val title = params[1]
            val content = params[2]
            val server_url = "http://15.164.252.136/reg_board.php"
            val url: URL
            var response = ""
            try {
                url = URL(server_url)
                val conn = url.openConnection() as HttpURLConnection
                conn.readTimeout = 15000
                conn.connectTimeout = 15000
                conn.requestMethod = "POST"
                conn.doInput = true
                conn.doOutput = true
                val builder = Uri.Builder()
                    .appendQueryParameter("userid", userid)
                    .appendQueryParameter("title", title)
                    .appendQueryParameter("content", content)
                val query = builder.build().encodedQuery
                val os = conn.outputStream
                val writer = BufferedWriter(
                    OutputStreamWriter(os, "UTF-8")
                )
                writer.write(query)
                writer.flush()
                writer.close()
                os.close()
                conn.connect()
                val responseCode = conn.responseCode
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    var line: String
                    val br = BufferedReader(InputStreamReader(conn.inputStream))
                    while (br.readLine().also { line = it } != null) {
                        response += line
                    }
                } else {
                    response = ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return response
        }
    }
}