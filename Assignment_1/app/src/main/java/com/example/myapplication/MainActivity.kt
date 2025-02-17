package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonShowMainMenu = findViewById<Button>(R.id.buttonOK)
        buttonShowMainMenu.setOnClickListener {
            val intent = Intent(this, MenuScreen::class.java)
            startActivity(intent)

        }
    }
}

