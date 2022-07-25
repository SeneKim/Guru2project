package com.example.guru2project.post

import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2project.R
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class DetailActivity : AppCompatActivity() {
    // 로그에 사용할 TAG
    private val TAG = javaClass.simpleName

    // 사용할 컴포넌트 선언
    var title_tv: TextView? = null
    var content_tv: TextView? = null
    var date_tv: TextView? = null
    var comment_layout: LinearLayout? = null
    var comment_et: EditText? = null
    var reg_button: Button? = null

    // 선택한 게시물의 번호
    var board_seq: String? = ""

    // 유저아이디 변수
    var userid: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

// ListActivity 에서 넘긴 변수들을 받아줌
        board_seq = intent.getStringExtra("board_seq")
        userid = intent.getStringExtra("userid")

// 컴포넌트 초기화
        content_tv = findViewById(R.id.content_tv)
        comment_layout = findViewById(R.id.comment_layout)
        comment_et = findViewById(R.id.comment_et)
        reg_button = findViewById(R.id.reg_button)

// 등록하기 버튼을 눌렀을 때 댓글 등록 함수 호출
        reg_button.setOnClickListener(View.OnClickListener {
            val regCmt: RegCmt = RegCmt()
            regCmt.execute(userid, comment_et.getText().toString(), board_seq)
        })

// 해당 게시물의 데이터 불러오기
        InitData()
    }

    private fun InitData() {

// 해당 게시물의 데이터를 읽어오는 함수, 파라미터로 보드 번호를 넘김
        val loadBoard: LoadBoard = LoadBoard()
        loadBoard.execute(board_seq)
    }

    internal inner class LoadBoard :
        AsyncTask<String?, Void?, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute")
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute, $result")
            try {
// 결과값이 JSONArray 형태로 넘어오기 때문에
// JSONArray, JSONObject 를 사용해서 파싱
                var jsonArray: JSONArray? = null
                jsonArray = JSONArray(result)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)

// Database 의 데이터들을 변수로 저장한 후 해당 TextView 에 데이터 입력
                    val title = jsonObject.optString("title")
                    val content = jsonObject.optString("content")
                    val crt_dt = jsonObject.optString("crt_dt")
                    title_tv!!.text = title
                    content_tv!!.text = content
                    date_tv!!.text = crt_dt
                }

// 해당 게시물에 대한 댓글 불러오는 함수 호출, 파라미터로 게시물 번호 넘김
                val loadCmt: LoadCmt = LoadCmt()
                loadCmt.execute(board_seq)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        protected override fun doInBackground(vararg params: String): String {
            val board_seq = params[0]

// 호출할 php 파일 경로
            val server_url = "http://15.164.252.136/load_board_detail.php"
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
                    .appendQueryParameter("board_seq", board_seq)
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

    // 게시물의 댓글을 읽어오는 함수
    internal inner class LoadCmt :
        AsyncTask<String?, Void?, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute")
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute, $result")

// 댓글을 뿌릴 LinearLayout 자식뷰 모두 제거
            comment_layout!!.removeAllViews()
            try {

// JSONArray, JSONObject 로 받은 데이터 파싱
                var jsonArray: JSONArray? = null
                jsonArray = JSONArray(result)

// custom_comment 를 불러오기 위한 객체
                val layoutInflater = LayoutInflater.from(this@DetailActivity)
                for (i in 0 until jsonArray.length()) {

// custom_comment 의 디자인을 불러와서 사용
                    val customView: View = layoutInflater.inflate(R.layout.custom_comment, null)
                    val jsonObject = jsonArray.getJSONObject(i)
                    val userid = jsonObject.optString("userid")
                    val content = jsonObject.optString("content")
                    val crt_dt = jsonObject.optString("crt_dt")
                    (customView.findViewById<View>(R.id.cmt_userid_tv) as TextView).text =
                        userid
                    (customView.findViewById<View>(R.id.cmt_content_tv) as TextView).text =
                        content
                    (customView.findViewById<View>(R.id.cmt_date_tv) as TextView).text =
                        crt_dt

// 댓글 레이아웃에 custom_comment 의 디자인에 데이터를 담아서 추가
                    comment_layout!!.addView(customView)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        protected override fun doInBackground(vararg params: String): String {
            val board_seq = params[0]
            val server_url = "http://15.164.252.136/load_cmt.php"
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
                    .appendQueryParameter("board_seq", board_seq)
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

    // 댓글을 등록하는 함수
    internal inner class RegCmt :
        AsyncTask<String?, Void?, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute")
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute, $result")

// 결과값이 성공으로 나오면
            if (result == "success") {

//댓글 입력창의 글자는 공백으로 만듦
                comment_et!!.setText("")

// 소프트 키보드 숨김처리
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(comment_et!!.windowToken, 0)

// 토스트메시지 출력
                Toast.makeText(this@DetailActivity, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show()

// 댓글 불러오는 함수 호출
                val loadCmt: LoadCmt = LoadCmt()
                loadCmt.execute(board_seq)
            } else {
                Toast.makeText(this@DetailActivity, result, Toast.LENGTH_SHORT).show()
            }
        }

        protected override fun doInBackground(vararg params: String): String {
            val userid = params[0]
            val content = params[1]
            val board_seq = params[2]
            val server_url = "http://15.164.252.136/reg_comment.php"
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
                    .appendQueryParameter("content", content)
                    .appendQueryParameter("board_seq", board_seq)
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