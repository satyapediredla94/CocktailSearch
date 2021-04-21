package com.example.cocktails.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktails.data.drink_category.CategoryDrink
import com.example.cocktails.data.ingredients.Ingredient
import com.example.cocktails.fragment.category_drink.CategoryDrinkAdapter
import com.example.cocktails.fragment.drink.DrinkAdapter
import com.example.cocktails.fragment.search.SearchDrinkAdapter
import com.example.cocktails.fragment.ingredient.IngredientAdapter

@BindingAdapter("app:setItems")
fun setItems(view: RecyclerView, items: List<com.example.cocktails.data.search.Drink>?) {
    items?.let {
        (view.adapter as SearchDrinkAdapter).submitList(it)
    }
}

@BindingAdapter("app:setIngredients")
fun setIngredients(view: RecyclerView, items: List<Ingredient>?) {
    items?.let {
        (view.adapter as IngredientAdapter).submitList(it)
    }
}

@BindingAdapter("app:setCategories")
fun setCategories(view: RecyclerView, items: List<com.example.cocktails.data.category.Drink>?) {
    items?.let {
        (view.adapter as DrinkAdapter).submitList(it)
    }
}

@BindingAdapter("setCategoryDrinks")
fun setCategoryDrinks(view: RecyclerView, items: List<CategoryDrink>?) {
    items?.let {
        (view.adapter as CategoryDrinkAdapter).submitList(it)
    }
}