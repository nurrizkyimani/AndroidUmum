package com.bocahrokok.androidumum

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bocahrokok.androidumum.data.Constants
import com.bocahrokok.androidumum.data.Questions
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    //posisi question ke berapa?
    private var mCurrentPosition: Int = 1 // Default and the first question position
    //list questionya;
    private var mQuestionsList: ArrayList<Questions>? = null


    //opsi yang dipilih
    private var mSelectedOptionPosition: Int = 0

    //jumlahJawaban yang benar;
    private var mCorrectAnswers: Int = 0


    // START
    private var mUserName: String? = null
    // END


    //lifecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        //get username dari act sebelumnya
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        //get question dari constant
        mQuestionsList = Constants.getQuestion()

        //set all question
        setQuestion()

        //activate onclick listener
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

        //progressbar max;
        progressBar.max = mQuestionsList!!.size


    }

    private fun setQuestion() {

        //current position mulai dari 1; tapi jadinya nanti index 0;
        val question = mQuestionsList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

        //default option kita bikin fuc
        defaultOptionsView()

        //kalau misal currentposisi diakhir question list;
        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }

        //progress bar mengituki
        progressBar.progress = mCurrentPosition

        //
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.getMax()

        //set semuanya gambar, text dkk;
        tv_question.text = question.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo


    }

    //text view selected waktu diclick;
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        //default option view;
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    //default option saat diaawal
    fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)


        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    //when the user click the options;
    override fun onClick(v: View?) {

        when (v?.id) {

            //saat opsi 1 diclick
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
            }
            //saat opsi 2 diclick
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
            }

            //if click submit button
            R.id.btn_submit -> {

                //if no selected options
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        //kalau belum elesai questinya
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            //kalau udah;
                            // START
                            val intent =
                                Intent(this, ResultActivity::class.java)
                                intent.putExtra(Constants.USER_NAME, mUserName)
                                intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)

                                startActivity(intent)
                                finish()
                            // END
                        }
                    }
                //selected certain options;
                } else {
                    //ambil questionnya buat dibandingin;
                    val question = mQuestionsList?.get(mCurrentPosition - 1)


                    //bandingin question correct answernya dengan selectedOptionPosition
                    //kalau salah; pilih selectedoptionposition;
                    //if only for false

                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        //kalau bener ya dpilih ini;
                        mCorrectAnswers++
                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    //if current position udah di max jumlah question; ganti button submit jadi finish;
//                    if (mCurrentPosition == mQuestionsList!!.size) {
//                        btn_submit.text = "FINISH"
//                    } else {
//                        btn_submit.text = "GO TO NEXT QUESTION"
//                    }

                    mSelectedOptionPosition = 0
                }
            }
        }

    }

    // boiler plate buat drawable wrong and right;
    private fun answerView(answer: Int, drawableView: Int) {

        //selected positition; then follow drawable dari salah atau benar;
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}