package com.example.bonappetitkotlin

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class AddActivity: AppCompatActivity() {

    private val FOLDERNAME = "SavedRecipes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    fun onSaveClick(view: View){
        val edt1: EditText = findViewById(R.id.edtName) as EditText
        val name: String = edt1.text.toString()

        val edt2: EditText = findViewById(R.id.edtInstructions) as EditText
        val body: String = edt2.text.toString()

        if (name != null && !name.isEmpty() && body != null && !body.isEmpty()) {

            val myExternalFile = File(getExternalFilesDir(FOLDERNAME), name)

            try {
                FileOutputStream(myExternalFile).use { os ->
                    os.write(body.toByteArray())
                    os.close()
                    Toast.makeText(this, "Recipe saved.", Toast.LENGTH_LONG).show()
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Something went wrong. Try again.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Meal Name and/or Instructions can't be empty.", Toast.LENGTH_LONG)
                .show()
        }
    }
}