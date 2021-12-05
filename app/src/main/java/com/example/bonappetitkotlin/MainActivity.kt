package com.example.bonappetitkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import org.json.JSONArray
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
// Lambdas 10 -> response (MealsRecyclerActivity), sortowanie?


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

    var mealsAPI: MutableList<MealAPI> = mutableListOf()
    var randomMeal: Meal = MealAPI()
    lateinit var jObject: JSONObject
    var mealsUser: ArrayList<Meal> = ArrayList<Meal>()

    val data = DataAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomRecipeImage = findViewById<View>(R.id.imgRandomMeal) as ImageView
        val randomRecipeName = findViewById<View>(R.id.txtRandomName) as TextView

        val queue: RequestQueue = Volley.newRequestQueue(this@MainActivity)
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()

        val stringRequest: StringRequest =
            StringRequest(Request.Method.GET, data.randomURL, { response ->
                val dataMeal: DataMeal = gson.fromJson(response, DataMeal::class.java)
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
        //createMealList()

        val intent = Intent(this, MealsRecyclerActivity::class.java)
       // intent.putExtra("MEALS", arrayListOf(mealsAPI))
        startActivity(intent)
    }

    fun onAddClick(view: View?) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    fun onViewClick(view: View?) {
        //createRecipeList()
        val intent = Intent(this, MealsRecyclerActivity::class.java)
        startActivity(intent)
    }

//    fun createMealList() {
//        //mealsAPI.clear()
//
//        val alphabet = arrayOf(
//            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
//            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
//        )
//        for (s in alphabet) {
//
//            val url = "https://www.themealdb.com/api/json/v1/1/search.php?f=$s" //string templates
//
//            val queue2: RequestQueue = Volley.newRequestQueue(this)
//            val gson: Gson = GsonBuilder().setPrettyPrinting().create()
//
//            val stringRequest = StringRequest(
//                Request.Method.GET, url, { response ->
//
//                    if (response != "{\"meals\":null}") {
//                        val strResp = response.toString()
//                        val jObj: JSONObject = JSONObject(strResp)
//                        val jArr: JSONArray = jObj.getJSONArray("meals")
//
//                        for (i in 0 until jArr.length()) {
//                            val jInner: JSONObject = jArr.getJSONObject(i)
//                            val newMeal: MealAPI =
//                                gson.fromJson(jInner.toString(), MealAPI::class.java)
//
//                            mealsAPI.add(newMeal)
//                        }
//                    }
//
//
//                    //processResponse(response)
//
//
////                    if (response != "{\"meals\":null}") {
////                        val dataMeal: DataMeal = gson.fromJson(response, DataMeal::class.java)
////                        myList = dataMeal.getMealsList()!!
////                        mealsAPI.addAll(myList)
//////                        for (i in dataMeal.getMealsList()!!) {
//////                            mealsAPI.add(i)
//////                        }
////                    }
//                }, { Toast.makeText(this, "That didn't work", Toast.LENGTH_LONG).show() })
//
//            queue2.add(stringRequest)
//        }
//    }
//
////    val dataMeal: DataMeal = gson.fromJson(response, DataMeal::class.java)
////    randomMeal = dataMeal.getMealsList()!![0]
////    randomRecipeName.text = randomMeal.getStrMeal()
////    Picasso.get()
////    .load(randomMeal.getStrMealThumb())
////    .into(randomRecipeImage)
//
//    fun processResponse(response: String) {
//        //var myList = mutableListOf<Meal>()
//
//        if (response != "{\"meals\":null}") {
//            val dataMeal: DataMeal = Gson().fromJson(response, DataMeal::class.java)
//            val myList = dataMeal.getMealsList()!!
//            mealsAPI.toMutableList().addAll(myList)
//        }
//    }

//    fun createRecipeList() {
////        mealsUser.clear()
////        val file = getExternalFilesDir(AddActivity.FOLDERNAME)
////        val fileListing = file!!.listFiles()
////        if (fileListing != null) {
////            for (i in fileListing.indices) {
////                val str = StringBuilder()
////                try {
////                    BufferedReader(FileReader(fileListing[i])).use { bufr ->
////                        val fileName = fileListing[i].name
////                        var text: String?
////                        while (bufr.readLine().also { text = it } != null) {
////                            str.append(text)
////                        }
////                        val recipe = MealUser(fileName, str.toString())
////                        mealsUser.add(recipe)
////                    }
////                } catch (e: FileNotFoundException) {
////                    e.printStackTrace()
////                    Toast.makeText(this, "File not found.", Toast.LENGTH_LONG).show()
////                } catch (e: IOException) {
////                    e.printStackTrace()
////                    Toast.makeText(this, "Something went wrong. Try again.", Toast.LENGTH_LONG)
////                        .show()
////                }
////            }
////        }
//    }
}


//            val stringRequest: StringRequest = StringRequest(
//                Request.Method.GET, url, Response.Listener { response ->
////                    Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show()
////
////                    val listOfStrings = Gson().fromJson(response, mutableListOf<String>().javaClass)
////
////                    for (j in 0 until listOfStrings.size) {
////                        val asObject = Gson().fromJson(listOfStrings[j], MealAPI::class.java)
////                        mealsAPI.add(asObject)
////                    }
//
//                    try {
//                        if (response != "{\"meals\":null}") {
//                            jObject = JSONObject(response)
//                            val jsonArray = jObject.getJSONArray("meals")
//                            for (j in 0 until jsonArray.length()) {
//                                val x = jsonArray.getJSONObject(j)
//                                val meal: MealAPI = gson.fromJson(x.toString(), MealAPI::class.java)
//
//                                mealsAPI.add(meal)
//                            }
//                        }
//                    } catch (e: JSONException) { //exception
//                        e.printStackTrace();
//                    }
//                }, Response.ErrorListener {
////                    fun onErrorResponse(error: VolleyError) {
//                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
//                    //}
//                }
//            )