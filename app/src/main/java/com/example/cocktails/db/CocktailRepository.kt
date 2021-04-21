package com.example.cocktails.db

import androidx.lifecycle.LiveData
import com.example.cocktails.data.search.Drink
import com.example.cocktails.data.search.Ingredient
import com.example.cocktails.data.drink_category.CategoryDrink
import javax.inject.Inject

class CocktailRepository @Inject constructor (
    private val dao: CocktailDao
) : DataRepository {

    override suspend fun getDrinkById(id: String): LiveData<Drink> {
        TODO("Not yet implemented")
    }

    override suspend fun getIngredientById(id: String): LiveData<Ingredient> {
        TODO("Not yet implemented")
    }

    override suspend fun getDrinkByName(name: String): LiveData<Drink> {
        TODO("Not yet implemented")
    }

    override suspend fun getIngredientByName(name: String): LiveData<Ingredient> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDrinksCategories(): LiveData<List<CategoryDrink>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDrinksByIngredient(): LiveData<List<Drink>> {
        TODO("Not yet implemented")
    }


}