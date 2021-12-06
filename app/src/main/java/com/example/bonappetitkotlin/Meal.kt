package com.example.bonappetitkotlin

import java.io.Serializable

abstract class Meal : Serializable, Comparable<Meal> {

    abstract fun getMealData(): String?;

    abstract fun getStrMeal(): String?

    abstract fun getStrMealThumb(): String?
}