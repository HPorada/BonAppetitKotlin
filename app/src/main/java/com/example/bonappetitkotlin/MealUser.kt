package com.example.bonappetitkotlin

class MealUser(fileName: String, text: String) : Meal() {
//     init {
//        val name: String = fileName
//        val body: String = text
//    }

    private val name: String = fileName
    private val body: String = text

    override fun getMealData(): String {
        return body
    }

    override fun getStrMeal(): String {
        return name
    }

    override fun getStrMealThumb(): String {
        return R.drawable.spices.toString()
    }

    override fun compareTo(other: Meal): Int {
        val n = name.compareTo(other.getStrMeal().toString())
        return if (n == 0) name.compareTo(other.getStrMeal().toString()) else n
    }
}