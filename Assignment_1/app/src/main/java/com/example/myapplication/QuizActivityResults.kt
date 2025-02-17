package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivityResults : AppCompatActivity() {

    // User Interface: The score achieved by the user is displayed in this screen.

    var scoreTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_screen)

        scoreTextView  = findViewById(R.id.score)

        val score = intent.getIntExtra("SCORE", 0)
        scoreTextView?.text = score.toString()+"/10"

        // Return to Main Menu
        val buttonReturnScreen = findViewById<Button>(R.id.return_btn)
        buttonReturnScreen.setOnClickListener {
            val intent = Intent(this, MenuScreen::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
        }

        // Review the answers of the quiz
        val reviewAnswerButton = findViewById<Button>(R.id.review_qui_btn)
        reviewAnswerButton.setOnClickListener {
            val intent = Intent(this, QuizActivityReview::class.java)
            startActivity(intent)
//            QuestionAnswer.SharedData.generatedNumbers.clear()
//            Log.d("TAG", "*****empty array at review answers****" + QuestionAnswer.SharedData.selectedAnswerArray.toString())
        }
    }
}