package com.example.bonappetitkotlin.activities

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bonappetitkotlin.meal.Meal
import com.example.bonappetitkotlin.R
import com.squareup.picasso.Picasso

class MealActivity : AppCompatActivity() {

    lateinit var meal: Meal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        meal = intent.getSerializableExtra("MealExtra") as Meal

        val typeM: MealType<Meal> = MealType(meal)
        val mealType = typeM.getClassType(meal)

        val name = findViewById<TextView>(R.id.txtMealName)
        val image = findViewById<ImageView>(R.id.imgRecipeImage)
        val instructions = findViewById<TextView>(R.id.txtMealInstructions)

        when(mealType){
            "com.example.bonappetitkotlin.meal.MealAPI" -> {
                name.text = meal.getStrMeal()

                Picasso.get()
                    .load(meal.getStrMealThumb())
                    .resize(250, 250)
                    .into(image)

                instructions.movementMethod = ScrollingMovementMethod()
                instructions.text = meal.getMealData()
            }
            "com.example.bonappetitkotlin.meal.MealUser" -> {
                name.text = meal.getStrMeal()

                Picasso.get()
                    .load(R.drawable.spices)
                    .resize(250,250)
                    .into(image)

                instructions.movementMethod = ScrollingMovementMethod()
                instructions.text = meal.getMealData()
            }
        }
    }

    //generics, single expression function
    class MealType<T: Meal>(t: T){
        fun getClassType(t: T): String = t::class.java.canonicalName as String
    }
}