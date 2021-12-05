package com.example.bonappetitkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

// DONE:
// String templates 1 -> url (MainActivity, createMealList())
// Lists or Sets 2 - MainActivity (meals)
// Interfaces 3 - OnItemClickListener
// Abstract classes 3 - Meal
// Inheritance 8 - Meal -> MealUser, MealAPI
// Polymorphism 10 - MealUser, MealAPI (getMealData)
// Data classes 3 - DataAPI
// Exceptions 5 -> try/catch przy łączeniu z api
// Lambdas 10 -> response (MainActivity), sortowanie?


// TODO
// Type check and automatic cast 1
// When expression 1
// Single-expression functions 1
// Infix function 2
// Nested functions 2
// Operator overloading 3
// Varargs 2
// Spread operator 2
// Maps 2
// Sealed classes 3
// Extension functions 3
// Lazy properties 3
// Delegations 8
// Observable properties 8
// Destructuring declarations 3 (do map)
// Null safety 5 -> przy dodawaniu własnego przepisu, gdy niedodany jest tytuł, automatycznie "Przepis"
// Save navigation operator 1
// Elvis operator 1
// Object expressions 5
// Companion object 3
// Generics 8

class MainActivity : AppCompatActivity() {

    val mealsAPI: ArrayList<Meal> = ArrayList<Meal>()
    var randomMeal: Meal = MealAPI()
    var jObject: JSONObject = JSONObject()
    val mealsUser: ArrayList<Meal> = ArrayList<Meal>()

    val data = DataAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createMealList()

        val randomRecipeImage = findViewById<View>(R.id.imgRandomMeal) as ImageView
        val randomRecipeName = findViewById<View>(R.id.txtRandomName) as TextView

        val queue: RequestQueue = Volley.newRequestQueue(this@MainActivity)
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()

        val stringRequest: StringRequest =
            StringRequest(Request.Method.GET, data.randomURL, Response.Listener { response ->

                val dataMeal: DataMeal = gson.fromJson(response, DataMeal::class.java)
                randomMeal = dataMeal.getMealsList()!![0]
                randomRecipeName.text = randomMeal.getStrMeal()
                Picasso.get()
                    .load(randomMeal.getStrMealThumb())
                    .into(randomRecipeImage)

            }, Response.ErrorListener {
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
        startActivity(intent)
    }

    fun onAddClick(view: View?) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    fun onViewClick(view: View?) {
        createRecipeList()
        val intent = Intent(this, MealsRecyclerActivity::class.java)
        startActivity(intent)
    }

    fun createMealList() {
        mealsAPI.clear()
        val alphabet = arrayOf(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        )
        for (s in alphabet) {

            val url = "https://www.themealdb.com/api/json/v1/1/search.php?f=$s" //string templates

            val queue: RequestQueue = Volley.newRequestQueue(this@MainActivity)
            val gson: Gson = GsonBuilder().setPrettyPrinting().create()

            val stringRequest = StringRequest(
                Request.Method.GET, url,

                Response.Listener { response ->

                    try {
                        if (response != "{\"meals\":null}") {
                            jObject = JSONObject(response)
                            val jsonArray = jObject.getJSONArray("meals")
                            for (j in 0 until jsonArray.length()) {
                                val x = jsonArray.getJSONObject(j)
                                val meal: MealAPI = gson.fromJson(x.toString(), MealAPI::class.java)
                                mealsAPI.add(meal)
                            }
                        }
                    } catch (e: JSONException) { //exception
                        e.printStackTrace();
                    }

                },
                Response.ErrorListener {
                    fun onErrorResponse(error: VolleyError) {
                    }
                }
            )
            queue.add(stringRequest)
        }
    }

    fun createRecipeList() {
//        mealsUser.clear()
//        val file = getExternalFilesDir(AddActivity.FOLDERNAME)
//        val fileListing = file!!.listFiles()
//        if (fileListing != null) {
//            for (i in fileListing.indices) {
//                val str = StringBuilder()
//                try {
//                    BufferedReader(FileReader(fileListing[i])).use { bufr ->
//                        val fileName = fileListing[i].name
//                        var text: String?
//                        while (bufr.readLine().also { text = it } != null) {
//                            str.append(text)
//                        }
//                        val recipe = MealUser(fileName, str.toString())
//                        mealsUser.add(recipe)
//                    }
//                } catch (e: FileNotFoundException) {
//                    e.printStackTrace()
//                    Toast.makeText(this, "File not found.", Toast.LENGTH_LONG).show()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                    Toast.makeText(this, "Something went wrong. Try again.", Toast.LENGTH_LONG)
//                        .show()
//                }
//            }
//        }
    }
}