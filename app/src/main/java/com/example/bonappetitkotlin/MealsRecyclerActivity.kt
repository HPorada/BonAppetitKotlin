package com.example.bonappetitkotlin

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.*
import java.util.*
import java.util.Collections.sort
import kotlin.collections.ArrayList

open class MealsRecyclerActivity : AppCompatActivity(), OnItemClickListener {

    open var dataMeal: DataMeal = DataMeal()
    open var meals: ArrayList<MealAPI> = ArrayList<MealAPI>()
    open var recipes: ArrayList<MealUser> = ArrayList<MealUser>()
    open lateinit var listAll: List<Meal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_meals)

        val check = intent.getSerializableExtra("isAll") as Boolean

        when(check){
            true -> {
                when (isNetworkAvailable()) {
                    true -> {
                        val data = readFromAsset()

                        val dMeal: DataMeal = Gson().fromJson(data, DataMeal::class.java)
                        meals = dMeal.getMealsList()!!
                    }
                    false -> {
                        meals = createMealList() as ArrayList<MealAPI>
                    }
                }

                createRecipeList()
                listAll = recipes + meals
            }

            false -> {
                createRecipeList()
                listAll = recipes
            }
        }

        sort(listAll)

        val rvMeals = findViewById<RecyclerView>(R.id.rvAreasList)
        rvMeals.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        val adapter: RecyclerView.Adapter<*> = MealsAdapter(listAll as ArrayList<Meal>, this)

        rvMeals.layoutManager = layoutManager
        rvMeals.adapter = adapter
    }

    private fun readFromAsset(): String {
        var data: String = ""

        try {
            val file_name = "data.json"
            val bufferReader = application.assets.open(file_name).bufferedReader()
            data = bufferReader.use {
                it.readText()
            }
        } catch (e: FileNotFoundException) {

        }
        Log.d("readFromAsset", data)

        return data
    }

    override fun onItemClick(view: View?, position: Int) {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("MealExtra", listAll[position])
        startActivity(intent)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    private fun createMealList(): MutableList<MealAPI> {
        fun makeRequest(s: String) {
            meals.clear()

            val url =
                "https://www.themealdb.com/api/json/v1/1/search.php?f=$s" //string templates

            val queue: RequestQueue = Volley.newRequestQueue(this@MealsRecyclerActivity)
            val gson: Gson = GsonBuilder().setPrettyPrinting().create()

            fun processRequest(response: String) {
                dataMeal = gson.fromJson(response, DataMeal::class.java)
                meals = dataMeal.getMealsList()!!
            }

            val stringRequest: StringRequest =
                StringRequest(Request.Method.GET, url, { response ->
                    try {
                        processRequest(response)
                    } catch (e: KotlinNullPointerException) {
                    }

                }, { Toast.makeText(this, "That didn't work", Toast.LENGTH_LONG).show() }
                )

            stringRequest.retryPolicy = DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
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
    }

    open fun createRecipeList() {
        recipes.clear()

        val folderName: String by lazy{
            "SavedRecipes"
        }

        val file = getExternalFilesDir(folderName)
        val fileListing = file!!.listFiles()
        if (fileListing != null) {
            for (i in fileListing.indices) {
                val str = StringBuilder()
                try {
                    BufferedReader(FileReader(fileListing[i])).use { bufr ->
                        val fileName = fileListing[i].name
                        var text: String?
                        while (bufr.readLine().also { text = it } != null) {
                            str.append(text)
                        }
                        val recipe = MealUser(fileName, str.toString())
                        recipes.add(recipe)
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                    Toast.makeText(this, "File not found.", Toast.LENGTH_LONG).show()
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Something went wrong. Try again.", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        Log.d("recipes", recipes[0].getMealData().toString())
    }

    open fun onSearchClick(view: View?) {
        val edt = findViewById<View>(R.id.edtSearch) as EditText
        val search = edt.text.toString()

        for (i in listAll.indices) {
            if (search == listAll[i].getStrMeal()) {
                val meal = listAll[i]

                val intent = Intent(this, MealActivity::class.java)
                intent.putExtra("MealExtra", meal)
                startActivity(intent)
            }
        }
    }
}