package com.bocahrokok.androidumum.data

import com.bocahrokok.androidumum.R

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"


    fun getQuestion(): ArrayList<Questions> {

        val questlist = ArrayList<Questions>()

        val q1 = Questions(
            1,
            "What country is this?",
            R.drawable.ic_flag_of_argentina,
            "Canada",
            "Argentina",
            2
        )

        questlist.add(q1)

        val q2 = Questions(
            2,
            "What country is this?",
            R.drawable.ic_flag_of_australia,
            "Australia",
            "America",
            1
        )

        questlist.add(q2)

        val q3 = Questions(
            1,
            "What country is this?",
            R.drawable.ic_flag_of_argentina,
            "Canada",
            "Argentina",
            2
        )

        questlist.add(q3)

        return questlist
    }
}



