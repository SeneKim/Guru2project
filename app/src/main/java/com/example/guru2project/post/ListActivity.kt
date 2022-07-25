package com.example.guru2project.post

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
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


class ListActivity : AppCompatActivity() {
    // 로그에 사용할 TAG 변수
    private val TAG = javaClass.simpleName

    // 사용할 컴포넌트 선언
    var listView: ListView? = null
    var reg_button: Button? = null
    var userid: String? = ""

    // 리스트뷰에 사용할 제목 배열
    var titleList = ArrayList<String>()

    // 클릭했을 때 어떤 게시물을 클릭했는지 게시물 번호를 담기 위한 배열
    var seqList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

// LoginActivity 에서 넘긴 userid 값 받기
        userid = intent.getStringExtra("userid")

// 컴포넌트 초기화
        listView = findViewById(R.id.listView)

// listView 를 클릭했을 때 이벤트 추가
        listView.setOnItemClickListener(OnItemClickListener { adapterView, view, i, l -> // 어떤 값을 선택했는지 토스트를 뿌려줌
            Toast.makeText(
                this@ListActivity,
                adapterView.getItemAtPosition(i).toString() + " 클릭",
                Toast.LENGTH_SHORT
            ).show()

            // 게시물의 번호와 userid를 가지고 DetailActivity 로 이동
            val intent = Intent(this@ListActivity, DetailActivity::class.java)
            intent.putExtra("board_seq", seqList[i])
            intent.putExtra("userid", userid)
            startActivity(intent)
        })



        // onResume() 은 해당 액티비티가 화면에 나타날 때 호출됨
        override fun onResume() {
            super.onResume()
            // 해당 액티비티가 활성화 될 때, 게시물 리스트를 불러오는 함수를 호출
            val getBoard: GetBoard = GetBoard()
            getBoard.execute()
        }

        // 게시물 리스트를 읽어오는 함수
        internal inner class GetBoard :
            AsyncTask<String?, Void?, String>() {
            override fun onPreExecute() {
                super.onPreExecute()
                Log.d(TAG, "onPreExecute")
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute, $result")

// 배열들 초기화
                titleList.clear()
                seqList.clear()
                try {

// 결과물이 JSONArray 형태로 넘어오기 때문에 파싱
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val title = jsonObject.optString("title")
                        val seq = jsonObject.optString("seq")

// title, seq 값을 변수로 받아서 배열에 추가
                        titleList.add(title)
                        seqList.add(seq)
                    }

// ListView 에서 사용할 arrayAdapter를 생성하고, ListView 와 연결
                    val arrayAdapter: ArrayAdapter<*> =
                        ArrayAdapter(this@ListActivity, R.layout.activity_list, titleList)
                    listView!!.adapter = arrayAdapter

// arrayAdapter의 데이터가 변경되었을때 새로고침
                    arrayAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            protected override fun doInBackground(vararg p0: String?): String? {
//
// String userid = params[0];
// String passwd = params[1];
                val server_url = "http://15.164.252.136/load_board.php"
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
                        .appendQueryParameter("userid", "")
                    // .appendQueryParameter("passwd", passwd);
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
    }}