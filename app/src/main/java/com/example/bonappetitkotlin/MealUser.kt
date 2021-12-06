package com.example.bonappetitkotlin

class MealUser(fileName: String?, text: String?) : Meal() {

    //null safety, Elvis operator
    private val name: String = fileName ?: "User's recipe"
    private val body: String = text ?: "Ingredients, measures, instructions of the recipe"

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