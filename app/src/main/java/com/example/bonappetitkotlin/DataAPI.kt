package com.example.bonappetitkotlin

data class DataAPI(
    val name: String = "The Meal DB",
    val randomURL: String = "https://www.themealdb.com/api/json/v1/1/random.php",
    val letterURL: String = "https://www.themealdb.com/api/json/v1/1/search.php?f="
) {
    companion object {
        fun create(): DataAPI = DataAPI()
    }
}
