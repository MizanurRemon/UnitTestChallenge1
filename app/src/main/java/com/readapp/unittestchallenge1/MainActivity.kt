package com.readapp.unittestchallenge1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    private lateinit var submitButton: AppCompatButton
    private lateinit var titleEditText: EditText
    private lateinit var descriptionText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        submitButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("message", "${titleEditText.text} ${descriptionText.text}")
            startActivity(intent)
        }
    }

    private fun initView() {
        titleEditText = findViewById(R.id.titleEditText)
        descriptionText = findViewById(R.id.descriptionEditText)
        submitButton = findViewById(R.id.submitButton)
    }
}