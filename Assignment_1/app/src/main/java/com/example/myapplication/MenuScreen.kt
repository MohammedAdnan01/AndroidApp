package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.content.SharedPreferences
import android.widget.Button
import android.widget.ToggleButton

class MenuScreen : Activity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val buttonStartGame = findViewById<Button>(R.id.buttonStartGame)
        val buttonShowScore = findViewById<View>(R.id.buttonShowScore)
        val buttonBack = findViewById<View>(R.id.buttonBack)
        val score = intent.getIntExtra("SCORE", 0)
        val toggleMusic = findViewById<ToggleButton>(R.id.toggleMusic)

        // Load saved music state from SharedPreferences
        sharedPreferences = getSharedPreferences("APP_SETTINGS", MODE_PRIVATE)
        val isMusicOn = sharedPreferences.getBoolean("MUSIC_STATE", false)
        toggleMusic.isChecked = isMusicOn

        if (isMusicOn) {
            startService(Intent(this, MusicService::class.java))
        }

        // Toggle Button Click Listener
        toggleMusic.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startService(Intent(this, MusicService::class.java))
            } else {
                stopService(Intent(this, MusicService::class.java))
            }
            // Save music state in SharedPreferences
            sharedPreferences.edit().putBoolean("MUSIC_STATE", isChecked).apply()
        }

        // Start Game Button
        buttonStartGame.setOnClickListener {
            val intent = Intent(this, QuestionAnswer::class.java)
            startActivity(intent)
            QuestionAnswer.SharedData.selectedAnswerArray.clear()
            QuestionAnswer.SharedData.generatedNumbers.clear()
        }

        // Show Score Button
        buttonShowScore.setOnClickListener {
            val intent = Intent(this, QuizActivityResults::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
        }

        // Back to Welcome Screen Button
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}