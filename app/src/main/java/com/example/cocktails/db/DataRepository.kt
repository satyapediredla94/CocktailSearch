package com.example.cocktails.db

import androidx.lifecycle.LiveData
import com.example.cocktails.data.search.Drink
import com.example.cocktails.data.search.Ingredient
import com.example.cocktails.data.drink_category.CategoryDrink

interface DataRepository {

    suspend fun getDrinkById(id: String) : LiveData<Drink>

    suspend fun getIngredientById(id: String) : LiveData<Ingredient>

    suspend fun getDrinkByName(name: String) : LiveData<Drink>

    suspend fun getIngredientByName(name: String) : LiveData<Ingredient>

    suspend fun getAllDrinksCategories() : LiveData<List<CategoryDrink>>

    suspend fun getDrinksByIngredient() : LiveData<List<Drink>>



}