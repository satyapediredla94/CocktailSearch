package com.example.cocktails.db

import androidx.lifecycle.LiveData
import com.example.cocktails.data.Drink
import com.example.cocktails.data.Ingredient
import com.example.cocktails.data.IngredientDrink
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

    override suspend fun getAllDrinksCategories(): LiveData<List<IngredientDrink>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDrinksByIngredient(): LiveData<List<Drink>> {
        TODO("Not yet implemented")
    }


}