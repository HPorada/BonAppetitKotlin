package com.example.bonappetitkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bonappetitkotlin.R
import com.example.bonappetitkotlin.meal.Meal
import com.squareup.picasso.Picasso

class MealsAdapter(
    mMeal: ArrayList<Meal>,
    mOnClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MealsAdapter.ViewHolder>() {
    class ViewHolder(
        itemView: View,
        onItemClickListener: OnItemClickListener

    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var text: TextView
        var image: ImageView
        var listener: OnItemClickListener

        override fun onClick(v: View) {
            listener.onItemClick(v, layoutPosition)
        }

        init {
            text = itemView.findViewById(R.id.txtListMeal) as TextView
            image = itemView.findViewById(R.id.imgListMeal) as ImageView

            listener = onItemClickListener

            itemView.setOnClickListener(this)
        }
    }

    private val mMeal: ArrayList<Meal>
    private val mOnClickListener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val mealView: View = inflater.inflate(R.layout.item_meal, parent, false)
        return ViewHolder(mealView, mOnClickListener)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val meal: Meal = mMeal[position]

        val textView = holder.text
        textView.text = meal.getStrMeal()

        if (meal.getStrMealThumb() == "dish") {
            val imageView = holder.image
            Picasso.get()
                .load(R.drawable.dish)
                .resize(100, 100)
                .into(imageView)
        } else {
            val imageView = holder.image
            Picasso.get()
                .load(meal.getStrMealThumb())
                .resize(100, 100)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return mMeal.size
    }

    init {
        this.mMeal = mMeal as ArrayList<Meal>
        this.mOnClickListener = mOnClickListener
    }
}



