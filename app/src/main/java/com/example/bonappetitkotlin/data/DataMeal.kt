package com.example.bonappetitkotlin.data

import com.example.bonappetitkotlin.meal.MealAPI
import java.util.*


class DataMeal {

    var meals: ArrayList<MealAPI>? = null

    fun getMealsList(): ArrayList<MealAPI>? {
        return meals;
    }
}