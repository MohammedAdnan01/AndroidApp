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

    private var currentQuestionNumber =0
    // Background colour to display the questions upon
    private val availableColors = mutableListOf(
        Color.parseColor("#FE9900"), // Orange
        Color.parseColor("#FFDE59"), // light yellow
        Color.parseColor("#7DDA58"), // light green
        Color.parseColor("#5DE2E7"), // light Blue
        Color.parseColor("#CC6CE7"),
        Color.parseColor("#DFC57B"),
        Color.parseColor("#E2EAF4"),
        Color.parseColor("#CECECE"),
        Color.parseColor("#EFC3CA"),
        Color.parseColor("#E7DDFF")
    )
    private var questionTextView: TextView? = null
    private var ansA: Button? = null
    private var ansB: Button? = null
    private var ansC: Button? = null
    private var ansD: Button? = null
    private var subBtn: Button? = null
    private var selectedAnswer = 0
    private var score = 0
    private var ranIndex=0

    // Array of 20 questions
    private var question = arrayOf(
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
        "Which country is known as “The Land of a Thousand Lakes”?",
        "The Galapagos Islands, known for their unique wildlife, belong to which country?",
        "Which country is the largest producer of oil in the world?",
        "The Taj Mahal, a famous UNESCO World Heritage Site, is located in which country?",
        "Which country is known for its fjords, volcanoes, and geothermal springs?",
        "Which is the largest island in the Mediterranean Sea?",
        "What is the capital city of Brazil?",
        "Which country is known for its tulips, windmills, and cycling culture?",
        "The Great Wall, one of the world’s most famous landmarks, is located in which country?",
        "Which country is located in both Europe and Asia?",
        "What is the highest mountain in North America?"
    )

    // The choices/option for the questions are presented as a nested array
    private var choices = arrayOf(
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
        arrayOf("Sweden", "Norway", "Finland", "Canada"),
        arrayOf("Ecuador", "Colombia", "Peru", "Costa Rica"),
        arrayOf("Saudi Arabia", "Russia", "United States", "Canada"),
        arrayOf("India", "China", "Pakistan", "Nepal"),
        arrayOf("Norway", "Iceland", "New Zealand", "Chile"),
        arrayOf("Sicily", "Cyprus", "Sardinia", "Crete"),
        arrayOf("Rio de Janeiro", "São Paulo", "Brasília", "Salvador"),
        arrayOf("Netherlands", "Belgium", "Denmark", "Switzerland"),
        arrayOf("China", "Japan", "South Korea", "Mongolia"),
        arrayOf("Turkey", "Greece", "Italy", "Ukraine"),
        arrayOf("Mount Kilimanjaro", "Mount Everest", "Denali (Mount McKinley)", "Mount Fuji")
    )

    // Answers of the respective questions are stored in an Array
    private var correctAnswers = arrayOf(
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
        3, // (c)
        1, // (a)
        1, // (a)
        1, // (a)
        2, // (b)
        1, // (a)
        3, // (c)
        1, // (a)
        1, // (a)
        1, // (a)
        3  // (c)
    )

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
