package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.myapplication.QuestionAnswer.SharedData.selectedAnswerArray

class MainActivity : Activity() {

    //User Interface: This is the landing page of the application that displays welcome message, logo of uOttawa and
    // an 'OK' button that navigates to  Main Menu

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

