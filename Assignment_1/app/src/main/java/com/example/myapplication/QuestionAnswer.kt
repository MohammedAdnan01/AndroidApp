package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
import android.graphics.Color
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.QuestionAnswer.SharedData.generatedNumbers
import com.example.myapplication.QuestionAnswer.SharedData.selectedAnswerArray
import com.example.myapplication.QuizActivityResults

class QuestionAnswer : AppCompatActivity(), View.OnClickListener{

    // User Interface: This screen is the Trivia Game play part. The user is presented with four questions each having three options.
    // User will choose an option and then click on Submit button to navigate to the next question.

    var currentQuestionNumber =0
    // Background colour to display the questions upon
    private val availableColors = mutableListOf(
        Color.parseColor("#b03060"), // Dark Pastel Red
        Color.parseColor("#008b8b"), // Dark Red
        Color.parseColor("#cd853f"), // Medium Sea Green
        Color.parseColor("#4682b4"), // Steel Blue
    )
    var questionTextView: TextView? = null

    var ansA: Button? = null
    var ansB: Button? = null

    // Array of 20 questions
    var question = arrayOf(
        "Which company owns Android?",
        "Which one is not a programming language?",
        "Which platform is known for browsing videos?",
        "Which company owns iPhone?",
        "What is the currency of the United States?",
        "Who wrote the Harry Potter series?",
        "What is the capital of Japan?",
        "Who painted the Mona Lisa?",
        "What is the chemical symbol for water?",
        "Who discovered gravity?",
        "What is the capital of France?",
        "What is the largest ocean on Earth?",
        "Who was the first man to walk on the moon?",
        "What is the square root of 144?",
        "What is the chemical symbol for gold?",
        "Who wrote the play 'Hamlet'?",
        "What is the largest mammal in the world?",
        "Who painted the Starry Night?",
        "What is the capital of Australia?",
        "What is the chemical symbol for oxygen?"
    )

    // The choices/option for the questions are presented as a nested array
    var choices = arrayOf(
        arrayOf("Google", "Apple", "Nokia"),
        arrayOf("Java", "Kotlin", "Notepad"),
        arrayOf("Facebook", "WhatsApp", "YouTube"),
        arrayOf("Google", "Apple", "Nokia"),
        arrayOf("Dollar", "Euro", "Pound"),
        arrayOf("J.K. Rowling", "Stephen King", "George Orwell"),
        arrayOf("Tokyo", "Beijing", "Seoul"),
        arrayOf("Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh"),
        arrayOf("H2O", "CO2", "NaCl"),
        arrayOf("Isaac Newton", "Albert Einstein", "Galileo Galilei"),
        arrayOf("Paris", "Berlin", "London"),
        arrayOf("Pacific Ocean", "Atlantic Ocean", "Indian Ocean"),
        arrayOf("Neil Armstrong", "Buzz Aldrin", "Michael Collins"),
        arrayOf("12", "14", "16"),
        arrayOf("Au", "Ag", "Fe"),
        arrayOf("William Shakespeare", "Charles Dickens", "Jane Austen"),
        arrayOf("Blue Whale", "African Elephant", "Giraffe"),
        arrayOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci"),
        arrayOf("Canberra", "Sydney", "Melbourne"),
        arrayOf("O", "O2", "CO2")
    )

    // Answers of the respective questions are stored in an Array
    var correctAnswers = arrayOf(
        1, // "Google" (a)
        3, // "Notepad" (b)
        3, // "YouTube" (c)
        2, // "Apple" (a)
        1, // "Dollar" (a)
        1, // "J.K. Rowling" (a)
        1, // "Tokyo" (a)
        1, // "Leonardo da Vinci" (a)
        1, // "H2O" (a)
        1, // "Isaac Newton" (a)
        1, // "Paris" (a)
        1, // "Pacific Ocean" (a)
        1, // "Neil Armstrong" (a)
        1, // "12" (a)
        1, // "Au" (a)
        1, // "William Shakespeare" (a)
        1, // "Blue Whale" (a)
        1, // "Vincent van Gogh" (a)
        1, // "Canberra" (a)
        2  // "O2" (c)
    )
    var ansC: Button? = null
    var submitBtn: Button? = null
    var selectedAnswer = 0
    var score = 0
    var ranIndex=0

    //Object to share the realtime data across different modules
    object SharedData {
        val generatedNumbers = mutableSetOf<Int>()
        val selectedAnswerArray: MutableList<Int> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_answer_screen)

        questionTextView = findViewById(R.id.question)
        ansA = findViewById(R.id.ans_A)
        ansB = findViewById(R.id.ans_B)
        ansC = findViewById(R.id.ans_C)
        submitBtn = findViewById(R.id.submit_btn)

        ansA?.setOnClickListener(this)
        ansB?.setOnClickListener(this)
        ansC?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)

        loadNewQuestion()
    }

    // Function to navigate to the next question
    private fun loadNewQuestion() {

        // Display only 4 questions
        if(currentQuestionNumber >= 4 ){
            finishQuiz();
            return;
        }

        // Changing the colour of the background everytime
        if (availableColors.isEmpty()) {
            return
        }
        val randomColorIndex = Random.nextInt(availableColors.size)
        val randomColor = availableColors[randomColorIndex]
        val qnaScreen = findViewById<View>((R.id.question_answer_screen))
        qnaScreen.setBackgroundColor(randomColor)
        availableColors.removeAt(randomColorIndex)

        val randomIndex = getRandomNumber(question.size)
        ranIndex= randomIndex;

        // Setting question and options based on index generated
        val randomQuestion = question[ranIndex]
        questionTextView?.text = randomQuestion
        ansA?.text = choices[ranIndex][0]
        ansB?.text = choices[ranIndex][1]
        ansC?.text = choices[ranIndex][2]
    }

    //Generating random numbers to pick 4 questions from the list of 20 questions
    private fun getRandomNumber(size: Int): Int {
        var randomNumber: Int
        do {
            randomNumber = Random.nextInt(question.size)
        } while (randomNumber in generatedNumbers)
        generatedNumbers.add(randomNumber)
        return randomNumber
    }

    // Function to stop displaying questions after the 4th question
    private fun finishQuiz() {
        val intent = Intent(this, QuizActivityResults::class.java)
        intent.putExtra("SCORE", score)
        startActivity(intent)

    }

    override fun onClick(v: View?) {

        // Setting the backgorund colour for the options
        ansA?.setBackgroundColor(Color.WHITE)
        ansB?.setBackgroundColor(Color.WHITE)
        ansC?.setBackgroundColor(Color.WHITE)

        val clickedButton = v as Button
        //If the submit button s clicked,
        if (clickedButton.id == R.id.submit_btn) {
            //and the selected answer is right the user gets 1 point
            if (selectedAnswer == correctAnswers[ranIndex]) {
                score++
            }
            // Incrementing the quiestion number to move to the next question
            currentQuestionNumber++

            //Capturing and adding the user's answer into a List
            selectedAnswerArray.add(selectedAnswer)
            Log.d("TAG", "*****selectedANswer array in questans****" + selectedAnswerArray.toString())
            loadNewQuestion()

        } else {
            //Indexing the option
            selectedAnswer = when (v?.id) {
                R.id.ans_A -> 1// Option A clicked
                R.id.ans_B -> 2 // Option B clicked
                R.id.ans_C -> 3 // Option C clicked
                else -> 0 // No option selected
            }
            clickedButton.setBackgroundColor(Color.parseColor("#778899"))
        }

    }




}
