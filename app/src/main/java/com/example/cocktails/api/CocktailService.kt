package com.example.cocktails.api

import androidx.lifecycle.LiveData
import com.example.cocktails.data.CocktailResult
import com.example.cocktails.data.IngredientResult
import com.example.cocktails.data.FilterSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {

    @GET("/filter.php")
    suspend fun cocktailSearchByIngredient(
        @Query("i") searchParam: String
    ) : LiveData<FilterSearchResult>

    @GET("/filter.php")
    suspend fun cocktailSearchByAlcoholicAndNon(
        @Query("a") searchParam: String
    ) : LiveData<FilterSearchResult>

    @GET("/filter.php")
    suspend fun cocktailSearchByCategory(
        @Query("c") searchParam: String
    ) : LiveData<FilterSearchResult>

    @GET("/lookup.php")
    suspend fun cocktailById(
        @Query("i") searchParam: Int
    ) : LiveData<CocktailResult>

    @GET("/lookup.php")
    suspend fun ingredientById(
        @Query("iid") searchParam: Int
    ) : LiveData<CocktailResult>

    @GET("/search.php")
    suspend fun ingredientByName(
        @Query("i") searchParam: String
    ) : LiveData<IngredientResult>

    @GET("/search.php")
    suspend fun cocktailByName(
        @Query("s") searchParam: String
    ) : LiveData<CocktailResult>

    @GET("/random.php")
    suspend fun getRandomCocktail() : LiveData<CocktailResult>

}