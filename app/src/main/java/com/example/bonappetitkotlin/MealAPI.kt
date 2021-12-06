package com.example.bonappetitkotlin

class MealAPI : Meal() {

    private val idMeal: String? = null;
    private val strMeal: String? = null;
    private val strDrinkAlternate: String? = null
    private val strCategory: String? = null
    private val strArea: String? = null
    private val strInstructions: String? = null
    private val strMealThumb: String? = null
    private val strTags: String? = null
    private val strYoutube: String? = null

    private val strIngredient1: String? = null
    private val strIngredient2: String? = null
    private val strIngredient3: String? = null
    private val strIngredient4: String? = null
    private val strIngredient5: String? = null
    private val strIngredient6: String? = null
    private val strIngredient7: String? = null
    private val strIngredient8: String? = null
    private val strIngredient9: String? = null
    private val strIngredient10: String? = null
    private val strIngredient11: String? = null
    private val strIngredient12: String? = null
    private val strIngredient13: String? = null
    private val strIngredient14: String? = null
    private val strIngredient15: String? = null
    private val strIngredient16: String? = null
    private val strIngredient17: String? = null
    private val strIngredient18: String? = null
    private val strIngredient19: String? = null
    private val strIngredient20: String? = null

    private val strMeasure1: String? = null
    private val strMeasure2: String? = null
    private val strMeasure3: String? = null
    private val strMeasure4: String? = null
    private val strMeasure5: String? = null
    private val strMeasure6: String? = null
    private val strMeasure7: String? = null
    private val strMeasure8: String? = null
    private val strMeasure9: String? = null
    private val strMeasure10: String? = null
    private val strMeasure11: String? = null
    private val strMeasure12: String? = null
    private val strMeasure13: String? = null
    private val strMeasure14: String? = null
    private val strMeasure15: String? = null
    private val strMeasure16: String? = null
    private val strMeasure17: String? = null
    private val strMeasure18: String? = null
    private val strMeasure19: String? = null
    private val strMeasure20: String? = null

    private val strSource: String? = null
    private val dateModified: String? = null

    override fun getMealData(): String? {
        val str = StringBuilder()

        str.append("Ingredients: \n")

        if (strIngredient1 != null && !strIngredient1.isEmpty()) {
            str.append("\t - \t").append(strIngredient1).append(", ").append(strMeasure1)
                .append("\n")
        }
        if (strIngredient2 != null && !strIngredient2.isEmpty()) {
            str.append("\t - \t").append(strIngredient2).append(", ").append(strMeasure2)
                .append("\n")
        }
        if (strIngredient3 != null && !strIngredient3.isEmpty()) {
            str.append("\t - \t").append(strIngredient3).append(", ").append(strMeasure3)
                .append("\n")
        }
        if (strIngredient4 != null && !strIngredient4.isEmpty()) {
            str.append("\t - \t").append(strIngredient4).append(", ").append(strMeasure4)
                .append("\n")
        }
        if (strIngredient5 != null && !strIngredient5.isEmpty()) {
            str.append("\t - \t").append(strIngredient5).append(", ").append(strMeasure5)
                .append("\n")
        }
        if (strIngredient6 != null && !strIngredient6.isEmpty()) {
            str.append("\t - \t").append(strIngredient6).append(", ").append(strMeasure6)
                .append("\n")
        }
        if (strIngredient7 != null && !strIngredient7.isEmpty()) {
            str.append("\t - \t").append(strIngredient7).append(", ").append(strMeasure7)
                .append("\n")
        }
        if (strIngredient8 != null && !strIngredient8.isEmpty()) {
            str.append("\t - \t").append(strIngredient8).append(", ").append(strMeasure8)
                .append("\n")
        }
        if (strIngredient9 != null && !strIngredient9.isEmpty()) {
            str.append("\t - \t").append(strIngredient9).append(", ").append(strMeasure9)
                .append("\n")
        }
        if (strIngredient10 != null && !strIngredient10.isEmpty()) {
            str.append("\t - \t").append(strIngredient10).append(", ").append(strMeasure10)
                .append("\n")
        }
        if (strIngredient11 != null && !strIngredient11.isEmpty()) {
            str.append("\t - \t").append(strIngredient11).append(", ").append(strMeasure11)
                .append("\n")
        }
        if (strIngredient12 != null && !strIngredient12.isEmpty()) {
            str.append("\t - \t").append(strIngredient12).append(", ").append(strMeasure12)
                .append("\n")
        }
        if (strIngredient13 != null && !strIngredient13.isEmpty()) {
            str.append("\t - \t").append(strIngredient13).append(", ").append(strMeasure13)
                .append("\n")
        }
        if (strIngredient14 != null && !strIngredient14.isEmpty()) {
            str.append("\t - \t").append(strIngredient14).append(", ").append(strMeasure14)
                .append("\n")
        }
        if (strIngredient15 != null && !strIngredient15.isEmpty()) {
            str.append("\t - \t").append(strIngredient15).append(", ").append(strMeasure15)
                .append("\n")
        }
        if (strIngredient16 != null && !strIngredient16.isEmpty()) {
            str.append("\t - \t").append(strIngredient16).append(", ").append(strMeasure16)
                .append("\n")
        }
        if (strIngredient17 != null && !strIngredient17.isEmpty()) {
            str.append("\t - \t").append(strIngredient17).append(", ").append(strMeasure17)
                .append("\n")
        }
        if (strIngredient18 != null && !strIngredient18.isEmpty()) {
            str.append("\t - \t").append(strIngredient18).append(", ").append(strMeasure18)
                .append("\n")
        }
        if (strIngredient19 != null && !strIngredient19.isEmpty()) {
            str.append("\t - \t").append(strIngredient19).append(", ").append(strMeasure19)
                .append("\n")
        }
        if (strIngredient20 != null && !strIngredient20.isEmpty()) {
            str.append("\t - \t").append(strIngredient20).append(", ").append(strMeasure20)
                .append("\n")
        }

        str.append("\n")
        str.append("Instructions: \n")
        str.append(strInstructions)

        return str.toString()
    }

    override fun getStrMeal(): String? {
        return strMeal
    }

    override fun getStrMealThumb(): String? {
        return strMealThumb
    }

    override fun compareTo(other: Meal): Int {
        val name = strMeal!!.compareTo(other.getStrMeal().toString())
        return if (name == 0) strMeal.compareTo(other.getStrMeal().toString()) else name
    }

}