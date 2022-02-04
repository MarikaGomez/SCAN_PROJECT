package com.coding.scanproject

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.databinding.ItemMealBinding
import android.content.Intent





class MealsAdapter(private val meals: List<MealsData>) : RecyclerView.Adapter<MealsAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemMealBinding) :  RecyclerView.ViewHolder(binding.root){
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meals = meals[position]
        val intent = Intent(this, DetailsMealActivity::class.java)
        holder.binding.strMeal.text = meals.strMeal
        holder.binding.btDetails.setOnClickListener(View.OnClickListener {
            intent.putExtra("idMeal", holder.binding.idMeal.text);
            startActivity(intent);
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