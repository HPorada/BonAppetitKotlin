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
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import javax.xml.transform.ErrorListener

class MainActivity : AppCompatActivity() {

    val mealsAPI: ArrayList<Meal> = ArrayList<Meal>()
    val randomMeal: Meal = MealAPI()
    var jObject: JSONObject = JSONObject()
    val mealsUser: ArrayList<Meal> = ArrayList<Meal>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        createMealList()
//        val randomRecipeImage =
//            findViewById<View>(R.id.imgRandomMeal) as ImageView
//        val randomRecipeName =
//            findViewById<View>(R.id.txtRandomName) as TextView
//        val url = "https://www.themealdb.com/api/json/v1/1/random.php"
//        val queue: RequestQueue = Volley.newRequestQueue(this)
//        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
//        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
//            val data: DataMeal = gson.fromJson(response, DataMeal::class.java)
//            randomMeal = data.meals().get(0)
//            randomRecipeName.setText(randomMeal.getStrMeal())
//            Picasso.get()
//                .load(randomMeal.getStrMealThumb())
//                .into(randomRecipeImage)
//        }, object : ErrorListener() {
//            fun onErrorResponse(error: VolleyError?) {
//                randomRecipeName.text = "That didn't work!"
//            }
//        })
//        queue.add(stringRequest)
    }

    fun onImageClick(view: View?) {
        val intent = Intent(this, MealActivity::class.java)
        //intent.putExtra("MealExtra", randomMeal)
        startActivity(intent)
    }

    fun onRandomMoreClick(view: View?) {
        val intent = Intent(this, MealActivity::class.java)
        //intent.putExtra("MealExtra", randomMeal)

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
//        for (s in alphabet) {
//            val url = "https://www.themealdb.com/api/json/v1/1/search.php?f=$s"
//            val queue: RequestQueue = Volley.newRequestQueue(this)
//            val gson: Gson = GsonBuilder().setPrettyPrinting().create()
//            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
//                try {
//                    if (!response.equals("{\"meals\":null}")) {
//                        jObject = JSONObject(response)
//                        val jsonArray = jObject.getJSONArray("meals")
//                        for (j in 0 until jsonArray.length()) {
//                            val x = jsonArray.getJSONObject(j)
//                            val meal: Meal = gson.fromJson(x.toString(), Meal::class.java)
//                            mealsAPI.add(meal)
//                        }
//                    }
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }, object : ErrorListener() {
//                fun onErrorResponse(error: VolleyError?) {}
//            }
//            )
//            queue.add(stringRequest)
//        }
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