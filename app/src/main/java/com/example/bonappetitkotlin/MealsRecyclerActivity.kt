package com.example.bonappetitkotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MealsRecyclerActivity: AppCompatActivity(), OnItemClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_meals)
    }

    override fun onItemClick(view: View?, position: Int) {
        TODO("Not yet implemented")
    }
}