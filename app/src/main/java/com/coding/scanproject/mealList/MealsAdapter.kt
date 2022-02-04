package com.coding.scanproject.mealList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.databinding.ItemMealBinding
import android.content.Intent
import com.coding.scanproject.mealDetail.DetailMealActivity
import com.squareup.picasso.Picasso

class MealsAdapter(private val meals: List<MealsData>) : RecyclerView.Adapter<MealsAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemMealBinding) :  RecyclerView.ViewHolder(binding.root){
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meals = meals[position]

        val imageUrl : String = meals.strMealThumb
        Picasso.get().load(imageUrl).into(holder.binding.imageView)
        holder.binding.strMeal.text = meals.strMeal
        holder.binding.idMeal.text = meals.idMeal
        holder.binding.btDetails.setOnClickListener(View.OnClickListener {

        val intent = Intent(it.context, DetailMealActivity::class.java)
            intent.putExtra("idMeal", holder.binding.idMeal.text);
            Log.i("Mainactvity", ""+holder.binding.idMeal.text)
            it.context.startActivity(intent);
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMealBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}