package com.example.guru2project

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener{

    lateinit var myProfileImage : ImageView
    lateinit var updateButton : Button
    lateinit var myWriting : TextView
    lateinit var myStorage : TextView
    lateinit var myChallenge: TextView
    lateinit var bottomNavigation : NavigationBarView

    lateinit var mydbHelper : SQLiteDatabase
    lateinit var sqliteDB : SQLiteOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myProfileImage = findViewById(R.id.myProfileImage)
        updateButton = findViewById(R.id.ProfileUpdateButton)
        myWriting = findViewById(R.id.MyWriting)
        myStorage = findViewById(R.id.MyStorage)
        myChallenge = findViewById(R.id.MyChallenge)




        //네비게이션 바 구현
        bottomNavigation.setOnItemSelectedListener (this)


        //됐나요!??!?!? 된건가요?!?!?!?! 제발 그렇다고 해주세요!!!!!!!!!!!!!!
        //여러분 화이팅 좋은 밤 되세요!!!
        //여러분 짱...

        updateButton.setOnClickListener {

        }



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")

    }
}