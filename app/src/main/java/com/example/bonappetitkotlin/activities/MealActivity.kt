package com.example.bonappetitkotlin.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import com.example.bonappetitkotlin.R
import com.example.bonappetitkotlin.meal.Meal
import com.squareup.picasso.Picasso
import java.io.File


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

        when (mealType) {
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
                    .load(R.drawable.dish)
                    .resize(250, 250)
                    .into(image)

                instructions.movementMethod = ScrollingMovementMethod()
                instructions.text = meal.getMealData()
            }
        }
    }

    fun onTitleClick(view: View?) {

        val builder: AlertDialog.Builder = Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Delete")
        builder.setMessage("Do you want to delete the recipe?")
        builder.setPositiveButton("Confirm",
            DialogInterface.OnClickListener { dialog, which ->
                val text = findViewById<TextView>(R.id.txtMealName)
                val name = text.text.toString()

                deleteRecipe(name)
                Toast.makeText(this, "File $name deleted.", Toast.LENGTH_LONG).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> })

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun deleteRecipe(filename: String) {
        val folderName: String by lazy {
            "SavedRecipes"
        }

        val myExternalFile = File(getExternalFilesDir(folderName), filename)

        myExternalFile.delete()
    }

    fun onSendClick(view: View) {
        val builder: AlertDialog.Builder = Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Send")
        builder.setMessage("Do you want to send this recipe?")
        builder.setPositiveButton("Confirm",
            DialogInterface.OnClickListener { dialog, which ->
                val text = findViewById<TextView>(R.id.txtMealInstructions)
                val name = text.text.toString()

                sendRecipe(name)
            })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> })

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun sendRecipe(message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)

        startActivity(intent)
    }


    //generics, single expression function
    class MealType<T : Meal>(t: T) {
        fun getClassType(t: T): String = t::class.java.canonicalName as String
    }


}