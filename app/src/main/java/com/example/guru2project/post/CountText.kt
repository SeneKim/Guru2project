package com.example.guru2project.post
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2project.R


class CountText : AppCompatActivity() {
    var editText: EditText? = null
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        /**
         * View들의 findViewById
         */
        editText = findViewById<View>(R.id.editText) as EditText
        textView = findViewById<View>(R.id.textView) as TextView


        editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val input = editText!!.text.toString()
                textView!!.text = input.length.toString() + " / 300"
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}