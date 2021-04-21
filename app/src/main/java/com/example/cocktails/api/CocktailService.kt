package com.example.cocktails.api

import com.example.cocktails.data.search.Drink
import com.example.cocktails.data.search.DrinkResult
import com.example.cocktails.data.search.Ingredient
import com.example.cocktails.data.drink_category.CategoryDrink
import com.example.cocktails.data.category.CategoryResult
import com.example.cocktails.data.drink_category.DrinkCategoryResult
import com.example.cocktails.data.ingredients.IngredientResult
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {

    @GET("filter.php")
    suspend fun cocktailSearchByIngredient(
        @Query("i") searchParam: String
    ): List<CategoryDrink>

    @GET("filter.php")
    suspend fun cocktailSearchByAlcoholicAndNon(
        @Query("a") searchParam: String
    ): List<CategoryDrink>

    @GET("filter.php")
    suspend fun cocktailSearchByCategory(
        @Query("c") searchParam: String
    ): DrinkCategoryResult


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

    @GET("list.php")
    suspend fun getIngredients(
        @Query("i") key: String = "list"
    ) : IngredientResult

    @GET("list.php")
    suspend fun getCategories(
        @Query("c") key: String = "list"
    ) : CategoryResult

}