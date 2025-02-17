package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class QuizActivityReview : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var optionsContainer: LinearLayout
    private lateinit var nextButton: Button

    private val questions = arrayOf(
        "Which is the largest ocean on Earth?",
        "Which country is known as the “Land of the Rising Sun”?",
        "Which river is the longest in the world?",
        "What is the capital city of Australia?",
        "Mount Everest, the highest peak in the world, is located in which mountain range?",
        "Which continent is the largest by land area?",
        "Which country is known as “The Land Down Under”?",
        "What is the largest desert in the world?",
        "Which country is both an island and a continent?",
        "Which city is located on two continents?",
        "The Great Barrier Reef, the world’s largest coral reef system, is located in which country?",
        "Which country is known as the “Land of Fire and Ice”?",
        "Which is the longest river in Europe?",
        "Which is the smallest continent by land area?",
        "What is the capital city of Canada?",
        "Which country is home to the Amazon Rainforest?",
        "Which African country is known as the “Cradle of Humankind”",
        "Which is the largest lake in Africa?",
        "What is the capital city of Spain?",
        "Which country is known as “The Land of a Thousand Lakes”?")

    private val options = arrayOf(
        arrayOf("Atlantic Ocean","Pacific Ocean","Indian Ocean","Arctic Ocean"),
        arrayOf("China", "Japan", "India","South Korea"),
        arrayOf("Nile River", "Amazon River", "Mississippi River","Yangtze River"),
        arrayOf("Sydney", "Melbourne", "Canberra","Perth"),
        arrayOf("Andes", "Himalayas", "Alps","Rocky Mountains"),
        arrayOf("Africa", "Asia", "North America", "South America"),
        arrayOf("New Zealand", "Australia", "Fiji", "Papua New Guinea"),
        arrayOf("Sahara Desert", "Gobi Desert", "Arabian Desert", "Antarctic Desert"),
        arrayOf("Madagascar", "Greenland", "Australia", "Iceland"),
        arrayOf("Istanbul", "Rome", "Cairo", "Moscow"),
        arrayOf("Indonesia", "Philippines", "Australia", "Maldives"),
        arrayOf("Iceland", "Greece", "Japan", "New Zealand"),
        arrayOf("Rhine River", "Danube River", "Volga River", "Seine River"),
        arrayOf("Europe", "Africa", "Antarctica", "South America"),
        arrayOf("Ottawa", "Toronto", "Montreal","Vancouver"),
        arrayOf("Brazil", "Colombia", "Peru", "Ecuador"),
        arrayOf("Kenya", "South Africa", "Egypt", "Ethiopia"),
        arrayOf("Lake Victoria", "Lake Tanganyika", "Lake Malawi", "Lake Chad"),
        arrayOf("Barcelona", "Seville", "Madrid", "Valencia"),
        arrayOf("Sweden", "Norway", "Finland", "Canada")
    )

    var correctAnswers = arrayOf(
        2, // (b)
        2, // (b)
        1, // (a)
        3, // (c)
        2, // (b)
        2, // (b)
        2, // (b)
        1, // (a)
        3, // (c)
        1, // (a)
        3, // (c)
        1, // (a)
        3, // (c)
        3, // (c)
        1, // (a)
        1, // (a)
        2, // (b)
        1, // (a)
        3, // (c)
        3  // (c)
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
                    correctOptionIndex -> setBackgroundColor(Color.parseColor("#6FE340"))
                    //if the user answered wrong, the right answered is highlighted in green and the wrongly answered one is highlighted red
                    selectedOptionIndex -> if (idx != correctOptionIndex) setBackgroundColor(Color.parseColor("#FA3C3C"))
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

