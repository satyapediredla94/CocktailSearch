package com.example.cocktails.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktails.data.Drink
import com.example.cocktails.data.ingredients.Ingredient
import com.example.cocktails.fragment.drinks.DrinkAdapter
import com.example.cocktails.fragment.ingredient.IngredientAdapter

@BindingAdapter("app:setItems")
fun setItems(view: RecyclerView, items: List<Drink>?) {
    items?.let {
        (view.adapter as DrinkAdapter).submitList(it)
    }
}

@BindingAdapter("app:setIngredients")
fun setIngredients(view: RecyclerView, items: List<Ingredient>?) {
    items?.let {
        (view.adapter as IngredientAdapter).submitList(it)
    }
}