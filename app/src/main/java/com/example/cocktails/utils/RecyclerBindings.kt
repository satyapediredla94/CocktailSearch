package com.example.cocktails.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktails.data.Drink
import com.example.cocktails.fragment.drinks.DrinkAdapter

@BindingAdapter("app:setItems")
fun setItems(view: RecyclerView, items: List<Drink>?) {
    items?.let {
        (view.adapter as DrinkAdapter).submitList(it)
    }
}