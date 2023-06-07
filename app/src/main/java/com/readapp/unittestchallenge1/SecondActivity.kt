package com.readapp.unittestchallenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var contentText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initView()

        contentText.text = intent.getStringExtra("message")
    }

    private fun initView() {
        contentText = findViewById(R.id.contentText)
    }
}