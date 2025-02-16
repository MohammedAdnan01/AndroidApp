package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MenuScreen : Activity() {

    // User Interface: This is the Main Menu that has three buttons - 'Start Game', 'Show Score', 'Back to Welcome Screen'
    // Start Game - Allows the user to start playing the trivia
    // Show Score - This displays the score of the last trivia game played.
    // Back to Welcome Screen - It allows navigation to the Main Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        val buttonStartGame = findViewById<Button>(R.id.buttonStartGame)
        val buttonShowScore = findViewById<View>(R.id.buttonShowScore)
        val buttonBack = findViewById<View>(R.id.buttonBack)
        val score = intent.getIntExtra("SCORE", 0)

        // Start Game Button
        buttonStartGame.setOnClickListener{
            val intent = Intent(this, QuestionAnswer::class.java)
            startActivity(intent)
//            finish()
            QuestionAnswer.SharedData.selectedAnswerArray.clear()
            QuestionAnswer.SharedData.generatedNumbers.clear()
        }

        // Show Score Button
        buttonShowScore.setOnClickListener{
            val intent = Intent(this, QuizActivityResults::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
        }

        // Back to Welcome Screen Button
        buttonBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}