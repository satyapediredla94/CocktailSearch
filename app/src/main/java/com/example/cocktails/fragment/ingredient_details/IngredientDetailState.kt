package com.example.cocktails.fragment.ingredient_details

import com.example.cocktails.data.ingredient_details.Ingredient

sealed class IngredientDetailState {

    object Default : IngredientDetailState()

    data class IngredientDetails(val ingredient: Ingredient) : IngredientDetailState()

    object Error : IngredientDetailState()

}