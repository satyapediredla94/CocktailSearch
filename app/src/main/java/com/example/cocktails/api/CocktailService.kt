package com.example.cocktails.api

import androidx.lifecycle.LiveData
import com.example.cocktails.data.Drink
import com.example.cocktails.data.Ingredient
import com.example.cocktails.data.IngredientDrink
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {

    @GET("/filter.php")
    suspend fun cocktailSearchByIngredient(
        @Query("i") searchParam: String
    ) : LiveData<List<IngredientDrink>>

    @GET("/filter.php")
    suspend fun cocktailSearchByAlcoholicAndNon(
        @Query("a") searchParam: String
    ) : LiveData<List<IngredientDrink>>

    @GET("/filter.php")
    suspend fun cocktailSearchByCategory(
        @Query("c") searchParam: String
    ) : LiveData<List<IngredientDrink>>

    @GET("/lookup.php")
    suspend fun cocktailById(
        @Query("i") searchParam: Int
    ) : LiveData<List<Drink>>

    @GET("/lookup.php")
    suspend fun ingredientById(
        @Query("iid") searchParam: Int
    ) : LiveData<List<Drink>>

    @GET("/search.php")
    suspend fun ingredientByName(
        @Query("i") searchParam: String
    ) : LiveData<List<Ingredient>>

    @GET("/search.php")
    suspend fun cocktailByName(
        @Query("s") searchParam: String
    ) : LiveData<List<Drink>>

    @GET("/random.php")
    suspend fun getRandomCocktail() : LiveData<List<Drink>>

}