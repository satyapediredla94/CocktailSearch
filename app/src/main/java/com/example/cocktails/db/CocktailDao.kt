package com.example.cocktails.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cocktails.data.Drink
import com.example.cocktails.data.Ingredient
import com.example.cocktails.utils.AppConstants

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDrink(drink: Drink)

    @Delete
    suspend fun deleteDrink(drink: Drink)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Query(
        "SELECT * FROM ${AppConstants.DRINK_TABLE_NAME}"
    )
    fun getDrinks(): LiveData<List<Drink>>

    @Query(
        "SELECT * FROM ${AppConstants.INGREDIENT_TABLE_NAME}"
    )
    fun getIngredients(): LiveData<List<Ingredient>>

}