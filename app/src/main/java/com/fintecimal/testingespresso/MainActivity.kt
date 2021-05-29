package com.fintecimal.testingespresso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonNextAtivity = findViewById<Button>(R.id.button_next_activity)

        buttonNextAtivity.setOnClickListener {
            val intent = Intent(this, SecondaryActivity::class.java)
            startActivity(intent)
        }

    }
}