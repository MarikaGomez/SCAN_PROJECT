package com.coding.scanproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.scanproject.databinding.ActivityMealsListBinding

class MealsListActivity : AppCompatActivity() {

    private val viewModel: MealsListViewModel by viewModels{MealsListViewModelFactory((application as MealsApplication).repository)}
    private lateinit var adapter: MealsAdapter
    private lateinit var binding: ActivityMealsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.deleteMeals()
//        viewModel.getMeals().observe(this, Observer {  meals ->
//            adapter = MealsAdapter(meals)
//            binding.recyclerview.adapter = adapter
//            binding.recyclerview.layoutManager = LinearLayoutManager(this)
//        })
    }
}