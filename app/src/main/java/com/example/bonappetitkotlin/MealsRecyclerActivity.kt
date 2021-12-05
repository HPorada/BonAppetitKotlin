package com.example.bonappetitkotlin

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

open class MealsRecyclerActivity : AppCompatActivity(), OnItemClickListener {
    open val mealList: MutableList<MealAPI> = mutableListOf()
    open var dataMeal: DataMeal = DataMeal()
    open var meals: ArrayList<MealAPI> = ArrayList<MealAPI>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_meals)

        mealList.addAll(createMealList())

        val mealListFinal = mealList as ArrayList<Meal>

        val rvMeals = findViewById<RecyclerView>(R.id.rvAreasList)
        rvMeals.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        val adapter: RecyclerView.Adapter<*> = MealsAdapter(mealListFinal, this)

        rvMeals.layoutManager = layoutManager
        rvMeals.adapter = adapter
    }

    override fun onItemClick(view: View?, position: Int) {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("MealExtra", mealList[position])
        startActivity(intent)
    }

    fun createMealList(): MutableList<MealAPI> {

        fun makeRequest(s: String) {

            meals.clear()

            val url =
                "https://www.themealdb.com/api/json/v1/1/search.php?f=$s" //string templates

            val queue: RequestQueue = Volley.newRequestQueue(this@MealsRecyclerActivity)
            val gson: Gson = GsonBuilder().setPrettyPrinting().create()

            fun processRequest(response: String){
                dataMeal = gson.fromJson(response, DataMeal::class.java)
                meals = dataMeal.getMealsList()!!
            }

            val stringRequest: StringRequest =
                StringRequest(Request.Method.GET, url, { response ->
                    try {
                        //processRequest(response)
                        dataMeal = gson.fromJson(response, DataMeal::class.java)
                        meals = dataMeal.getMealsList()!!
                        Toast.makeText(this, meals[0].getStrMeal(), Toast.LENGTH_LONG).show()
                        //dataMeal = gson.fromJson(response, DataMeal::class.java)
                        //meals = dataMeal.getMealsList()!!
                        //return@StringRequest
                    } catch (e: KotlinNullPointerException) {
                    }

                }, { Toast.makeText(this, "That didn't work", Toast.LENGTH_LONG).show() }
            )
            //queue.cache.clear()
            queue.add(stringRequest)
        }

        val myList: MutableList<MealAPI> = mutableListOf()

        val alphabet = arrayOf(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        )

        for (s in alphabet) {
            makeRequest(s)
            myList.addAll(meals)
        }

        return myList


//            val stringRequest = StringRequest(
//                Request.Method.GET, url, { response ->
//                    try {
//                        dataMeal = gson.fromJson(response.toString(), DataMeal::class.java)
//                        meals = dataMeal.getMealsList()!!
//                        //Toast.makeText(this, meals[0].getStrMeal(), Toast.LENGTH_LONG).show()
//
////                        val strResp = response.toString()
////                        val jObj = JSONObject(strResp)
////                        val jArr: JSONArray = jObj.getJSONArray("meals")
////
////                        for (i in 0 until jArr.length()) {
////                            val jInner: JSONObject = jArr.getJSONObject(i)
////                            val newMeal: MealAPI =
////                                gson.fromJson(jInner.toString(), MealAPI::class.java)
////
////                            mealList.add(newMeal)
////                        }
//                    } catch (e: KotlinNullPointerException) {
//                    }
//
//                }, { Toast.makeText(this, "That didn't work", Toast.LENGTH_LONG).show() })
//
//            stringRequest.setShouldCache(false)
//            queue.cache.clear()
//            queue.add(stringRequest)
//
//            try {
//                myList.addAll(meals)
//                Toast.makeText(this, myList[0].getStrMeal(), Toast.LENGTH_LONG).show()
//            } catch (e: RuntimeException) {
//
//            }
//        }
//        mealList.addAll(myList)
//    }

    }
}