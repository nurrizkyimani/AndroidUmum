package com.bocahrokok.androidumum.data

data class Questions (
    val id : Int,
    val question: String,
    val image : Int,
    val optionOne: String,
    val optionTwo: String,
    val correctAnswer: Int
)