package com.example.bonappetitkotlin

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        /*  val intent = intent
          val meal = getIntent().getSerializableExtra("MealExtra") as Meal?
          val name = findViewById<View>(R.id.txtMealName) as TextView
          val image =
              findViewById<View>(R.id.imgRecipeImage) as ImageView
          val instructions =
              findViewById<View>(R.id.txtMealInstructions) as TextView
          name.setText(meal.getStrMeal())
          Picasso.get()
              .load(meal.getStrMealThumb())
              .resize(250, 250)
              .into(image)
          instructions.movementMethod = ScrollingMovementMethod()
          instructions.text = meal.toString()
     */
    }
}