package com.example.bonappetitkotlin

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MealActivity : AppCompatActivity() {

    lateinit var meal: Meal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        meal = intent.getSerializableExtra("MealExtra") as Meal

        val typeM: MealType<Meal> = MealType(meal)
        val mealType = typeM.getClassType(meal)

        var name = findViewById<TextView>(R.id.txtMealName)
        var image = findViewById<ImageView>(R.id.imgRecipeImage)
        var instructions = findViewById<TextView>(R.id.txtMealInstructions)

        when(mealType){
            "com.example.bonappetitkotlin.MealAPI" -> {
                name.text = meal.getStrMeal()

                Picasso.get()
                    .load(meal.getStrMealThumb())
                    .resize(250, 250)
                    .into(image)

                instructions.movementMethod = ScrollingMovementMethod()
                instructions.text = meal.getMealData()
            }
            "com.example.bonappetitkotlin.MealUser" -> {
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

    class MealType<T: Meal>(t: T){
        fun getClassType(t: T): String {
            return t::class.java.canonicalName as String
        }
    }
}