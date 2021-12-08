package com.example.bonappetitkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bonappetitkotlin.*
import com.example.bonappetitkotlin.data.DataAPI
import com.example.bonappetitkotlin.data.DataMeal
import com.example.bonappetitkotlin.meal.Meal
import com.example.bonappetitkotlin.meal.MealAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso

// DONE:
// Type check and automatic cast 1 -> MainActivity, response to String
// String templates 1 -> url (MealsRecyclerActivity, createMealList())
// When expression 1 -> MealsRecyclerActivity, onCreate()
// Single-expression functions 1 -> MealActivity, MealType
// Nested functions 2 -> MealsRecyclerActivity, createMealList() + makeRequest()
// Lists or Sets 2 -> MealsRecyclerActivity (meals)
// Interfaces 3 -> OnItemClickListener
// Abstract classes 3 -> Meal
// Inheritance 8 -> Meal - MealUser, MealAPI
// Polymorphism 10 -> MealUser, MealAPI (getMealData)
// Data classes 3 -> DataAPI
// Lazy properties 3 -> AddActivity, FolderName,
// Exceptions 5 -> MealsRecyclerActivity, try/catch przy łączeniu z api (createMealList) i tworzeniu listy przepisów użytkownika (createRecipeList)
// Null safety 5 -> konstruktor MealUser, compareTo (operator !!)
// Lambdas 10 -> response (MainActivity, MealsRecyclerActivity), AddActivity, MealsRecyclerActivity
// Elvis operator 1 -> konstruktor MealUser
// Companion object 3 -> DataAPI, implementacja w MainActivity
// Generics 8 -> MealActivity, MealType


class MainActivity : AppCompatActivity() {

    var randomMeal: Meal = MealAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomRecipeImage = findViewById<View>(R.id.imgRandomMeal) as ImageView
        val randomRecipeName = findViewById<View>(R.id.txtRandomName) as TextView

        val queue: RequestQueue = Volley.newRequestQueue(this@MainActivity)
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()

        val stringRequest: StringRequest =
            StringRequest(Request.Method.GET, DataAPI.create().randomURL, { response -> //lambda, companion object

                var checkedResponse: String = ""

                //type check and automatic cast
                if (response is String) checkedResponse = response

                val dataMeal: DataMeal = gson.fromJson(checkedResponse, DataMeal::class.java)
                randomMeal = dataMeal.getMealsList()!![0]
                randomRecipeName.text = randomMeal.getStrMeal()
                Picasso.get()
                    .load(randomMeal.getStrMealThumb())
                    .into(randomRecipeImage)

            }, {
                fun onErrorResponse(error: VolleyError) {
                    randomRecipeName.text = "That didn't work!"
                }
            }
            )
        queue.add(stringRequest)
    }

    fun onImageClick(view: View?) {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("MealExtra", randomMeal)
        startActivity(intent)
    }

    fun onRandomMoreClick(view: View?) {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("MealExtra", randomMeal)
        startActivity(intent)
    }

    fun onAllMealsClick(view: View?) {
        val intent = Intent(this, MealsRecyclerActivity::class.java)
        intent.putExtra("isAll", true)
        startActivity(intent)
    }

    fun onAddClick(view: View?) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    fun onViewClick(view: View?) {
        val intent = Intent(this, MealsRecyclerActivity::class.java)
        intent.putExtra("isAll", false)
        startActivity(intent)
    }
}