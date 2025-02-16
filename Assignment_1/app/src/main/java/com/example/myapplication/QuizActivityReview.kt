package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.myapplication.QuestionAnswer.SharedData.selectedAnswerArray


class QuizActivityReview : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var optionsContainer: LinearLayout
    private lateinit var nextButton: Button

    private val questions = arrayOf("Which company owns Android?",
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
        "What is the chemical symbol for oxygen?")
    private val options = arrayOf(arrayOf("Google", "Apple", "Nokia"),
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

    var correctAnswers = arrayOf(
        1, // "Google" (a)
        3, // "Notepad" (b)
        3, // "YouTube" (c)
        1, // "Apple" (a)
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

    private var currentQuestionIndex = 0

    //Capturing values from QuestionAnswer.kt
    val numbers = QuestionAnswer.SharedData.generatedNumbers.toTypedArray()
    private val questionsIndicesToShow = numbers

    val optionsChosen = QuestionAnswer.SharedData.selectedAnswerArray.toTypedArray()

    //
    fun createArrayFromIndices(sourceArray: Array<Int>, indexArray: Array<Int>): Array<Int> {
        val validIndices = indexArray.filter { it < sourceArray.size }
        val resultArray = validIndices.map { sourceArray[it] }.toTypedArray()
        return resultArray
    }

    //Displaying the questions that were randomly generated from the array
    private fun displayQuestion(index: Int) {
        Log.d("TAG", "//////////////questionsIndicesToShow" + questionsIndicesToShow.contentToString())
        val questionIndex = questionsIndicesToShow[index]
        questionTextView.text = questions[questionIndex]
        optionsContainer.removeAllViews()

        val correctOptionIndex = correctAnswers[questionIndex] - 1
        val selectedOptionIndex = optionsChosen[index] - 1

        options[questionIndex].forEachIndexed { idx, option ->
            val optionButton = Button(this).apply {
                text = option

                when (idx) {
                    //if the user answered right the answer of the question is highlighted only in green
                    correctOptionIndex -> setBackgroundColor(Color.GREEN)
                    //if the user answered wrong, the right answered is highlighted in green and the wrongly answered one is highlighted red
                    selectedOptionIndex -> if (idx != correctOptionIndex) setBackgroundColor(Color.RED)
                    //the third option is left with transparent background
                    else -> setBackgroundColor(Color.TRANSPARENT)
                }
            }
            optionsContainer.addView(optionButton)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_activity_review)

        questionTextView = findViewById(R.id.questionTextView)
        optionsContainer = findViewById(R.id.optionsContainer)
        nextButton = findViewById(R.id.nextButton)

        displayQuestion(currentQuestionIndex)

        //'Next' button to navigate to the subsequent questions
        nextButton.setOnClickListener {
            if (currentQuestionIndex < questionsIndicesToShow.size - 1) {
                currentQuestionIndex++
                displayQuestion(currentQuestionIndex)
            }
        }

        //'Return to Main Menu' button to go back to the main menu
        val returnMainMenu = findViewById<View>(R.id.returnMainMenu)
        returnMainMenu.setOnClickListener{
            val intent = Intent(this, MenuScreen::class.java)
            startActivity(intent)
        }

    }

}

