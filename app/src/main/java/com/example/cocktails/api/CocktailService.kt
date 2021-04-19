package com.example.cocktails.api

import com.example.cocktails.data.Drink
import com.example.cocktails.data.DrinkResult
import com.example.cocktails.data.Ingredient
import com.example.cocktails.data.IngredientDrink
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {

    @GET("filter.php")
    suspend fun cocktailSearchByIngredient(
        @Query("i") searchParam: String
    ): List<IngredientDrink>

    @GET("filter.php")
    suspend fun cocktailSearchByAlcoholicAndNon(
        @Query("a") searchParam: String
    ): List<IngredientDrink>

    @GET("filter.php")
    suspend fun cocktailSearchByCategory(
        @Query("c") searchParam: String
    ): List<IngredientDrink>


    @GET("lookup.php")
    suspend fun cocktailById(
        @Query("i") searchParam: String
    ): DrinkResult


    @GET("lookup.php")
    suspend fun ingredientById(
        @Query("iid") searchParam: String
    ): List<Drink>


    @GET("search.php")
    suspend fun ingredientByName(
        @Query("i") searchParam: String
    ): List<Ingredient>

    @GET("search.php")
    suspend fun cocktailByName(
        @Query("s") searchParam: String
    ): DrinkResult


    @GET("random.php")
    suspend fun getRandomCocktail(): List<Drink>


}