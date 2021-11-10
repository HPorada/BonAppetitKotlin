package com.example.bonappetitkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val mealsAPI: ArrayList<Meal> = ArrayList<Meal>()
    val randomMeal: Meal = Meal()
    var jObject: JSONObject = JSONObject()
    val mealsUser: ArrayList<Meal> = ArrayList<Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}