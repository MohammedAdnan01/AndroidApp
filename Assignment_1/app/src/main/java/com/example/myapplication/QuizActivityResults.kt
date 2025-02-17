package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class QuizActivityResults : AppCompatActivity() {

    private var scoreTextView: TextView? = null
    private var feedbackTextView: TextView? = null
    private var star1: ImageView? = null
    private var star2: ImageView? = null
    private var star3: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_screen)

        scoreTextView  = findViewById(R.id.score)
        feedbackTextView = findViewById(R.id.feedback_text)
        star1 = findViewById(R.id.star1)
        star2 = findViewById(R.id.star2)
        star3 = findViewById(R.id.star3)

        val score = intent.getIntExtra("SCORE", 0)
        scoreTextView?.text = score.toString()+"/10"

        when {
            score >= 7 -> {
                feedbackTextView?.text = "Excellent Job!"
                star1?.setImageResource(R.drawable.ic_full_star)
                star2?.setImageResource(R.drawable.ic_full_star)
                star3?.setImageResource(R.drawable.ic_full_star)
            }

            score > 3 -> {
                feedbackTextView?.text = "Good Effort!"
                star1?.setImageResource(R.drawable.ic_full_star)
                star2?.setImageResource(R.drawable.ic_full_star)
                star3?.setImageResource(R.drawable.ic_empty_star)
            }

            score > 0 -> {
                feedbackTextView?.text = "Keep Practicing!"
                star1?.setImageResource(R.drawable.ic_full_star)
                star2?.setImageResource(R.drawable.ic_empty_star)
                star3?.setImageResource(R.drawable.ic_empty_star)
            }

            else -> {
                feedbackTextView?.text = "Try again and keep learning!"
                star1?.setImageResource(R.drawable.ic_empty_star)
                star2?.setImageResource(R.drawable.ic_empty_star)
                star3?.setImageResource(R.drawable.ic_empty_star)
            }
        }

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
        }
    }
}