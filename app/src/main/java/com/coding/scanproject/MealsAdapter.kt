package com.coding.scanproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.databinding.ItemMealBinding


class MealsAdapter(private val meals: List<MealsData>) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemMealBinding) :  RecyclerView.ViewHolder(binding.root){
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meals = meals[position]
        holder.binding.textView.text = meals.strMeal
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